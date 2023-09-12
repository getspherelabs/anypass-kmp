package io.spherelabs.lockerkmp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.fontFamilyResource
import io.spherelabs.lockerkmp.components.grid.GridLayout
import io.spherelabs.lockerkmp.ui.addnewpassword.AddNewPasswordScreen
import io.spherelabs.lockerkmp.ui.confirmpassword.ConfirmPassword
import io.spherelabs.lockerkmp.ui.createpassword.CreatePassword
import io.spherelabs.lockerkmp.ui.home.HomeScreen
import io.spherelabs.lockerkmp.ui.onboarding.OnboardingScreen


@Preview
@Composable
fun ButtonPreview() {

}

@Preview
@Composable
fun ButtonPreview3() {
    ConfirmPassword {}
}

@Preview
@Composable
fun ButtonPreview2() {
    HomeScreen {}
}



@Preview
@Composable
fun ButtonPreview4() {
    CreatePassword()
}

@Preview
@Composable
fun ButtonPreview5() {
    AddNewPasswordScreen()
}