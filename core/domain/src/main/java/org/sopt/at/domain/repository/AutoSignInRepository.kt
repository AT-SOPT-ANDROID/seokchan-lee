package org.sopt.at.domain.repository

import kotlinx.coroutines.flow.Flow

interface AutoSignInRepository {
    val autoLogin: Result<Flow<Boolean>>
    suspend fun setAutoLogin(autoLogin: Boolean)
}