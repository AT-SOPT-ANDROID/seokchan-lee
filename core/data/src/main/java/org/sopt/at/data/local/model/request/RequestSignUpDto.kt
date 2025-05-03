package org.sopt.at.data.local.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.at.model.SignUpInfo

@Serializable
data class RequestSignUpDto(
    @SerialName("loginId")
    val loginId: String,
    @SerialName("password")
    val password: String,
    @SerialName("nickname")
    val nickname: String,
)

fun SignUpInfo.toDto() = RequestSignUpDto(
    loginId = loginId,
    password = password,
    nickname = nickname,
)
