package io.spherelabs.admob


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.LocalUIViewController
import androidx.compose.ui.interop.UIKitView
import cocoapods.GoogleMobileAds.GADBannerView as IosBannerView
import cocoapods.GoogleMobileAds.GADAdSizeBanner
import cocoapods.GoogleMobileAds.GADRequest
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.NSLayoutConstraint
import platform.UIKit.UIView

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun GADBannerView(
    adId: String,
    modifier: Modifier,
    onAdLoaded: (loaded: Boolean) -> Unit
) {
    val uiViewController = LocalUIViewController.current

    val bannerSize = remember {
        GADAdSizeBanner
    }

    val bannerView = remember {
        IosBannerView(adSize = bannerSize).apply {
            adUnitID = adId
            rootViewController = uiViewController
            loadRequest(GADRequest())
        }
    }

    UIKitView(
        factory = {
            val iosView = UIView().apply {
                bannerView.translatesAutoresizingMaskIntoConstraints = false
                addSubview(bannerView)
                NSLayoutConstraint.activateConstraints(listOf(
                    bannerView.topAnchor.constraintEqualToAnchor(topAnchor),
                    bannerView.bottomAnchor.constraintEqualToAnchor(bottomAnchor),
                    bannerView.leadingAnchor.constraintEqualToAnchor(leadingAnchor),
                    bannerView.trailingAnchor.constraintEqualToAnchor(trailingAnchor)
                ))
            }
            iosView
        },
        modifier = modifier.fillMaxWidth(),
    )

}
