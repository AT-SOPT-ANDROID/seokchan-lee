package org.sopt.at.domain.usecase

import kotlinx.coroutines.flow.first
import org.sopt.at.domain.repository.AtsoptRepository
import org.sopt.at.domain.repository.AutoSignInRepository
import javax.inject.Inject

class GetMyNicknameUseCase @Inject constructor(
    private val atsoptRepository: AtsoptRepository,
    private val autoSignInRepository: AutoSignInRepository
) {
    suspend operator fun invoke(): Result<String> {
        val userId = autoSignInRepository.userToken.getOrThrow().first()
        return atsoptRepository.getMyNickname(userId = userId)
    }
}