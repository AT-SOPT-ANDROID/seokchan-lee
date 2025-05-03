package org.sopt.at.data.local.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.at.model.SignUpResult

@Serializable
data class ResponseSignUpDto(
    @SerialName("userId")
    val userId: String,
    @SerialName("nickname")
    val nickname: String,
)

fun ResponseSignUpDto.toModel() = SignUpResult(
    userId = userId,
    nickname = nickname,
)
