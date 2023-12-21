package passwordhealthimpl.domain

import domain.model.PasswordHealth
import domain.repository.PasswordHealthRepository
import domain.usecase.GetPasswordHealthUseCase
import io.spherelabs.common.exception.NotAvailableException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class DefaultGetPasswordHealthUseCase(
    private val passwordHealthRepository: PasswordHealthRepository,
) : GetPasswordHealthUseCase {

    override fun execute(): Flow<List<PasswordHealth>> {
        return combine(
            passwordHealthRepository.getStrongPasswords(),
            passwordHealthRepository.getWeakPasswords(),
            passwordHealthRepository.getReusedPasswords(),
        ) { totalPasswords, weakPasswords, reusedPasswords ->
            val result = totalPasswords + weakPasswords + reusedPasswords

            result.takeIf { it.isNotEmpty() } ?: run {
                throw NotAvailableException("Passwords is not existed yet.")
            }
        }
    }
}
