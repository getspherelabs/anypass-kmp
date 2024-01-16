package io.spherelabs.addnewpasswordimpl

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.spherelabs.addnewpasswordapi.model.WebsiteDomain
import io.spherelabs.addnewpasswordimpl.presentation.addnewlogin.AddNewLoginState
import io.spherelabs.addnewpasswordimpl.ui.AddNewLoginContext

@Preview
@Composable
fun AddNewLoginScreenPreview() {
    Surface {
        AddNewLoginContext(uiState =
            AddNewLoginState(
                websites = listOf(
                    WebsiteDomain(
                        name = "Amazon",
                        url = "https://amazon.com/"
                    ),
                    WebsiteDomain(
                        name = "Google",
                        url = "https://google.com/"
                    )
                )
            )
        ){}
    }
}
