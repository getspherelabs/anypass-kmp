import io.spherelabs.addnewpassworddomain.model.AddNewPasswordDomain
import io.spherelabs.addnewpassworddomain.usecase.AddNewPassword
import io.spherelabs.addnewpassworddomain.usecase.DefaultAddNewPassword
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class AddNewPasswordTest {

    private lateinit var usecase: AddNewPassword

    @BeforeTest
    fun setup() {
        usecase = DefaultAddNewPassword(
            FakeAddNewRepository()
        )
    }

    @Test
    fun `check the add new password use case is working correctly`() = runTest {
        val result = usecase.execute(
            AddNewPasswordDomain(
                id = "12345",
                title = "Test",
                password = "1234567891012",
                category = ""
            )
        )
        assertTrue(result.isSuccess)
    }


    @Test
    fun `check the add new password use case when the password length is less than 10`() = runTest {
        val result = usecase.execute(
            AddNewPasswordDomain(
                id = "1234",
                category = "",
                password = "1234"
            )
        )
        println(result)
        assertTrue(result.isFailure)
    }
}