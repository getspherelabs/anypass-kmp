package passwordhealthimpl.domain

import domain.model.PasswordStats
import domain.repository.PasswordHealthRepository
import domain.usecase.GetPasswordStatsUseCase
import io.spherelabs.common.uuid4
import io.spherelabs.meteorlogger.log
import kotlinx.coroutines.flow.*

class DefaultGetPasswordStatsUseCase(
    private val passwordHealthRepository: PasswordHealthRepository,
) : GetPasswordStatsUseCase {

  override fun execute(): Flow<List<PasswordStats>> {
    return combine(
        passwordHealthRepository.getTotalPasswords(),
        passwordHealthRepository.getSizeOfStrongPasswords(),
        passwordHealthRepository.getSizeOfWeakPasswords(),
        passwordHealthRepository.getSizeOfReusedPasswords(),
    ) { totalPassword, sizeOfStrongPassword, sizeOfWeakPassword, sizeOfReusedPassword ->
      val result =
          listOf(
              PasswordStats(
                  id = uuid4(),
                  title = "Total Passwords",
                  count = totalPassword,
              ),
              PasswordStats(
                  id = uuid4(),
                  title = "Strong",
                  count = sizeOfStrongPassword,
              ),
              PasswordStats(
                  id = uuid4(),
                  title = "Weak",
                  count = sizeOfWeakPassword,
              ),
              PasswordStats(
                  id = uuid4(),
                  title = "Reused",
                  count = sizeOfReusedPassword,
              ),
          )
      log("Test: Use case is $result")
      result
    }
  }
}
