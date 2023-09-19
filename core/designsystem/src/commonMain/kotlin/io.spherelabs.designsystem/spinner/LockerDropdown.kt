package io.spherelabs.designsystem.spinner

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import io.spherelabs.designsystem.hooks.useScroll

@Composable
internal fun DropdownMenuItem(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = LockerDropdownTokens.DropdownMenuItemContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit
) {
    DropdownMenuItemContent(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        content = content
    )
}

@Composable
internal fun DropdownMenuContent(
    expandedStates: MutableTransitionState<Boolean>,
    transformOriginState: MutableState<TransformOrigin>,
    modifier: Modifier = Modifier,
    dropdownStyle: LockerDropdownStyle = LockerDropdownDefaults.dropdownStyle(),
    content: @Composable ColumnScope.() -> Unit
) {
    val transition = updateTransition(expandedStates, "DropDownMenu")
    val elevation = dropdownStyle.elevation()
    val verticalPadding = dropdownStyle.verticalPadding()

    val scale by transition.animateFloat(
        transitionSpec = {
            if (false isTransitioningTo true) {

                tween(
                    durationMillis = LockerDropdownTokens.InTransitionDuration,
                    easing = LinearOutSlowInEasing
                )
            } else {

                tween(
                    durationMillis = 1,
                    delayMillis = LockerDropdownTokens.OutTransitionDuration - 1
                )
            }
        }
    ) {
        if (it) {
            1f
        } else {
            0.8f
        }
    }

    val alpha by transition.animateFloat(
        transitionSpec = {
            if (false isTransitioningTo true) {
                tween(durationMillis = LockerDropdownTokens.InTransitioningDuration)
            } else {
                tween(durationMillis = LockerDropdownTokens.OutTransitionDuration)
            }
        }
    ) {
        if (it) {
            1f
        } else {
            0f
        }
    }
    Card(
        modifier = Modifier.graphicsLayer {
            scaleX = scale
            scaleY = scale
            this.alpha = alpha
            transformOrigin = transformOriginState.value
        },
        elevation = elevation.value
    ) {
        Column(
            modifier = modifier
                .padding(vertical = verticalPadding.value)
                .width(IntrinsicSize.Max)
                .verticalScroll(useScroll()),
            content = content
        )
    }
}

@Composable
internal fun DropdownMenuItemContent(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = LockerDropdownTokens.DropdownMenuItemContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit
) {

    Row(
        modifier = modifier
            .clickable(
                enabled = enabled,
                onClick = onClick,
                interactionSource = interactionSource,
                indication = rememberRipple(true)
            )
            .fillMaxWidth()
            .sizeIn(
                minWidth = LockerDropdownTokens.MenuItemDefaultMinWidth,
                maxWidth = LockerDropdownTokens.MenuItemDefaultMaxWidth,
                minHeight = LockerDropdownTokens.MenuItemDefaultMinHeight
            )
            .padding(contentPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val typography = MaterialTheme.typography
        ProvideTextStyle(typography.subtitle1) {
            val contentAlpha = if (enabled) ContentAlpha.high else ContentAlpha.disabled
            CompositionLocalProvider(LocalContentAlpha provides contentAlpha) {
                content()
            }
        }
    }
}

