package org.sopt.at.datastore.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.sopt.at.datastore.source.UserTokenPreferencesDataSource
import javax.inject.Inject
import javax.inject.Named

class DefaultUserTokenPreferencesDataSource @Inject constructor(
    @Named("tokenDataStore") private val dataStore: DataStore<Preferences>,
) : UserTokenPreferencesDataSource {

    override val userToken: Flow<Long> = dataStore.data.map { preferences ->
        preferences[PreferencesKey.USER_TOKEN] ?: 0
    }

    override suspend fun setUserToken(userToken: Long) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.USER_TOKEN] = userToken
        }
    }


    object PreferencesKey {
        val USER_TOKEN = longPreferencesKey("USER_TOKEN")
    }
}
