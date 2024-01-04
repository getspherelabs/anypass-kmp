package io.spherelabs.admob


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.LocalUIViewController
import androidx.compose.ui.interop.UIKitView
import cocoapods.GoogleMobileAds.GADAdSize
import cocoapods.GoogleMobileAds.GADBannerView as IosBannerView
import cocoapods.GoogleMobileAds.GADCurrentOrientationInlineAdaptiveBannerAdSizeWithWidth
import cocoapods.GoogleMobileAds.GADRequest
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.cValue
import platform.UIKit.UIView

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
                NSLayoutConstraint.activate(listOf(
                    label.topAnchor.constraintEqualToAnchor(topAnchor),
                    label.bottomAnchor.constraintEqualToAnchor(bottomAnchor),
                    label.leadingAnchor.constraintEqualToAnchor(leadingAnchor),
                    label.trailingAnchor.constraintEqualToAnchor(trailingAnchor)
                ))
            }
            iosView
        },
        modifier = modifier.fillMaxWidth(),
    )

}
