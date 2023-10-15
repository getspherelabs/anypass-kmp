import assertk.assertThat
import assertk.assertions.isEqualTo
import io.spherelabs.changepassworddomain.repository.ChangePasswordRepository
import io.spherelabs.changepassworddomain.usecase.DefaultSetNewKeyPasswordUseCase
import io.spherelabs.changepassworddomain.usecase.SetNewKeyPasswordUseCase
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlinx.coroutines.test.runTest

class SetNewPasswordUseCaseTest {

    private lateinit var repository: ChangePasswordRepository
    private lateinit var useCase: SetNewKeyPasswordUseCase

    @BeforeTest
    fun setup() {
        repository = FakeChangePasswordRepository()
        useCase = DefaultSetNewKeyPasswordUseCase(repository)
    }

    @Test
    fun `check the set new password use case works properly`() = runTest {
        val arg = "1234"

        val result = runCatching {
            useCase.execute(arg)
        }

        assertThat(result.getOrNull()).isEqualTo(kotlin.Unit)
    }
}
