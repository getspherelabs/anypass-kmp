package io.spherelabs.designsystem.textfield

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.sourceInformationMarkerEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.spherelabs.foundation.color.Jaguar

@Composable
fun LKTitleTextField(
    textValue: String,
    modifier: Modifier = Modifier,
    keyboardActions: KeyboardActions = KeyboardActions(),
    onValueChanged: (String) -> Unit,
) {
    Column {
        TextField(
            modifier = modifier.width(240.dp).padding(start = 12.dp, end = 12.dp),
            value = textValue,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
            ),
            keyboardActions = keyboardActions,
            colors =
            TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                backgroundColor = Jaguar,
                cursorColor = Color.Black,
                disabledLabelColor = Jaguar,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            onValueChange = { newValue -> onValueChanged.invoke(newValue) },
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
        )
    }
}

@Composable
fun LKPasswordTextField(
    textValue: String,
    passwordVisibility: Boolean = false,
    titleColor: Color = Color.White,
    fontFamily: FontFamily,
    onToggleChanged: () -> Unit,
    onNextCallback: () -> Boolean? = { null },
    modifier: Modifier = Modifier,
    onValueChanged: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    Column {
        val lightBlue = Color(0xffd8e6ff)
        Text(
            text = "Password",
            modifier = Modifier.fillMaxWidth().padding(start = 24.dp, top = 8.dp, bottom = 4.dp),
            textAlign = TextAlign.Start,
            color = titleColor,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = fontFamily,
        )
        TextField(
            modifier = Modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp),
            value = textValue,
            colors =
            TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                backgroundColor = Jaguar,
                cursorColor = Color.Black,
                disabledLabelColor = Jaguar,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                autoCorrect = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    onNextCallback.invoke()
                },
                onDone = {
                    focusManager.clearFocus()
                },
            ),
            onValueChange = { newValue -> onValueChanged.invoke(newValue) },
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = Color.White,
                )
            },
            visualTransformation =
            if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon = if (passwordVisibility) {
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }

                IconButton(
                    onClick = {
                        onToggleChanged.invoke()
                    },
                ) {
                    Icon(
                        icon,
                        tint = Color.White,
                        contentDescription = "Visibility",
                    )
                }
            },
        )
    }
}

@Composable
fun KeyPasswordTextField(
    textValue: String,
    passwordVisibility: Boolean = false,
    title: String = "Key password",
    description: String? = null,
    titleColor: Color = Color.White,
    fontFamily: FontFamily,
    onToggleChanged: () -> Unit,
    modifier: Modifier = Modifier,
    onValueChanged: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val lightBlue = Color(0xffd8e6ff)

    Column {
        Text(
            text = title,
            modifier = Modifier.fillMaxWidth().padding(start = 24.dp, top = 8.dp, bottom = 4.dp),
            textAlign = TextAlign.Start,
            color = titleColor,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = fontFamily,
        )
        if (description != null) {
            Text(
                modifier = modifier.padding(horizontal = 24.dp, vertical = 4.dp),
                text = description,
                color = Color.White.copy(alpha = 0.7f),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
            )
        }

        TextField(
            modifier = Modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp),
            value = textValue,
            colors =
            TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                backgroundColor = Jaguar,
                cursorColor = Color.Black,
                disabledLabelColor = Jaguar,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                autoCorrect = true,
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                },
            ),
            onValueChange = { newValue -> onValueChanged.invoke(newValue) },
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = Color.White,
                )
            },
            visualTransformation =
            if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon = if (passwordVisibility) {
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }

                IconButton(
                    onClick = {
                        onToggleChanged.invoke()
                    },
                ) {
                    Icon(
                        icon, tint = Color.White,
                        contentDescription = "Visibility",
                    )
                }
            },
        )
    }
}

@Composable
fun APSNameTextField(
    textValue: String,
    fontFamily: FontFamily,
    modifier: Modifier = Modifier,
    onValueChanged: (String) -> Unit,
) {
    Column {
        Text(
            text = "Name",
            modifier = Modifier.fillMaxWidth().padding(start = 24.dp, bottom = 4.dp),
            textAlign = TextAlign.Start,
            color = Color.White,
            fontSize = 18.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
        )
        TextField(
            modifier = Modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp),
            value = textValue,
            colors =
            TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                backgroundColor = Jaguar,
                cursorColor = Color.Black,
                disabledLabelColor = Jaguar,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            onValueChange = { newValue -> onValueChanged.invoke(newValue) },
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            trailingIcon = { CloseIcon(textValue, onValueChanged) },
        )
    }
}

