package org.sopt.at.data.local.repository

import org.sopt.at.data.local.model.request.toDto
import org.sopt.at.data.local.model.response.toModel
import org.sopt.at.data.local.remote.AtsoptApi
import org.sopt.at.domain.repository.AtsoptRepository
import org.sopt.at.model.SignUpInfo
import org.sopt.at.model.SignUpResult
import javax.inject.Inject


class AtsoptRepositoryImpl @Inject constructor(
    private val atsoptApi: AtsoptApi
) : AtsoptRepository {
    override suspend fun postSignUp(singUpInfo: SignUpInfo): Result<SignUpResult> =
        runCatching { atsoptApi.postSignUp(singUpInfo.toDto()).toModel() }
}