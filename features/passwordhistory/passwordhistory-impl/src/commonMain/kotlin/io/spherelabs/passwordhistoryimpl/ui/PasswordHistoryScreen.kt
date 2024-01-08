package io.spherelabs.passwordhistoryimpl.ui


import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import io.spherelabs.designsystem.button.BackButton
import io.spherelabs.designsystem.fonts.LocalStrings
import io.spherelabs.designsystem.text.Headline
import io.spherelabs.foundation.color.Jaguar
import io.spherelabs.foundation.color.LavenderBlue
import io.spherelabs.resource.fonts.GoogleSansFontFamily
import io.spherelabs.system.ui.randompassword.ColorizedRandomPassword

class PasswordHistoryScreen : Screen {

    @Composable
    override fun Content() {

    }
}

@Composable
internal fun PasswordHistoryTopBar(
    modifier: Modifier = Modifier,
    navigateToBack: () -> Unit,
) {
    val strings = LocalStrings.current

    Row(
        modifier = modifier.fillMaxWidth().padding(top = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BackButton(
            modifier,
            navigateToBack = { navigateToBack.invoke() },
        )
        Headline(
            text = strings.passwordHealth,
            modifier = modifier,
            fontFamily = GoogleSansFontFamily,
            fontWeight = FontWeight.Medium,
            textColor = Color.White,
        )
    }
}


@Composable
fun PasswordHistoryRow(
    modifier: Modifier = Modifier,
    password: String,
    createAd: String,
    isHidden: Boolean,
    onToggleVisibility: () -> Unit,
) {
    val animatedBlur by animateDpAsState(
        targetValue = if (isHidden) 16.dp else 0.dp,
        animationSpec = tween(500, easing = LinearEasing),
    )
    Card(
        colors =
        CardDefaults.cardColors(
            containerColor = Jaguar,
        ),
        modifier =
        modifier.height(95.dp).fillMaxWidth().padding(start = 24.dp, end = 24.dp, top = 8.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Row(
            modifier = modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = modifier) {
                Text(
                    modifier = modifier.padding(start = 24.dp),
                    text = createAd,
                    fontSize = 14.sp,
                    fontFamily = GoogleSansFontFamily,
                    color = Color.White.copy(alpha = 0.5f),
                    textAlign = TextAlign.Center,
                )
                ColorizedRandomPassword(
                    modifier = modifier.blur(
                        animatedBlur,
                        edgeTreatment = BlurredEdgeTreatment.Unbounded,
                    ).clickable {
                        onToggleVisibility.invoke()
                    },
                    password = password,
                    fontSize = 35.sp,
                )
                Text(
                    modifier = modifier.padding(top = 4.dp, start = 24.dp),
                    text = password,
                    fontSize = 18.sp,
                    fontFamily = GoogleSansFontFamily,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = Color.White.copy(alpha = 0.9f),
                )
            }
        }
    }
}
