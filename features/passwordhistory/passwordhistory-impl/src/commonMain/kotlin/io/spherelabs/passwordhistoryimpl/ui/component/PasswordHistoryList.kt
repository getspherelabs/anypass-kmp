package io.spherelabs.passwordhistoryimpl.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.spherelabs.common.formatLongTime
import io.spherelabs.foundation.color.BlackRussian
import io.spherelabs.foundation.color.LavenderBlue
import io.spherelabs.passwordhistoryimpl.presentation.UiPasswordHistory
import io.spherelabs.resource.fonts.GoogleSansFontFamily
import kotlinx.datetime.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PasswordHistoryColumn(
    modifier: Modifier = Modifier,
    passwords: List<UiPasswordHistory>,
    onClearPasswordClick: () -> Unit,
) {

    LazyColumn(
        modifier = modifier.fillMaxWidth().padding(top = 8.dp),
    ) {
        val grouped = passwords.groupBy { it.month }
        grouped.forEach { (month, passwords) ->
            stickyHeader {
                Row(
                    modifier = Modifier.fillMaxWidth().background(BlackRussian)
                        .padding(top = 8.dp, bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        modifier = modifier.padding(start = 24.dp),
                        text = month.name,
                        fontSize = 12.sp,
                        fontFamily = GoogleSansFontFamily,
                        color = Color.White.copy(0.6f),
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                    )

                    Text(
                        modifier = modifier.padding(end = 24.dp).clip(RoundedCornerShape(6.dp))
                            .background(
                                LavenderBlue.copy(0.3f),
                            ).padding(4.dp).clickable {
                                onClearPasswordClick.invoke()
                            },
                        text = "Clear passwords",
                        fontSize = 10.sp,
                        fontFamily = GoogleSansFontFamily,
                        color = Color.White.copy(0.8f),
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Start,
                    )
                }


            }
            item { Spacer(Modifier.height(8.dp)) }

            items(
                passwords,
                key = { it.id },
            ) { password ->
                PasswordHistoryRow(
                    password = password.password,
                    createAt = password.createdAt.formatLongTime(),
                )
            }
        }
    }
}

