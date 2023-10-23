package io.spherelabs.designsystem.pininput

import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import io.spherelabs.designsystem.hooks.useScope
import io.spherelabs.designsystem.hooks.useState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
internal fun LKCell(
    modifier: Modifier,
    value: Char?,
    isCursorVisible: Boolean = false,
    obscureText: String?,
) {
    val scope = useScope()
    val (cursorSymbol, setCursorSymbol) = useState("")

    LaunchedEffect(key1 = cursorSymbol, isCursorVisible) {
        if (isCursorVisible) {
            scope.launch {
                delay(350)
                setCursorSymbol(if (cursorSymbol.isEmpty()) "|" else "")
            }
        }
    }

    Box(modifier = modifier) {
        Text(
            text =
            if (isCursorVisible) cursorSymbol
            else if (!obscureText.isNullOrBlank() && value?.toString().isNullOrBlank().not())
                obscureText
            else value?.toString() ?: "",
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.Center),
        )
    }
}
