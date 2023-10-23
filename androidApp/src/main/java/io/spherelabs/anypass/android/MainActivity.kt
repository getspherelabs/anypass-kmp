package io.spherelabs.anypass.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import io.spherelabs.anypass.MainView

class MainActivity : FragmentActivity() {

    private val inAppUpdate = InAppUpdate(activity = this, onUpdateFailed = { finish() })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inAppUpdate.checkForUpdate()
        setContent {
            MaterialTheme() {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    MainView()
                }
            }

        }
    }

    override fun onResume() {
        super.onResume()
        inAppUpdate.onResume()
    }

}
