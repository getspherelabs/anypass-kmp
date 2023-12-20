package passwordhealthimpl.domain

import domain.model.PasswordHealth
import domain.repository.PasswordHealthRepository
import domain.usecase.GetPasswordHealthUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow

class DefaultGetPasswordHealthUseCase(
    private val passwordHealthRepository: PasswordHealthRepository,
) : GetPasswordHealthUseCase {

    override fun execute(): Flow<List<PasswordHealth>> {
        return flow {
            combine(
                passwordHealthRepository.getStrongPasswords(),
                passwordHealthRepository.getWeakPasswords(),
                passwordHealthRepository.getReusedPasswords(),
            ) { totalPasswords, weakPasswords, reusedPasswords ->
                emit(
                    totalPasswords + weakPasswords + reusedPasswords
                )
            }
        }
    }
}
