package org.sopt.at.data.local.repository

import org.sopt.at.database.source.LocalUserDataSource
import org.sopt.at.domain.repository.UserRepository
import org.sopt.at.model.UserInfo
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val localUserDataSource: LocalUserDataSource,
) : UserRepository {

    override fun insertUser(user: UserInfo): Result<Boolean> =
        runCatching { localUserDataSource.insertUser(user = user) }

    override fun getUser(user: UserInfo): Result<Boolean> =
        runCatching { localUserDataSource.getUser(user = user) }

    override fun deleteUser(user: UserInfo): Result<Int> =
        runCatching { localUserDataSource.deleteUser(user = user) }
}