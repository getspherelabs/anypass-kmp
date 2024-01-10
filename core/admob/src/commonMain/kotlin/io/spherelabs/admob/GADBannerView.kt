package io.spherelabs.admob

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun GADBannerView(
    adId: String,
    modifier: Modifier = Modifier,
    onAdLoaded: (loaded: Boolean) -> Unit
)
