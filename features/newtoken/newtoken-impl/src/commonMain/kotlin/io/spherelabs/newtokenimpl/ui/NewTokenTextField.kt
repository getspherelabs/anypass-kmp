package io.spherelabs.newtokenimpl.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.spherelabs.designsystem.fonts.LocalStrings
import io.spherelabs.designsystem.spinner.LKSpinner
import io.spherelabs.foundation.color.Jaguar
import io.spherelabs.foundation.color.LavenderBlue
import io.spherelabs.newtokenimpl.presentation.NewTokenState
import io.spherelabs.newtokenimpl.presentation.NewTokenWish
import io.spherelabs.resource.fonts.GoogleSansFontFamily

@Composable
internal fun SecretTextField(
    modifier: Modifier = Modifier,
    textValue: String,
    focusManager: FocusManager,
    onTextValueChanged: (String) -> Unit,
) {
  Column {
    Text(
        text = "Secret key",
        modifier = modifier.fillMaxWidth().padding(start = 24.dp, top = 8.dp, bottom = 4.dp),
        textAlign = TextAlign.Start,
        color = Color.White,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        fontFamily = GoogleSansFontFamily,
    )
    TextField(
        modifier = modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp),
        value = textValue,
        keyboardOptions =
            KeyboardOptions(
                imeAction = ImeAction.Next,
            ),
        keyboardActions =
            KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) },
            ),
        colors =
            TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                backgroundColor = Jaguar,
                cursorColor = Color.Black,
                disabledLabelColor = Jaguar,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
        onValueChange = { newValue -> onTextValueChanged.invoke(newValue) },
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
    )
  }
}

@Composable
internal fun AdditionalInfoTextField(
    modifier: Modifier = Modifier,
    textValue: String,
    focusManager: FocusManager,
    onTextValueChanged: (String) -> Unit,
) {
  Column() {
    Text(
        modifier =
            modifier.fillMaxWidth().padding(start = 24.dp, top = 8.dp, end = 24.dp, bottom = 4.dp),
        text = "Additional info",
        textAlign = TextAlign.Start,
        color = Color.White,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        fontFamily = GoogleSansFontFamily,
    )
    TextField(
        modifier = modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp),
        value = textValue,
        keyboardOptions =
            KeyboardOptions(
                imeAction = ImeAction.Next,
            ),
        keyboardActions =
            KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) },
            ),
        colors =
            TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                backgroundColor = Jaguar,
                cursorColor = Color.Black,
                disabledLabelColor = Jaguar,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
        onValueChange = { newValue -> onTextValueChanged.invoke(newValue) },
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
    )
  }
}

@Composable
internal fun NewTokenTypeSpinner(
    modifier: Modifier = Modifier,
    state: NewTokenState,
    wish: (NewTokenWish) -> Unit,
) {
  Column {
    Spacer(modifier.height(8.dp))
    Text(
        text = "Algorithm Type",
        modifier = Modifier.fillMaxWidth().padding(start = 24.dp, bottom = 4.dp),
        textAlign = TextAlign.Start,
        color = Color.White,
        fontSize = 18.sp,
        fontFamily = GoogleSansFontFamily,
        fontWeight = FontWeight.Medium,
    )
    LKSpinner(
        expanded = state.isTypeExpanded,
        modifier = modifier,
        onExpandedChange = { wish.invoke(NewTokenWish.OnTypeExpandChanged(it)) },
        current = state.type,
        options = NewTokenState.types(),
        onOptionChosen = { wish.invoke(NewTokenWish.OnTypeChanged(it)) },
    )
  }
}

@Composable
internal fun NewTokenDigitSpinner(
    modifier: Modifier = Modifier,
    state: NewTokenState,
    wish: (NewTokenWish) -> Unit,
) {
  Column {
    Spacer(modifier.height(8.dp))
    Text(
        text = "Digits",
        modifier = modifier.fillMaxWidth().padding(start = 24.dp, bottom = 4.dp),
        textAlign = TextAlign.Start,
        color = Color.White,
        fontSize = 18.sp,
        fontFamily = GoogleSansFontFamily,
        fontWeight = FontWeight.Medium,
    )
    LKSpinner(
        expanded = state.isDigitExpanded,
        modifier = modifier,
        onExpandedChange = { wish.invoke(NewTokenWish.OnDigitExpandChanged(it)) },
        current = state.digit,
        options = NewTokenState.digits().map { it.toString() },
        onOptionChosen = { newOption -> wish.invoke(NewTokenWish.OnDigitChanged(newOption)) },
    )
  }
}

@Composable
internal fun NewTokenDurationSpinner(
    modifier: Modifier = Modifier,
    state: NewTokenState,
    wish: (NewTokenWish) -> Unit,
) {
  Column {
    Spacer(modifier.height(8.dp))
    Text(
        text = "Duration",
        modifier = modifier.fillMaxWidth().padding(start = 24.dp, bottom = 4.dp),
        textAlign = TextAlign.Start,
        color = Color.White,
        fontSize = 18.sp,
        fontFamily = GoogleSansFontFamily,
        fontWeight = FontWeight.Medium,
    )
    LKSpinner(
        expanded = state.isDurationExpanded,
        modifier = modifier,
        onExpandedChange = { wish.invoke(NewTokenWish.OnDurationExpandChanged(it)) },
        current = state.duration.toString(),
        options = NewTokenState.duration().map { it.toString() },
        onOptionChosen = { newOption ->
          wish.invoke(NewTokenWish.OnDurationChanged(newOption.toInt()))
        },
    )
  }
}

@Composable
internal fun SaveButton(
    modifier: Modifier = Modifier,
    wish: (NewTokenWish) -> Unit,
) {
  val strings = LocalStrings.current
  Spacer(modifier.height(8.dp))
  Button(
      modifier = Modifier.fillMaxWidth().height(65.dp).padding(start = 24.dp, end = 24.dp),
      colors =
          ButtonDefaults.buttonColors(
              backgroundColor = LavenderBlue.copy(0.3f),
          ),
      shape = RoundedCornerShape(24.dp),
      onClick = { wish.invoke(NewTokenWish.OnSubmitClicked) },
  ) {
    Text(
        text = strings.save,
        color = Color.White,
        fontSize = 18.sp,
        fontFamily = GoogleSansFontFamily,
        fontWeight = FontWeight.Medium,
    )
  }
}
