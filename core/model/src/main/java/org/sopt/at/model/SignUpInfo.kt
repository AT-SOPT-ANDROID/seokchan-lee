package org.sopt.at.model

data class SignUpInfo(
    val loginId: String,
    val password: String,
    val nickname: String,
)

data class SignUpResult(
    val userId: Long,
    val nickname: String,
)
