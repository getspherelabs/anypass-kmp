import io.spherelabs.accountdomain.repository.OpenUrl
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.test.runTest

class OpenUrlTest {

  private lateinit var openUrl: OpenUrl

  @BeforeTest
  fun setup() {
    openUrl = FakeOpenUrl()
  }

  @Test
  fun `check the open url is working properly`() = runTest {
    kotlin
      .runCatching { openUrl.execute(url = "") }
      .onFailure { assertEquals("Url is empty.", it.message) }
  }
}
