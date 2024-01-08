import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import io.spherelabs.passwordhistoryapi.usecase.ClearAllPasswordHistoryUseCase
import io.spherelabs.passwordhistoryapi.usecase.GetAllPasswordHistoryUseCase
import io.spherelabs.passwordhistoryimpl.presentation.PasswordHistoryMiddleware
import io.spherelabs.passwordhistoryimpl.presentation.PasswordHistoryReducer
import io.spherelabs.passwordhistoryimpl.presentation.PasswordHistoryViewModel
import io.spherelabs.passwordhistoryimpl.presentation.PasswordHistoryWish
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.*

@OptIn(ExperimentalCoroutinesApi::class)
class PasswordHistoryViewModelTest {

    private lateinit var viewModel: PasswordHistoryViewModel
    private lateinit var clearAllPasswordHistoryUseCase: ClearAllPasswordHistoryUseCase
    private lateinit var getAllPasswordHistoryUseCase: GetAllPasswordHistoryUseCase

    private val mainDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @BeforeTest
    fun setup() {
        clearAllPasswordHistoryUseCase = FakeClearPasswordHistoryUseCase()
        getAllPasswordHistoryUseCase = FakeGetAllPasswordsUseCase()
        Dispatchers.setMain(mainDispatcher)
        viewModel = PasswordHistoryViewModel(
            passwordHistoryReducer = PasswordHistoryReducer(),
            passwordHistoryMiddleware = PasswordHistoryMiddleware(
                clearAllPasswordHistoryUseCase = clearAllPasswordHistoryUseCase,
                getAllPasswordHistoryUseCase = getAllPasswordHistoryUseCase,
            ),
        )
    }

    @Test
    fun `GIVEN loaded WHEN clear history THEN checks the history is empty`() = runTest {
        viewModel.wish(PasswordHistoryWish.StartLoadingPasswordHistory)
        viewModel.wish(PasswordHistoryWish.OnClearPasswordHistory)

        viewModel.state.test {
            val result = awaitItem()

            assertThat(0).isEqualTo(result.history.size)
        }
    }


    @Test
    fun `GIVEN hidden password WHEN click toggle THEN change the password visibility`() = runTest {
        viewModel.wish(PasswordHistoryWish.OnToggleVisibility)

        viewModel.state.test {
            val isHidden = awaitItem().isPasswordHidden

            assertThat(true).isEqualTo(isHidden)
        }
    }


    @Test
    fun `GIVEN starts history WHEN loaded THEN checks the size`() = runTest() {
        viewModel.wish(PasswordHistoryWish.StartLoadingPasswordHistory)

        viewModel.state.test {
            println("new state is ${awaitItem()}")
            assertThat(1).isEqualTo(awaitItem().history.size)
        }
    }

    @AfterTest
    fun teardown() {
        Dispatchers.resetMain()
    }

}
