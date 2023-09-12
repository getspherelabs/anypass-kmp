package io.spherelabs.lockerkmp.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.ArrowBackIos
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.fontFamilyResource
import dev.icerock.moko.resources.compose.stringResource
import io.spherelabs.lockerkmp.MR
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.style.TextAlign

@Composable
fun NewItemButton(
    modifier: Modifier = Modifier,
    onNewItemClick: () -> Unit
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(MR.colors.white)
        ),
        shape = RoundedCornerShape(16.dp),
        onClick = {
            onNewItemClick.invoke()
        }) {
        Text(
            text = stringResource(MR.strings.new_item),
            color = colorResource(MR.colors.black),
            fontSize = 14.sp,
            fontFamily = fontFamilyResource(MR.fonts.hiraginosans.medium),
        )

        Icon(
            modifier = modifier.size(14.dp),
            imageVector = Icons.Default.Add,
            contentDescription = null,
            tint = colorResource(MR.colors.black)
        )

    }
}


@Composable
fun BackButton(
    text: String,
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit
) {
    Box(
        modifier = modifier
            .width(75.dp)
            .height(32.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(color = colorResource(MR.colors.dynamic_yellow))
    ) {
        Row(
            modifier = modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = modifier.size(12.dp),
                imageVector = Icons.Outlined.ArrowBackIos, contentDescription = null
            )
            Spacer(modifier = modifier.width(4.dp))
            Text("Back", fontSize = 12.sp)
        }
    }
}

@Composable
fun UseButton(
    text: String,
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit
) {
    Box(
        modifier = modifier
            .width(75.dp)
            .height(32.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(color = colorResource(MR.colors.dynamic_yellow))
    ) {
        Row(
            modifier = modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Use", fontSize = 12.sp)
            Spacer(modifier = modifier.width(4.dp))
            Icon(
                modifier = modifier.size(12.dp),
                imageVector = Icons.Outlined.ArrowForwardIos, contentDescription = null
            )
        }
    }
}


@Composable
fun NumberButton(
    value: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(75.dp)
            .clickable { onClick.invoke() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = value,
            color = Color.White,
            style = MaterialTheme.typography.h4,
            fontFamily = fontFamilyResource(MR.fonts.googlesans.medium)
        )
    }
}