package io.spherelabs.lockerkmp.components.cell

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun OtpCell(
    modifier: Modifier,
    value: String,
    isCursorVisible: Boolean = false
) {
    val scope = rememberCoroutineScope()
    val (cursorSymbol, setCursorSymbol) = remember { mutableStateOf("") }

    LaunchedEffect(key1 = cursorSymbol, isCursorVisible) {
        if (isCursorVisible) {
            scope.launch {
                delay(350)
                setCursorSymbol(if (cursorSymbol.isEmpty()) "|" else "")
            }
        }
    }

    Box(
        modifier = modifier
    ) {
        Text(
            text = if (isCursorVisible) cursorSymbol else value,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@ExperimentalComposeUiApi
@Composable
fun PinInput(
    modifier: Modifier = Modifier,
    length: Int = 4,
    value: String = "",
    onValueChanged: (String) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val keyboard = LocalSoftwareKeyboardController.current

    Row(
        modifier = Modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        (0 until length).map { index ->
            OtpCell(
                modifier = modifier
                    .size(width = 75.dp, height = 75.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(color = Color.White)
                    .focusRequester(focusRequester),
                value = value.getOrNull(index)?.toString() ?: "",
                isCursorVisible = value.length == index
            )
        }

    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PinInput(
    modifier: Modifier = Modifier,
    cellModifier: Modifier = Modifier,
    length: Int = 5,
    value: String = "",
    disableKeypad: Boolean = false,
    obscureText: String? = "*",
    onValueChanged: (String) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val keyboard = LocalSoftwareKeyboardController.current
    TextField(
        readOnly = disableKeypad,
        value = value,
        onValueChange = {
            if (it.length <= length) {
                if (it.all { c -> c in '0'..'9' }) {
                    onValueChanged(it)
                }
                if (it.length >= length) {
                    keyboard?.hide()
                }
            }
        },
        // Hide the text field
        modifier = Modifier
            .size(0.dp)
            .focusRequester(focusRequester),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        )
    )

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(length) {
            OtpCell(
                modifier = cellModifier
                    .size(width = 75.dp, height = 75.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(color = Color.White)
                    .clickable {
                        focusRequester.requestFocus()
                        keyboard?.show()
                    },
                value = value.getOrNull(it),
                isCursorVisible = value.length == it,
                obscureText
            )
            if (it != length - 1)
                Spacer(modifier = Modifier.size(8.dp))
        }
    }
}

@Composable
fun OtpCell(
    modifier: Modifier,
    value: Char?,
    isCursorVisible: Boolean = false,
    obscureText: String?
) {
    val scope = rememberCoroutineScope()
    val (cursorSymbol, setCursorSymbol) = remember { mutableStateOf("") }

    LaunchedEffect(key1 = cursorSymbol, isCursorVisible) {
        if (isCursorVisible) {
            scope.launch {
                delay(350)
                setCursorSymbol(if (cursorSymbol.isEmpty()) "|" else "")
            }
        }
    }

    Box(
        modifier = modifier
    ) {
        Text(
            text = if (isCursorVisible) cursorSymbol else if (!obscureText.isNullOrBlank() && value?.toString()
                    .isNullOrBlank().not()
            ) obscureText else value?.toString() ?: "",
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}