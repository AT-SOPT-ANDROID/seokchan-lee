package org.sopt.at.data.local.datasource

import org.sopt.at.model.UserInfo

interface LocalUserDataSource {
    fun insertUser(user: UserInfo): Boolean
    fun getUser(user: UserInfo): Boolean
    fun deleteUser(user: UserInfo): Int
}