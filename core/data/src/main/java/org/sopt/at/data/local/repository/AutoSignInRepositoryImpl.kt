package org.sopt.at.data.local.repository

import kotlinx.coroutines.flow.Flow
import org.sopt.at.datastore.datastore.AutoSignInPreferencesDataSource
import org.sopt.at.domain.repository.AutoSignInRepository
import javax.inject.Inject

class AutoSignInRepositoryImpl @Inject constructor(
    private val autoSignInPreferencesDataSource: AutoSignInPreferencesDataSource,
) : AutoSignInRepository {

    override val autoLogin: Result<Flow<Boolean>> =
        runCatching { autoSignInPreferencesDataSource.autoSignIn }

    override suspend fun setAutoLogin(autoLogin: Boolean) {
        autoSignInPreferencesDataSource.setAutoSignIn(autoLogin)
    }
}