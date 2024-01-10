import io.spherelabs.passwordhistoryapi.model.PasswordHistory
import io.spherelabs.passwordhistoryapi.usecase.GetAllPasswordHistoryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FakeGetAllPasswordsUseCase : GetAllPasswordHistoryUseCase {

    override fun execute(): Flow<List<PasswordHistory>> {
        return flow {
            val data = listOf(
                PasswordHistory(
                    "1",
                    password = "rerersIUASdsa",
                    createdAt = 3430909012932,
                ),
            )
            emit(
                data,
            )
        }.flowOn(Dispatchers.IO)
    }
}
