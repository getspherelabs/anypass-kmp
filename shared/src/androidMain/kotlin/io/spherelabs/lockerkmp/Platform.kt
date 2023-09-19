package io.spherelabs.lockerkmp

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.spherelabs.designsystem.passwordcard.PasswordCard
import io.spherelabs.lockerkmp.ui.confirmpassword.ConfirmPassword


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
    PasswordCard(
        password = "yesdllkdskoeroer123249lk",
        title = "Behance",
        email = "behzoddev@gmail.com",
    )
}



@Preview
@Composable
fun ButtonPreview4() {
    // GeneratePasswordScreen()
}

@Preview
@Composable
fun ButtonPreview5() {
    //
}