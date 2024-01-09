import io.spherelabs.passwordhistoryapi.usecase.ClearAllPasswordHistoryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class FakeClearPasswordHistoryUseCase: ClearAllPasswordHistoryUseCase {

    override suspend fun execute() {
        withContext(Dispatchers.IO) {
            println("Run the clearing history")
        }
    }
}
