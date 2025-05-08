package org.sopt.at.data.local.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestSignInDto(
    @SerialName("loginId")
    val loginId: String,
    @SerialName("password")
    val password: String
)
