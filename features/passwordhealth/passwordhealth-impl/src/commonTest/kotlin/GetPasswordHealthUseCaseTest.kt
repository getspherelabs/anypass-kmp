import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.contains
import domain.model.PasswordHealth
import domain.model.PasswordType
import domain.repository.PasswordHealthRepository
import domain.usecase.GetPasswordHealthUseCase
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.runTest
import passwordhealthimpl.domain.DefaultGetPasswordHealthUseCase
//
//class GetPasswordHealthUseCaseTest {
//
//    private lateinit var passwordHealthRepository: PasswordHealthRepository
//    private lateinit var useCase: GetPasswordHealthUseCase
//
//    @BeforeTest
//    fun setup() {
//        passwordHealthRepository = FakePasswordHealthRepository()
//        useCase = DefaultGetPasswordHealthUseCase(passwordHealthRepository)
//    }
//
//    @Test
//    fun `check the getting password health response`() = runTest {
//        // Given
//        val strongPasswords = listOf(
//            PasswordHealth(
//                id = "2",
//                name = "Linkedin",
//                email = "test@gmail.com",
//                image = "Linkedin",
//                type = PasswordType.Strong,
//            ),
//        )
//        val weakPasswords = listOf(
//            PasswordHealth(
//                id = "2",
//                name = "Behance",
//                email = "test@gmail.com",
//                image = "Behance",
//                type = PasswordType.Weak,
//            ),
//        )
//        val reusedPasswords = listOf(
//            PasswordHealth(
//                id = "3",
//                name = "Behance",
//                email = "test@gmail.com",
//                image = "Behance",
//                type = PasswordType.Reused,
//            ),
//        )
//
//        // Then
//        val result = useCase.execute()
//        result.co
//
//    }
//}
