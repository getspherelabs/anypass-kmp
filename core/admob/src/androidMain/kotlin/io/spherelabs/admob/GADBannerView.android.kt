package io.spherelabs.admob

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView as AndroidBannerView
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest

@SuppressLint("MissingPermission")
@Composable
actual fun GADBannerView(
    adId: String,
    modifier: Modifier,
) {
    val context = LocalContext.current

    val currentAdSize = remember {
        AdSize.BANNER
    }

    val adRequest = AdRequest.Builder().build()

    val bannerView = remember {
        AndroidBannerView(context).apply {
            setAdSize(currentAdSize)
            adUnitId = adId
        }
    }


    AndroidView(
        factory = {
            bannerView
        },
        modifier = modifier.fillMaxWidth(),
        update = { currentBannerView ->
            currentBannerView.loadAd(adRequest)
        },
    )
}