@Composable
fun LKEmailTextField(
    textValue: String,
    titleColor: Color = Color.White,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    fontFamily: FontFamily,
    modifier: Modifier = Modifier,
    onValueChanged: (String) -> Unit,
) {
    Column {
        val lightBlue = Color(0xffd8e6ff)
        val blue = Color(0xff76a9ff)
        Text(
            text = "Email",
            modifier = Modifier.fillMaxWidth().padding(start = 24.dp, bottom = 4.dp),
            textAlign = TextAlign.Start,
            color = titleColor,
            fontSize = 18.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
        )
        TextField(
            modifier = Modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp),
            value = textValue,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
            ),
            keyboardActions = keyboardActions,
            colors =
            TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                backgroundColor = Jaguar,
                cursorColor = Color.Black,
                disabledLabelColor = Jaguar,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            onValueChange = { newValue -> onValueChanged.invoke(newValue) },
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            trailingIcon = { CloseIcon(textValue, onValueChanged) },
        )
    }
}

@Composable
fun LKNotesTextField(
    textValue: String,
    fontFamily: FontFamily,
    modifier: Modifier = Modifier,
    onValueChanged: (String) -> Unit,
    onDoneCallback: () -> Boolean? = { null },
) {
    Column {
        Text(
            text = "Notes",
            modifier = Modifier.fillMaxWidth().padding(start = 24.dp, top = 8.dp, bottom = 4.dp),
            textAlign = TextAlign.Start,
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = fontFamily,
        )
        TextField(
            modifier = Modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp),
            value = textValue,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onDoneCallback.invoke()
                },
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
            onValueChange = { newValue -> onValueChanged.invoke(newValue) },
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            trailingIcon = { CloseIcon(textValue, onValueChanged) },
        )
    }
}

@Composable
fun LKWebsiteAddressTextField(
    textValue: String,
    fontFamily: FontFamily,
    modifier: Modifier = Modifier,
    onValueChanged: (String) -> Unit,
    onNextCallback: () -> Boolean? = { null },
) {
    Column {
        Text(
            text = "Website Address",
            modifier = Modifier.fillMaxWidth().padding(start = 24.dp, top = 8.dp, bottom = 4.dp),
            textAlign = TextAlign.Start,
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = fontFamily,
        )
        TextField(
            modifier = Modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp),
            value = textValue,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    onNextCallback.invoke()
                },
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
            onValueChange = { newValue -> onValueChanged.invoke(newValue) },
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            trailingIcon = { CloseIcon(textValue, onValueChanged) },
        )
    }
}

@Composable
fun LKUserNameTextField(
    textValue: String,
    fontFamily: FontFamily,
    modifier: Modifier = Modifier,
    onValueChanged: (String) -> Unit,
    textLength: Int = 0,
    maxLength: Int = 18,
) {
    Column {
        Text(
            text = "Username",
            modifier = Modifier.fillMaxWidth().padding(start = 24.dp, top = 8.dp, bottom = 4.dp),
            textAlign = TextAlign.Start,
            color = Color.White,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            fontFamily = fontFamily,
        )
        TextField(
            modifier = Modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp),
            value = textValue,
            colors =
            TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                backgroundColor = Jaguar,
                cursorColor = Color.Black,
                disabledLabelColor = Jaguar,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            onValueChange = { newValue ->
                if (newValue.length <= maxLength) {
                    onValueChanged.invoke(newValue)
                }
            },
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            trailingIcon = { CloseIcon(textValue, onValueChanged) },
        )
        MaxLengthText(text = "$textLength / $maxLength")
    }
}

@Composable
fun LKPasswordTextField(
    textValue: String,
    fontFamily: FontFamily,
    modifier: Modifier = Modifier,
    onValueChanged: (String) -> Unit,
    textLength: Int = 0,
    maxLength: Int = 10,
) {
    Column {
        val lightBlue = Color(0xffd8e6ff)

        Text(
            text = "Username",
            modifier = Modifier.fillMaxWidth().padding(start = 24.dp, top = 8.dp, bottom = 4.dp),
            textAlign = TextAlign.Start,
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = fontFamily,
        )
        TextField(
            modifier = Modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp),
            value = textValue,
            colors =
            TextFieldDefaults.textFieldColors(
                backgroundColor = Jaguar,
                cursorColor = Color.White,
                disabledLabelColor = Jaguar,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            onValueChange = { newValue ->
                if (newValue.length <= maxLength) {
                    onValueChanged.invoke(newValue)
                }
            },
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            trailingIcon = { CloseIcon(textValue, onValueChanged) },
        )
        MaxLengthText(text = "$textLength / $maxLength")
    }
}

@Composable
internal fun CloseIcon(textValue: String, onValueChanged: (String) -> Unit) {
    if (textValue.isNotEmpty()) {
        IconButton(onClick = { onValueChanged.invoke("") }) {
            Icon(imageVector = Icons.Outlined.Close, contentDescription = null, tint = Color.White)
        }
    }
}

@Composable
private fun MaxLengthText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Black.copy(alpha = 0.5f),
) {
    Text(
        text = text,
        modifier = modifier.fillMaxWidth().padding(top = 4.dp, end = 24.dp),
        textAlign = TextAlign.End,
        color = color,
    )
}
