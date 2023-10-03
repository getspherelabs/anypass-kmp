package io.spherelabs.common

import platform.Foundation.NSUUID

actual fun uuid4(): String {
  return  "ios-" + NSUUID().UUIDString
}
