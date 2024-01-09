package io.spherelabs.passwordhistoryimpl.ui.component


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.spherelabs.foundation.color.Jaguar
import io.spherelabs.resource.fonts.GoogleSansFontFamily
import io.spherelabs.system.ui.randompassword.ColorizedRandomPassword

@Composable
fun PasswordHistoryRow(
    modifier: Modifier = Modifier,
    password: String,
    createAt: String,
) {
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
                    text = createAt,
                    fontSize = 14.sp,
                    fontFamily = GoogleSansFontFamily,
                    color = Color.White.copy(alpha = 0.5f),
                    textAlign = TextAlign.Center,
                )
                ColorizedRandomPassword(
                    modifier = modifier.padding(top = 4.dp, start = 24.dp),
                    password = password,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}
