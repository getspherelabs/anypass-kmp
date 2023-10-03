package io.spherelabs.common

import java.util.UUID

public actual fun uuid4(): String {
  return "android-" + UUID.randomUUID().toString()
}
