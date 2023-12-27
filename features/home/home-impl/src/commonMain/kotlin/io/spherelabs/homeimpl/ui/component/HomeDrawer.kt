package io.spherelabs.homeimpl.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.CastConnected
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.HelpCenter
import androidx.compose.material.icons.filled.Password
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.compose.painterResource
import io.spherelabs.designsystem.fonts.LocalStrings
import io.spherelabs.designsystem.image.RoundedImage
import io.spherelabs.foundation.color.BlackRussian
import io.spherelabs.resource.fonts.GoogleSansFontFamily
import io.spherelabs.resource.images.MR

@Composable
internal fun HomeDrawer(
    modifier: Modifier = Modifier,
    navigateToMyAccount: () -> Unit,
    navigateToPasswordHealth: () -> Unit,
    navigateToAuthenticator: () -> Unit,
    navigateToGenerator: () -> Unit,
    navigateToHelp: () -> Unit,
) {
  val strings = LocalStrings.current

  Column(
      modifier =
          modifier
              .fillMaxSize()
              .background(color = BlackRussian)
              .padding(start = 8.dp, top = 32.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    LazyColumn(modifier = modifier.weight(1f)) {
      item {
        Row(
            modifier = modifier.padding(start = 24.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
        ) {
          RoundedImage(
              imageSize = 75,
              painter = painterResource(MR.images.avatar),
              contentDescription = null,
          )
        }

        Spacer(modifier.height(16.dp))

        Spacer(
            modifier
                .padding(horizontal = 8.dp)
                .height(1.dp)
                .fillMaxWidth()
                .background(
                    Color.Black.copy(
                        0.5f,
                    ),
                ),
        )

        Spacer(modifier.height(16.dp))
        Row(
            modifier =
                modifier.padding(start = 24.dp).fillMaxWidth().clickable {
                  navigateToMyAccount.invoke()
                },
            horizontalArrangement = Arrangement.Start,
        ) {
          Icon(
              tint = Color.White,
              imageVector = Icons.Default.AccountBox,
              contentDescription = null,
          )
          Spacer(modifier.width(8.dp))
          Text(
              modifier = modifier,
              text = strings.myAccount,
              fontSize = 20.sp,
              color = Color.White,
              fontFamily = GoogleSansFontFamily,
              fontWeight = FontWeight.Bold,
          )
        }
        Spacer(modifier.height(16.dp))

        Row(
            modifier =
                modifier.padding(start = 24.dp).fillMaxWidth().clickable {
                  navigateToPasswordHealth.invoke()
                },
            horizontalArrangement = Arrangement.Start,
        ) {
          Icon(
              tint = Color.White,
              imageVector = Icons.Default.HealthAndSafety,
              contentDescription = null,
          )
          Spacer(modifier.width(8.dp))
          Text(
              modifier = modifier,
              text = strings.passwordHealth,
              fontSize = 20.sp,
              color = Color.White,
              fontFamily = GoogleSansFontFamily,
              fontWeight = FontWeight.Bold,
          )
        }
        Spacer(modifier.height(16.dp))
        Row(
            modifier =
                modifier.padding(start = 24.dp).fillMaxWidth().clickable {
                  navigateToAuthenticator.invoke()
                },
            horizontalArrangement = Arrangement.Start,
        ) {
          Icon(
              imageVector = Icons.Default.CastConnected,
              tint = Color.White,
              contentDescription = null,
          )
          Spacer(modifier.width(8.dp))
          Text(
              modifier = modifier,
              text = strings.authenticator,
              fontSize = 20.sp,
              color = Color.White,
              fontFamily = GoogleSansFontFamily,
              fontWeight = FontWeight.Bold,
          )
        }
        Spacer(modifier.height(16.dp))

        Row(
            modifier =
                modifier.padding(start = 24.dp).fillMaxWidth().clickable {
                  navigateToGenerator.invoke()
                },
            horizontalArrangement = Arrangement.Start,
        ) {
          Icon(
              imageVector = Icons.Default.Password,
              tint = Color.White,
              contentDescription = null,
          )
          Spacer(modifier.width(8.dp))
          Text(
              modifier = modifier,
              text = strings.generator,
              fontSize = 20.sp,
              color = Color.White,
              fontFamily = GoogleSansFontFamily,
              fontWeight = FontWeight.Bold,
          )
        }

        Spacer(modifier.height(16.dp))

        Row(
            modifier =
                modifier.padding(start = 24.dp).fillMaxWidth().clickable {
                  navigateToHelp.invoke()
                },
            horizontalArrangement = Arrangement.Start,
        ) {
          Icon(
              tint = Color.White,
              imageVector = Icons.Default.HelpCenter,
              contentDescription = null,
          )
          Spacer(modifier.width(8.dp))
          Text(
              modifier = modifier,
              text = strings.help,
              fontSize = 20.sp,
              color = Color.White,
              fontFamily = GoogleSansFontFamily,
              fontWeight = FontWeight.Bold,
          )
        }
      }
    }
  }
}
