import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEmpty
import io.spherelabs.changepassworddomain.repository.ChangePasswordRepository
import io.spherelabs.changepassworddomain.usecase.DefaultGetCurrentKeyPasswordUseCase
import io.spherelabs.changepassworddomain.usecase.GetCurrentKeyPasswordUseCase
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlinx.coroutines.test.runTest

class GetCurrentKeyPasswordUseCaseTest {

    private lateinit var repository: ChangePasswordRepository
    private lateinit var getCurrentKeyPasswordUseCase: GetCurrentKeyPasswordUseCase

    @BeforeTest
    fun setup() {
        repository = FakeChangePasswordRepository()
        getCurrentKeyPasswordUseCase = DefaultGetCurrentKeyPasswordUseCase(repository)
    }

    @Test
    fun `check the get current key password use case returns value`() = runTest {
        val result = getCurrentKeyPasswordUseCase.execute()

        assertThat(result).isNotEmpty()
        assertThat(result).isEqualTo("1234")
    }
}
