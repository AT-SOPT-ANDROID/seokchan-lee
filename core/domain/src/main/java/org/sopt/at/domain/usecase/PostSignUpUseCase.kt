package org.sopt.at.domain.usecase

import org.sopt.at.domain.repository.AtsoptRepository
import org.sopt.at.model.SignUpInfo
import org.sopt.at.model.SignUpResult
import javax.inject.Inject

class PostSignUpUseCase @Inject constructor(
    private val atsoptRepository: AtsoptRepository
) {
    suspend operator fun invoke(singUpInfo: SignUpInfo): Result<SignUpResult> =
        atsoptRepository.postSignUp(singUpInfo)
}
