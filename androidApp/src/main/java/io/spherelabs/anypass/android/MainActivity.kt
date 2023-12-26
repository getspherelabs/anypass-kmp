package io.spherelabs.anypass.android

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import io.spherelabs.anypass.MainView
import io.spherelabs.data.settings.screenshot.RestrictScreenshotSettings
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : FragmentActivity() {

    private val inAppUpdate = InAppUpdate(activity = this, onUpdateFailed = { finish() })
    private val screenshotSettings: RestrictScreenshotSettings by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        checkInAppUpdate()
        setRestrictScreenshot()

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize().safeDrawingPadding(), color = MaterialTheme.colors.background,
                ) {
                    MainView()
                }
            }

        }
    }

    override fun onResume() {
        super.onResume()
        resumeInAppUpdate()
    }

    private fun checkInAppUpdate() {
        inAppUpdate.checkForUpdate()
    }

    private fun resumeInAppUpdate() {
        inAppUpdate.onResume()
    }

    private fun setRestrictScreenshot() {
        lifecycleScope.launch {
            val result = screenshotSettings.getRestrictScreenshot()

            result.collectLatest { isEnabled ->
                if (isEnabled) {
                    window.setFlags(
                        WindowManager.LayoutParams.FLAG_SECURE,
                        WindowManager.LayoutParams.FLAG_SECURE
                    )
                } else {
                    window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
                }
            }
        }
    }
}

