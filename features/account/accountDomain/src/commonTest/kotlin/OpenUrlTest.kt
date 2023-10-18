import io.spherelabs.accountdomain.usecase.OpenUrlUseCase
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.test.runTest

class OpenUrlTest {

  private lateinit var openUrlUseCase: OpenUrlUseCase

  @BeforeTest
  fun setup() {
    openUrlUseCase = FakeOpenUrlUseCase()
  }

  @Test
  fun `check the open url is working properly`() = runTest {
    kotlin
      .runCatching { openUrlUseCase.execute(url = "") }
      .onFailure { assertEquals("Url is empty.", it.message) }
  }
}
