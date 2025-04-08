package org.sopt.at.domain.usecase

import org.sopt.at.domain.repository.AutoSignInRepository
import javax.inject.Inject

class SetAutoSignInUseCase @Inject constructor(
    private val autoSignInRepository: AutoSignInRepository
) {
    suspend operator fun invoke(autoSignIn: Boolean) = autoSignInRepository.setAutoLogin(autoSignIn)
}