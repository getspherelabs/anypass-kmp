package passwordhealthimpl.domain

import domain.repository.PasswordHealthRepository
import domain.usecase.GetPasswordProgressUseCase
import io.spherelabs.meteorlogger.log
import kotlin.math.abs
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map

class DefaultGetPasswordProgressUseCase(
    private val passwordHealthRepository: PasswordHealthRepository,
) : GetPasswordProgressUseCase {

    override fun execute(): Flow<Int> {
        return passwordHealthRepository.getCurrentPasswordHealth().map { abs(it).toInt() }
    }
}
