package org.sopt.at.domain.usecase

import org.sopt.at.domain.repository.UserRepository
import org.sopt.at.model.UserInfo
import javax.inject.Inject

class PostSignUpUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(userInfo: UserInfo): Result<Boolean> = userRepository.insertUser(userInfo)
}
