package org.sopt.at.data.local.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyNicknameResponseDto(
    @SerialName("nickname") val nickname: String
)
