package io.spherelabs.admob

import androidx.compose.ui.Modifier
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView as AndroidBannerView
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import io.spherelabs.system.ui.shimmer.shimmer


@SuppressLint("MissingPermission")
@Composable
actual fun GADBannerView(
    adId: String,
    modifier: Modifier,
    onAdLoaded: (loaded: Boolean) -> Unit,
) {
    val context = LocalContext.current
    val bannerView: AndroidBannerView = remember { AndroidBannerView(context) }
    var adLoaded by remember { mutableStateOf(false) }

    DisposableEffect(adId) {
        onDispose {
            bannerView.destroy()
        }
    }

    val bannerListener = rememberBannerAdListener(
        onAdFailedToLoad = {},
        onAdLoaded = { adLoaded = true },
    )

    val currentAdSize = remember {
        AdSize.BANNER
    }

    val adRequest = AdRequest.Builder().build()

    Box(modifier = modifier.height(currentAdSize.height.dp).padding(horizontal = 24.dp)) {
        if (!adLoaded) {
            Box(
                Modifier
                    .fillMaxWidth().fillMaxHeight().shimmer()
                    .background(color = Color.White.copy(0.5f)),
            )
        }

        AndroidView(
            factory = {
                bannerView.apply {
                    setAdSize(currentAdSize)
                    adUnitId = adId
                    adListener = bannerListener
                }
            },
            modifier = modifier.fillMaxSize(),
            update = { currentBannerView ->
                currentBannerView.loadAd(adRequest)
            },
        )
    }


}


@Composable
private fun rememberBannerAdListener(
    onAdFailedToLoad: (LoadAdError) -> Unit,
    onAdLoaded: () -> Unit,
) = remember {
    object : AdListener() {
        override fun onAdFailedToLoad(error: LoadAdError) {
            onAdFailedToLoad(error)
        }

        override fun onAdLoaded() {
            super.onAdLoaded()
            onAdLoaded()
        }
    }
}
