package io.spherelabs.anypass.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentActivity
import io.spherelabs.anypass.MainView
import io.spherelabs.anypass.ui.authenticator.AuthenticatorCard
import io.spherelabs.authenticatordomain.model.RealTimeOtpDomain

class MainActivity : FragmentActivity() {

    private val inAppUpdate = InAppUpdate(activity = this, onUpdateFailed = { finish() })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkInAppUpdate()
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
        resumeInAppUpdate()
    }

    private fun checkInAppUpdate() {
        inAppUpdate.checkForUpdate()
    }

    private fun resumeInAppUpdate() {
        inAppUpdate.onResume()
    }

}

@Preview
@Composable
fun AuthenticatorCardPreview() {
    Surface {
        Column(modifier = Modifier.fillMaxSize()) {
            AuthenticatorCard(
                token = "232323232",
                serviceName = "Behance",
                info = "It is test",
                time = "15s",
                realTimeOtpDomain = RealTimeOtpDomain(12,"")
            )
        }

    }

}
