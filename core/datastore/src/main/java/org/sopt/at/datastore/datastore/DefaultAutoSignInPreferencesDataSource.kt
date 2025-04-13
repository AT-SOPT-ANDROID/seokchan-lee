package org.sopt.at.datastore.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.sopt.at.datastore.source.AutoSignInPreferencesDataSource
import javax.inject.Inject
import javax.inject.Named

class DefaultAutoSignInPreferencesDataSource @Inject constructor(
    @Named("authDataStore") private val dataStore: DataStore<Preferences>
) : AutoSignInPreferencesDataSource {

    object PreferencesKey {
        val AUTO_SIGNIN = booleanPreferencesKey("AUTO_LOGIN")
    }

    override val autoSignIn: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[PreferencesKey.AUTO_SIGNIN] ?: false
    }

    override suspend fun setAutoSignIn(autoLogin: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.AUTO_SIGNIN] = autoLogin
        }
    }
}
