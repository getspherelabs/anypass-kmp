import kotlin.test.Test
import kotlin.test.assertEquals

class MasterPasswordReducerTest {

  @Test
  fun `check the build string`() {
    val text = "123"

    val newPassword: String = buildString { if (text.length < 4) append(text + "1") }

    assertEquals(4, newPassword.length)
  }
}
