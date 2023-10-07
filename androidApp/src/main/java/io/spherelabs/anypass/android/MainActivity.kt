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

    private lateinit var appUpdateManager: AppUpdateManager
    private val updateType = AppUpdateType.IMMEDIATE

    private val UPDATE_REQUEST_CODE = 16

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appUpdateManager = AppUpdateManagerFactory.create(applicationContext)
        checkForAppUpdate()

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

    private fun checkForAppUpdate() {
        appUpdateManager.appUpdateInfo.addOnSuccessListener {
            val isUpdateAvailable = info.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
            val isUpdateAlowed = info.isImmediateUpdateAllowed
            if (isUpdateAvailable && isUpdateAlowed) {
                appUpdateManager.startUpdateFlowForResult(
                    info,
                    updateType,
                    this,
                    UPDATE_REQUEST_CODE
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if(updateType == AppUpdateType.IMMEDIATE) {
            appUpdateManager.appUpdateInfo.addOnSuccessListener {
                if (info.udpateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                    appUpdateManager.startUpdateFlowForResult(
                        info,
                        updateType,
                        this,
                        UPDATE_REQUEST_CODE
                    )
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == UPDATE_REQUEST_CODE) {
            if (resultCode != RESULT_OK) {
                println("Something went wrong updating...")
                finish()
            }
        }
    }
}
