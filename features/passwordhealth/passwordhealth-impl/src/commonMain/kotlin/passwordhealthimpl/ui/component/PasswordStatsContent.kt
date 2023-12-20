package passwordhealthimpl.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domain.model.PasswordStats
import io.spherelabs.foundation.color.LavenderBlue
import io.spherelabs.resource.fonts.GoogleSansFontFamily

@Composable
fun PasswordStatContent(
    modifier: Modifier = Modifier,
    stats: List<PasswordStats>,
) {
    PasswordStatList(stats = stats)
}

@Composable
fun PasswordStatList(
    modifier: Modifier = Modifier,
    stats: List<PasswordStats>,
) {
    LazyHorizontalGrid(
        modifier = modifier.height(175.dp),
        rows = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(
            stats,
            key = { stat ->
                stat.id
            },
        ) {
            PasswordStatRow(
                modifier,
                it.title,
                it.count,
            )
        }
    }
}

@Composable
fun PasswordStatRow(
    modifier: Modifier,
    title: String,
    count: Int,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = LavenderBlue.copy(alpha = 0.7f),
        ),
        modifier = modifier
            .size(width = 150.dp, height = 45.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Text(
            modifier = modifier.padding(top = 16.dp, start = 24.dp),
            text = title,
            fontSize = 14.sp,
            fontFamily = GoogleSansFontFamily,
            color = Color.White.copy(alpha = 0.5f),
            textAlign = TextAlign.Center,
        )
        Text(
            modifier = modifier.padding(top = 4.dp, start = 24.dp),
            text = "$count",
            fontSize = 18.sp,
            fontFamily = GoogleSansFontFamily,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = Color.White.copy(alpha = 0.9f),
        )
    }

}
