package io.spherelabs.designsystem.swiper

import androidx.compose.foundation.lazy.LazyScopeMarker
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.unit.Density

@Stable
@LazyScopeMarker
interface LKCardStackItemScope {

  fun Modifier.dragEnabled(enable: Boolean): Modifier
}

class LKCardStackItemScopeImpl : LKCardStackItemScope {

  override fun Modifier.dragEnabled(enable: Boolean): Modifier {
    return then(DragableEnabledParentData(enable))
  }
}

class DragableEnabledParentData(val isEnabled: Boolean) : ParentDataModifier {

  override fun Density.modifyParentData(parentData: Any?) = this@DragableEnabledParentData
}
