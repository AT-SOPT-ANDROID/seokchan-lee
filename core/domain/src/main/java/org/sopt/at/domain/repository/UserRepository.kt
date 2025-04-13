package org.sopt.at.domain.repository

import org.sopt.at.model.UserInfo

interface UserRepository {
    fun insertUser(user: UserInfo): Result<Boolean>
    fun getUser(user: UserInfo): Result<Boolean>
    fun deleteUser(user: UserInfo): Result<Int>
}
