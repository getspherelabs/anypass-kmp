package io.spherelabs.admob


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.LocalUIViewController
import androidx.compose.ui.interop.UIKitView
import cocoapods.GoogleMobileAds.GADAdSize
import cocoapods.GoogleMobileAds.GADBannerView as IosBannerView
import cocoapods.GoogleMobileAds.GADRequest
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.cValue

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun GADBannerView(
    adId: String,
    modifier: Modifier,
) {
    val uiViewController = LocalUIViewController.current

    val bannerSize = remember {
        cValue<GADAdSize> {
            GADAdSize.size
        }
    }

    val bannerView = remember {
        IosBannerView(adSize = bannerSize).apply {
            adSize = bannerSize
            adUnitID = adId
            rootViewController = uiViewController
        }
    }

    UIKitView(
        factory = {
            bannerView
        },
        modifier = modifier.fillMaxWidth(),
        update = { _bannerView ->
            _bannerView.loadRequest(GADRequest())
        },
    )

}
