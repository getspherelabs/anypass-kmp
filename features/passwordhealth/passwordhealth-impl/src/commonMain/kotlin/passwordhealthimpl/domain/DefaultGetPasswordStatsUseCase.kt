package passwordhealthimpl.domain

import domain.model.PasswordStats
import domain.repository.PasswordHealthRepository
import domain.usecase.GetPasswordStatsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow

class DefaultGetPasswordStatsUseCase(
    private val passwordHealthRepository: PasswordHealthRepository,
) : GetPasswordStatsUseCase {

    override fun execute(): Flow<PasswordStats> {
        return flow {
            combine(
                passwordHealthRepository.getTotalPasswords(),
                passwordHealthRepository.getSizeOfStrongPasswords(),
                passwordHealthRepository.getSizeOfWeakPasswords(),
                passwordHealthRepository.getSizeOfReusedPasswords(),
            ) { totalPassword, sizeOfStrongPassword, sizeOfWeakPassword, sizeOfReusedPassword ->
                val result = PasswordStats(
                    totalPasswords = totalPassword,
                    sizeOfStrongPasswords = sizeOfStrongPassword,
                    sizeOfWeakPasswords = sizeOfWeakPassword,
                    sizeOfReusedPasswords = sizeOfReusedPassword,
                )
                emit(result)
            }
        }
    }
}
