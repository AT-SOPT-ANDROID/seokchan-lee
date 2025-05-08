package org.sopt.at.domain.usecase

import org.sopt.at.domain.repository.AtsoptRepository
import org.sopt.at.model.SignInInfo
import org.sopt.at.model.SignUpInfo
import org.sopt.at.model.UserInfo
import javax.inject.Inject

class PostSignInUseCase @Inject constructor(
    private val atsoptRepository: AtsoptRepository
) {
    suspend operator fun invoke(signInInfo: SignInInfo): Result<Long> =
        atsoptRepository.postSignIn(signInInfo)
}
