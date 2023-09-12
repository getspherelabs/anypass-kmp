package io.spherelabs.lockerkmp.ui.addnewpassword

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ImageSearch
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.ArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.fontFamilyResource
import dev.icerock.moko.resources.compose.painterResource
import io.spherelabs.lockerkmp.components.Headline
import io.spherelabs.lockerkmp.MR
import io.spherelabs.lockerkmp.components.NewItemButton
import io.spherelabs.lockerkmp.components.RoundedImage
import io.spherelabs.lockerkmp.components.textfield.EmailTextField
import io.spherelabs.lockerkmp.components.textfield.NotesTextField
import io.spherelabs.lockerkmp.components.textfield.PasswordTextField
import io.spherelabs.lockerkmp.components.textfield.TitleTextField
import io.spherelabs.lockerkmp.components.textfield.UserNameTextField
import io.spherelabs.lockerkmp.components.textfield.WebsiteAddressTextField

@Composable
fun AddNewPasswordRoute() {

}
@Composable
fun AddNewPasswordScreen(
    modifier: Modifier = Modifier
) {
    val state = rememberScrollState(0)

    var textState by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    var websiteAddress by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize().background(color = Color.White).verticalScroll(state)
    ) {
        Headline(
            text = "Add new password",
            textColor = Color.Black
        )

        Text(
            text = "Add new password to your records",
            fontSize = 16.sp,
            fontFamily = fontFamilyResource(MR.fonts.googlesans.medium),
            color = Color.Black.copy(alpha = 0.5F),
            modifier = modifier.padding(start = 24.dp, top = 8.dp)
        )

        Row(
            modifier = modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp, top = 8.dp),
        ) {
            RoundedImage(
                painter = painterResource(MR.images.avatar), contentDescription = null
            )

            TitleTextField(title, modifier, onValueChanged = { newValue ->
                title = newValue
            })

            Box(
                modifier = modifier.size(56.dp).clip(RoundedCornerShape(8.dp)).border(
                    width = 2.dp,
                    color = Color.Black.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(8.dp)
                ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ImageSearch,
                    contentDescription = null
                )
            }
        }


        UserNameTextField(textState, onValueChanged = { newValue ->
            textState = newValue
        }, textLength = textState.length)

        EmailTextField(email, onValueChanged = { newValue ->
            email = newValue
        })

        PasswordTextField(password, onValueChanged = { newValue ->
            password = newValue
        })

        WebsiteAddressTextField(websiteAddress, onValueChanged = { newValue ->
            websiteAddress = newValue
        })

        NotesTextField(notes, onValueChanged = { newValue ->
            notes = newValue
        })

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, bottom = 4.dp, top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Generate password ",
                textAlign = TextAlign.Start,
                color = Color.Black.copy(0.5f),
                fontSize = 16.sp,
                fontFamily = fontFamilyResource(MR.fonts.googlesans.medium)
            )
            Box(
                modifier = modifier.size(16.dp).clip(CircleShape).border(
                    width = 1.dp,
                    color = Color.Black.copy(0.5f),
                    shape = CircleShape
                ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Outlined.ArrowForward,
                    contentDescription = null,
                    tint = Color.Black.copy(0.5f)
                )
            }
        }


        Spacer(modifier = modifier.height(32.dp))

        Button(modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .padding(start = 24.dp, end = 24.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(MR.colors.grey)
            ),
            shape = RoundedCornerShape(24.dp),
            onClick = { /*TODO*/ }) {
            Text(
                text = "Save password",
                color = Color.White,
                fontSize = 18.sp,
                fontFamily = fontFamilyResource(
                    fontResource = MR.fonts.googlesans.medium
                )
            )
        }
    }
}