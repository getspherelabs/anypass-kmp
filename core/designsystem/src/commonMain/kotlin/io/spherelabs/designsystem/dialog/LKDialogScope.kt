package io.spherelabs.designsystem.dialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color

interface LKDialogScope {
  val dialogState: LKDialogState
  val dialogButtons: LKDialogButtons

  val callbacks: SnapshotStateMap<Int, () -> Unit>
  val positiveButtonEnabled: SnapshotStateMap<Int, Boolean>

  val autoDismiss: Boolean

  fun submit()

  fun reset()

  @Composable fun PositiveButtonEnabled(valid: Boolean, onDispose: () -> Unit)

  @Composable fun DialogCallback(callback: () -> Unit)
}

internal class LKDialogScopeImpl(
    override val dialogState: LKDialogState,
    override val autoDismiss: Boolean = true
) : LKDialogScope {
  override val dialogButtons = LKDialogButtons(this)

  override val callbacks = mutableStateMapOf<Int, () -> Unit>()
  private val callbackCounter = AtomicInt(0)

  override val positiveButtonEnabled = mutableStateMapOf<Int, Boolean>()
  private val positiveEnabledCounter = AtomicInt(0)

  override fun submit() {
    dialogState.hide()
    callbacks.values.forEach { it() }
  }

  override fun reset() {
    positiveButtonEnabled.clear()
    callbacks.clear()

    positiveEnabledCounter.set(0)
    callbackCounter.set(0)
  }

  @Composable
  override fun PositiveButtonEnabled(valid: Boolean, onDispose: () -> Unit) {
    val positiveEnabledIndex = remember { positiveEnabledCounter.getAndIncrement() }

    DisposableEffect(valid) {
      positiveButtonEnabled[positiveEnabledIndex] = valid
      onDispose {
        positiveButtonEnabled[positiveEnabledIndex] = true
        onDispose()
      }
    }
  }

  @Composable
  override fun DialogCallback(callback: () -> Unit) {
    val callbackIndex = rememberSaveable { callbackCounter.getAndIncrement() }

    DisposableEffect(Unit) {
      callbacks[callbackIndex] = callback
      onDispose { callbacks[callbackIndex] = {} }
    }
  }
}

class LKDialogState(initialValue: Boolean = false) {
  var showing by mutableStateOf(initialValue)

  var dialogBackgroundColor by mutableStateOf<Color?>(null)

  fun show() {
    showing = true
  }

  fun hide(focusManager: FocusManager? = null) {
    focusManager?.clearFocus()
    showing = false
  }

  companion object {
    /** The default [Saver] implementation for [LKDialogState]. */
    fun Saver(): Saver<LKDialogState, *> =
        Saver(save = { it.showing }, restore = { LKDialogState(it) })
  }
}

@Composable
fun useDialogState(initialValue: Boolean = false): LKDialogState {
  return rememberSaveable(saver = LKDialogState.Saver()) { LKDialogState(initialValue) }
}
