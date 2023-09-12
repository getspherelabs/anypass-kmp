package io.spherelabs.lockerkmp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.spherelabs.lockerkmp.Greeting
import io.spherelabs.lockerkmp.MainView
import io.spherelabs.lockerkmp.ui.LockerApp
import io.spherelabs.lockerkmp.ui.createpassword.CreatePassword
import io.spherelabs.lockerkmp.ui.home.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           MainView()
        }
    }
}
