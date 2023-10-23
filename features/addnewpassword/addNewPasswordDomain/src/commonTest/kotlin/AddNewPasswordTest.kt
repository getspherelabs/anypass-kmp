import io.spherelabs.addnewpassworddomain.model.AddNewPasswordDomain
import io.spherelabs.addnewpassworddomain.usecase.AddNewPasswordUseCase
import io.spherelabs.addnewpassworddomain.usecase.DefaultAddNewPasswordUseCaseUseCase
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlinx.coroutines.test.runTest

class AddNewPasswordTest {

    private lateinit var usecase: AddNewPasswordUseCase

    @BeforeTest
    fun setup() {
        usecase = DefaultAddNewPasswordUseCaseUseCase(FakeAddNewRepository())
    }

    @Test
    fun `check the add new password use case is working correctly`() = runTest {
        val result =
            usecase.execute(
                AddNewPasswordDomain(
                    id = "12345",
                    title = "Test",
                    password = "1234567891012",
                    category = "",
                ),
            )
        assertTrue(result.isSuccess)
    }

    @Test
    fun `check the add new password use case when the password length is less than 10`() = runTest {
        val result =
            usecase.execute(AddNewPasswordDomain(id = "1234", category = "", password = "1234"))
        assertTrue(result.isSuccess)
    }
}
