package org.sopt.at.domain.repository

import org.sopt.at.model.SignUpInfo
import org.sopt.at.model.SignUpResult

interface AtsoptRepository {
    suspend fun postSignUp(singUpInfo: SignUpInfo): Result<SignUpResult>
}