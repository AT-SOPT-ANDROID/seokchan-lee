package org.sopt.at.data.local.repository

import android.util.Log
import org.sopt.at.data.local.model.request.RequestSignInDto
import org.sopt.at.data.local.model.request.RequestSignUpDto
import org.sopt.at.data.local.remote.AtsoptApi
import org.sopt.at.domain.repository.AtsoptRepository
import org.sopt.at.model.SignInInfo
import org.sopt.at.model.SignUpInfo
import org.sopt.at.model.SignUpResult
import javax.inject.Inject

class AtsoptRepositoryImpl @Inject constructor(
    private val atsoptApi: AtsoptApi
) : AtsoptRepository {
    override suspend fun postSignUp(singUpInfo: SignUpInfo): Result<SignUpResult> =
        runCatching {
            atsoptApi.postSignUp(
                RequestSignUpDto(
                    loginId = singUpInfo.loginId,
                    password = singUpInfo.password,
                    nickname = singUpInfo.nickname,
                )
            ).data
        }.mapCatching {
            SignUpResult(userId = it.userId, nickname = it.nickname)
        }

    override suspend fun postSignIn(signInInfo: SignInInfo): Result<Long> =
        runCatching {
            atsoptApi.postSignIn(
                RequestSignInDto(
                    loginId = signInInfo.loginId,
                    password = signInInfo.password
                )
            ).data.userId
        }
}