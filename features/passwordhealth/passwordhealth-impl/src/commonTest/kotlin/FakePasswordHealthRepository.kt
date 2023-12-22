import domain.model.PasswordHealth
import domain.model.PasswordType
import domain.repository.PasswordHealthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class FakePasswordHealthRepository : PasswordHealthRepository {

    override fun getSizeOfStrongPasswords(): Flow<Int> {
        return flowOf(10)
    }

    override fun getSizeOfWeakPasswords(): Flow<Int> {
        return flowOf(4)
    }

    override fun getTotalPasswords(): Flow<Int> {
        return flowOf(14)
    }

    override fun getSizeOfReusedPasswords(): Flow<Int> {
        return flowOf(2)
    }

    override fun getCurrentPasswordHealth(): Flow<Double> {
        return flowOf(20.0)
    }

    override fun getWeakPasswords(): Flow<List<PasswordHealth>> {
        return flow {
            val weakPasswords = listOf(
                PasswordHealth(
                    id = "2",
                    name = "Behance",
                    email = "test@gmail.com",
                    image = "Behance",
                    type = PasswordType.Weak,
                ),
            )
            emit(weakPasswords)
        }
    }

    override fun getStrongPasswords(): Flow<List<PasswordHealth>> {
        return flow {
            val strongPasswords = listOf(
                PasswordHealth(
                    id = "2",
                    name = "Linkedin",
                    email = "test@gmail.com",
                    image = "Linkedin",
                    type = PasswordType.Strong,
                ),
            )
            emit(strongPasswords)
        }
    }

    override fun getReusedPasswords(): Flow<List<PasswordHealth>> {
        return flow {
            val reusedPasswords = listOf(
                PasswordHealth(
                    id = "3",
                    name = "Behance",
                    email = "test@gmail.com",
                    image = "Behance",
                    type = PasswordType.Reused,
                ),
            )
            emit(reusedPasswords)
        }
    }
}
