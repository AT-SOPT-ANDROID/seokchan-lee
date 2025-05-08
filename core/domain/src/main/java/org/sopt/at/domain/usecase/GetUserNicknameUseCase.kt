package org.sopt.at.domain.usecase

import org.sopt.at.domain.repository.AtsoptRepository
import javax.inject.Inject

class GetUserNicknameUseCase @Inject constructor(
    private val atsoptRepository: AtsoptRepository
) {
    suspend operator fun invoke(keyword: String) =
        atsoptRepository.getUserNickname(keyword = keyword)
}