package io.spherelabs.lockerkmp.components.textfield

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.compose.fontFamilyResource
import io.spherelabs.lockerkmp.MR


@Composable
fun TitleTextField(
    textValue: String,
    modifier: Modifier = Modifier,
    onValueChanged: (String) -> Unit
) {
    Column {
        val lightBlue = Color(0xffd8e6ff)
        val blue = Color(0xff76a9ff)

        TextField(
            modifier = modifier.width(240.dp).padding(start = 12.dp, end = 12.dp),
            value = textValue,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = lightBlue,
                cursorColor = Color.Black,
                disabledLabelColor = lightBlue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = { newValue ->
                onValueChanged.invoke(newValue)
            },
            shape = RoundedCornerShape(8.dp),
            singleLine = true
        )
    }
}

@Composable
fun PasswordTextField(
    textValue: String,
    modifier: Modifier = Modifier,
    onValueChanged: (String) -> Unit
) {
    Column {
        val lightBlue = Color(0xffd8e6ff)
        val blue = Color(0xff76a9ff)
        Text(
            text = "Password",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, top = 8.dp, bottom = 4.dp),
            textAlign = TextAlign.Start,
            color = Color.Black,
            fontSize = 18.sp,
            fontFamily = fontFamilyResource(MR.fonts.googlesans.medium)
        )
        TextField(
            modifier = Modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp),
            value = textValue,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = lightBlue,
                cursorColor = Color.Black,
                disabledLabelColor = lightBlue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = { newValue ->
                onValueChanged.invoke(newValue)
            },
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = null)
            },
            trailingIcon = {
                CloseIcon(textValue, onValueChanged)
            }
        )
    }
}

@Composable
fun EmailTextField(
    textValue: String,
    modifier: Modifier = Modifier,
    onValueChanged: (String) -> Unit,
) {
    Column {
        val lightBlue = Color(0xffd8e6ff)
        val blue = Color(0xff76a9ff)
        Text(
            text = "Email",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, bottom = 4.dp),
            textAlign = TextAlign.Start,
            color = Color.Black,
            fontSize = 18.sp,
            fontFamily = fontFamilyResource(MR.fonts.googlesans.medium)
        )
        TextField(
            modifier = Modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp),
            value = textValue,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = lightBlue,
                cursorColor = Color.Black,
                disabledLabelColor = lightBlue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = { newValue ->
                onValueChanged.invoke(newValue)
            },
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            trailingIcon = {
                CloseIcon(textValue, onValueChanged)
            }
        )
    }
}

@Composable
fun NotesTextField(
    textValue: String,
    modifier: Modifier = Modifier,
    onValueChanged: (String) -> Unit,
) {
    Column {
        val lightBlue = Color(0xffd8e6ff)
        val blue = Color(0xff76a9ff)
        Text(
            text = "Notes",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, top = 8.dp,bottom = 4.dp),
            textAlign = TextAlign.Start,
            color = Color.Black,
            fontSize = 18.sp,
            fontFamily = fontFamilyResource(MR.fonts.googlesans.medium)
        )
        TextField(
            modifier = Modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp),
            value = textValue,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = lightBlue,
                cursorColor = Color.Black,
                disabledLabelColor = lightBlue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = { newValue ->
                onValueChanged.invoke(newValue)
            },
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            trailingIcon = {
                CloseIcon(textValue, onValueChanged)
            }
        )
    }
}

@Composable
fun WebsiteAddressTextField(
    textValue: String,
    modifier: Modifier = Modifier,
    onValueChanged: (String) -> Unit,
) {
    Column {
        val lightBlue = Color(0xffd8e6ff)
        val blue = Color(0xff76a9ff)
        Text(
            text = "Website Address",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, top = 8.dp, bottom = 4.dp),
            textAlign = TextAlign.Start,
            color = Color.Black,
            fontSize = 18.sp,
            fontFamily = fontFamilyResource(MR.fonts.googlesans.medium)
        )
        TextField(
            modifier = Modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp),
            value = textValue,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = lightBlue,
                cursorColor = Color.Black,
                disabledLabelColor = lightBlue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = { newValue ->
                onValueChanged.invoke(newValue)
            },
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            trailingIcon = {
                CloseIcon(textValue, onValueChanged)
            }
        )
    }
}

@Composable
fun UserNameTextField(
    textValue: String,
    modifier: Modifier = Modifier,
    onValueChanged: (String) -> Unit,
    textLength: Int = 0,
    maxLength: Int = 18
) {
    Column {
        val lightBlue = Color(0xffd8e6ff)
        val blue = Color(0xff76a9ff)
        Text(
            text = "Username",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, top = 8.dp, bottom = 4.dp),
            textAlign = TextAlign.Start,
            color = Color.Black,
            fontSize = 18.sp,
            fontFamily = fontFamilyResource(MR.fonts.googlesans.medium)
        )
        TextField(
            modifier = Modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp),
            value = textValue,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = lightBlue,
                cursorColor = Color.Black,
                disabledLabelColor = lightBlue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = { newValue ->
                if (newValue.length <= maxLength) {
                    onValueChanged.invoke(newValue)
                }
            },
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            trailingIcon = {
                CloseIcon(textValue, onValueChanged)
            }
        )
        MaxLengthText(text = "$textLength / $maxLength")
    }
}

@Composable
private fun CloseIcon(
    textValue: String,
    onValueChanged: (String) -> Unit
) {
    if (textValue.isNotEmpty()) {
        IconButton(onClick = {
            onValueChanged.invoke("")
        }) {
            Icon(
                imageVector = Icons.Outlined.Close,
                contentDescription = null
            )
        }
    }

}

@Composable
private fun MaxLengthText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Black.copy(alpha = 0.5f)
) {
    Text(
        text = text,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 4.dp, end = 24.dp),
        textAlign = TextAlign.End,
        color = color
    )
}