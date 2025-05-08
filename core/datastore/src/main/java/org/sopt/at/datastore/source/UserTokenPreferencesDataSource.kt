package org.sopt.at.datastore.source

import kotlinx.coroutines.flow.Flow

interface UserTokenPreferencesDataSource {
    val userToken: Flow<Long>
    suspend fun setUserToken(userToken: Long)
}