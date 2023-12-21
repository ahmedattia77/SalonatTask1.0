package com.example.salonattask10.data.local

import androidx.datastore.preferences.core.edit
import com.example.salonattask10.domain.manger.LocalUserManger
import kotlinx.coroutines.flow.Flow
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.salonattask10.Constants
import com.example.salonattask10.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalUserMangerImp(
    private val context: Context
) : LocalUserManger {

    override suspend fun saveAppEntry() {
        context.dataStore.edit { settings ->
            settings[PreferencesKey.APP_ENTRY] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> =
        context.dataStore.data.map { pref ->
            pref[PreferencesKey.APP_ENTRY] ?: false
        }

    override suspend fun saveToken(token: String) {
        context.dataStore.edit { settings ->
            settings[PreferencesKey.TOKEN] = token
        }
    }

    override suspend fun saveCenterId(centerId: String) {
        context.dataStore.edit { settings ->
            settings[PreferencesKey.CENTER_ID] = centerId
        }
    }

    override fun readToken(): Flow<String> =
        context.dataStore.data.map { pref ->
            pref[PreferencesKey.TOKEN] ?: ""
        }

    override fun readCenterId(): Flow<String> =
        context.dataStore.data.map { pref ->
            pref[PreferencesKey.CENTER_ID] ?: ""
        }
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS)

private object PreferencesKey {
    val APP_ENTRY = booleanPreferencesKey(name = Constants.APP_ENTRY)
    val TOKEN = stringPreferencesKey(name = Constants.TOKEN)
    val CENTER_ID = stringPreferencesKey(name = Constants.CENTER_ID)
}
