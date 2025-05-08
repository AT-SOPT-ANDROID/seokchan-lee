package org.sopt.at.data.local.repository

import kotlinx.coroutines.flow.Flow
import org.sopt.at.datastore.source.AutoSignInPreferencesDataSource
import org.sopt.at.datastore.source.UserTokenPreferencesDataSource
import org.sopt.at.domain.repository.AutoSignInRepository
import javax.inject.Inject

class AutoSignInRepositoryImpl @Inject constructor(
    private val autoSignInPreferencesDataSource: AutoSignInPreferencesDataSource,
    private val userTokenPreferencesDataSource: UserTokenPreferencesDataSource,
) : AutoSignInRepository {

    override val autoLogin: Result<Flow<Boolean>> =
        runCatching { autoSignInPreferencesDataSource.autoSignIn }

    override suspend fun setAutoLogin(autoLogin: Boolean) {
        autoSignInPreferencesDataSource.setAutoSignIn(autoLogin)
    }

    override val userToken: Result<Flow<Long>> =
        runCatching { userTokenPreferencesDataSource.userToken }

    override suspend fun setUserToken(userToken: Long) {
        userTokenPreferencesDataSource.setUserToken(userToken)
    }
}
