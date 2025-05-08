package org.sopt.at.domain.repository

import org.sopt.at.model.SignInInfo
import org.sopt.at.model.SignUpInfo
import org.sopt.at.model.SignUpResult

interface AtsoptRepository {
    suspend fun postSignUp(singUpInfo: SignUpInfo): Result<SignUpResult>
    suspend fun postSignIn(signInInfo: SignInInfo): Result<Long>
    suspend fun getMyNickname(userId: Long): Result<String>
}