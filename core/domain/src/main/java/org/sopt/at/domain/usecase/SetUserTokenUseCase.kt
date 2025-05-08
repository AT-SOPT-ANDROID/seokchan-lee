package org.sopt.at.domain.usecase

import org.sopt.at.domain.repository.AutoSignInRepository
import javax.inject.Inject

class SetUserTokenUseCase @Inject constructor(
    private val autoSignInRepository: AutoSignInRepository
) {
    suspend operator fun invoke(userToken: Long) =
        autoSignInRepository.setUserToken(userToken)
}
