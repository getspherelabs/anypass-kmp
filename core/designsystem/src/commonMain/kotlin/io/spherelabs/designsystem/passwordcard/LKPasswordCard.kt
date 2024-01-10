package io.spherelabs.designsystem.passwordcard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LKPasswordCard(
    password: @Composable () -> Unit,
    email: String,
    title: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onCopyClick: () -> Unit,
    onToggleVisibility: () -> Unit,
    passwordCardStyle: LKPasswordCardStyle = LKPasswordCardDefaults.passwordCardStyle(),
    passwordCardColor: LKPasswordCardColor = LKPasswordCardDefaults.passwordCardColor(),
    contentDescription: String? = null,
) {
    Column(
        modifier =
        modifier
            .fillMaxWidth()
            .height(400.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color = passwordCardColor.backgroundColor().value),
    ) {
        Spacer(modifier.height(24.dp))

        Row(
            modifier = modifier.fillMaxWidth().padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = modifier.size(56.dp).clip(RoundedCornerShape(16.dp)),
                imageVector = icon,
                contentDescription = contentDescription,
            )
            Spacer(modifier.width(16.dp))
            Column(modifier = modifier) {
                Text(
                    text = title,
                    color = passwordCardColor.titleColor().value,
                    fontFamily = passwordCardStyle.titleFontFamily().value,
                    fontWeight = FontWeight.Medium,
                    fontSize = passwordCardStyle.titleFontSize().value,
                )
                Spacer(modifier.height(4.dp))
                Text(
                    text = email,
                    color = passwordCardColor.emailColor().value,
                    fontFamily = passwordCardStyle.emailFontFamily().value,
                    fontSize = passwordCardStyle.emailFontSize().value,
                    fontWeight = FontWeight.Medium,
                )
            }
        }

        Spacer(modifier.height(24.dp))
        Row(
            modifier = modifier.fillMaxWidth().padding(horizontal = 24.dp),
        ) { password() }

        Spacer(modifier.weight(1f))
        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            Box(
                modifier = modifier.size(56.dp).clip(CircleShape).background(color = Color.White),
                contentAlignment = Alignment.Center,
            ) {
                Image(imageVector = Icons.Default.Delete, contentDescription = null)
            }

            Box(
                modifier =
                modifier
                    .height(52.dp)
                    .width(80.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(color = Color.White),
                contentAlignment = Alignment.Center,
            ) {
                Row(
                    modifier = modifier.fillMaxSize().clickable { onCopyClick.invoke() },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = "Copy",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = passwordCardStyle.copyFontFamily().value,
                    )
                    Image(imageVector = Icons.Default.ArrowOutward, contentDescription = null)
                }
            }
        }

        Spacer(modifier.height(24.dp))
    }
}
