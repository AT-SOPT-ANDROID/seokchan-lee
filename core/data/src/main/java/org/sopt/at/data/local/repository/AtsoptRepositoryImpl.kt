package org.sopt.at.data.local.repository

import org.sopt.at.data.local.model.request.SignInRequestDto
import org.sopt.at.data.local.model.request.SignUpRequestDto
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
                SignUpRequestDto(
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
                SignInRequestDto(
                    loginId = signInInfo.loginId,
                    password = signInInfo.password
                )
            ).data.userId
        }

    override suspend fun getMyNickname(userId: Long): Result<String> =
        runCatching {
            atsoptApi.getMyNickname(
                userId = userId
            ).data.nickname
        }

    override suspend fun getUserNickname(keyword: String): Result<List<String>> =
        runCatching {
            atsoptApi.getUserNickname(keyword = keyword).data.nicknameList
        }
}