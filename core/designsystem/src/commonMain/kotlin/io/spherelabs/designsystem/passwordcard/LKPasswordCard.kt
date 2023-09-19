package io.spherelabs.designsystem.passwordcard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material.icons.filled.BlurCircular
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LKPasswordCard(
    password: String,
    email: String,
    title: String,
    icon: Painter,
    modifier: Modifier = Modifier,
    passwordCardStyle: LKPasswordCardStyle = LKPasswordCardDefaults.passwordCardStyle(),
    passwordCardColor: LKPasswordCardColor = LKPasswordCardDefaults.passwordCardColor(),
    contentDescription: String? = null
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(400.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color = passwordCardColor.backgroundColor().value)
    ) {
        Spacer(modifier.height(24.dp))

        Row(
            modifier = modifier.fillMaxWidth().padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = modifier.size(56.dp).clip(RoundedCornerShape(16.dp)),
                painter = icon,
                contentDescription = contentDescription
            )
            Spacer(modifier.width(16.dp))
            Column(modifier = modifier) {
                Text(
                    text = title,
                    color = passwordCardColor.titleColor().value,
                    fontFamily = passwordCardStyle.titleFontFamily().value,
                    fontWeight = FontWeight.Bold,
                    fontSize = passwordCardStyle.titleFontSize().value
                )
                Spacer(modifier.height(4.dp))
                Text(
                    text = email,
                    color = passwordCardColor.emailColor().value,
                    fontFamily = passwordCardStyle.emailFontFamily().value,
                    fontSize = passwordCardStyle.emailFontSize().value
                )
            }
        }

        Spacer(modifier.height(24.dp))

        Text(
            modifier = modifier.fillMaxWidth().padding(horizontal = 24.dp),
            text = password,
            fontFamily = passwordCardStyle.passwordFontFamily().value,
            fontSize = passwordCardStyle.passwordFontSize().value,
            color = passwordCardColor.passwordColor().value
        )
        Spacer(modifier.weight(1f))
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Box(
                modifier = modifier.size(56.dp).clip(CircleShape).background(color = Color.White),
                contentAlignment = Alignment.Center
            ) {
                Image(imageVector = Icons.Default.Delete, contentDescription = null)
            }

            Box(
                modifier = modifier
                    .height(52.dp)
                    .width(80.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(color = Color.White),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Copy",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = passwordCardStyle.copyFontFamily().value
                    )
                    Image(imageVector = Icons.Default.ArrowOutward, contentDescription = null)
                }
            }

        }

        Spacer(modifier.height(24.dp))

    }
}