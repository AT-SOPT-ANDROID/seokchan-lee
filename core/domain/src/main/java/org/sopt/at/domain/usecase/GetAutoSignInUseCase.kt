package org.sopt.at.domain.usecase

import org.sopt.at.domain.repository.AutoSignInRepository
import javax.inject.Inject

class GetAutoSignInUseCase @Inject constructor(
    private val autoSignInRepository: AutoSignInRepository
) {
    operator fun invoke() = autoSignInRepository.autoLogin
}
