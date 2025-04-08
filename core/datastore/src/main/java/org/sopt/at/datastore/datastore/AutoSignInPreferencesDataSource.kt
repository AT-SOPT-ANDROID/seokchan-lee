package org.sopt.at.datastore.datastore

import kotlinx.coroutines.flow.Flow

interface AutoSignInPreferencesDataSource {
    val autoSignIn: Flow<Boolean>
    suspend fun setAutoSignIn(autoLogin: Boolean)
}