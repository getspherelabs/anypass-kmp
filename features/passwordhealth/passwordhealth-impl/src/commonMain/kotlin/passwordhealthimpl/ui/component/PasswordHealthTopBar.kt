package passwordhealthimpl.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.spherelabs.designsystem.fonts.LocalStrings
import io.spherelabs.designsystem.text.Headline
import io.spherelabs.foundation.color.LavenderBlue
import io.spherelabs.resource.fonts.GoogleSansFontFamily

@Composable
internal fun PasswordHealthTopBar(
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
            navigateToBack = {
                navigateToBack.invoke()
            },
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
fun RowScope.BackButton(
    modifier: Modifier,
    backgroundColor: Color = LavenderBlue.copy(0.7f),
    iconColor: Color = Color.White,
    navigateToBack: () -> Unit,
) {

    Box(
        modifier = modifier.padding(start = 24.dp).size(42.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = backgroundColor)
            .clickable { navigateToBack.invoke() },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            tint = iconColor,
            contentDescription = "Back",
        )
    }
}
