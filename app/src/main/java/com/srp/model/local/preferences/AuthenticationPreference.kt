package com.srp.model.local.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "userPreferences")

class AuthenticationPreference private constructor(private val dataStore: DataStore<Preferences>) {

    private val onBoardKey = booleanPreferencesKey("onBoard")
    private val nameKey = stringPreferencesKey("NameKey")
    private val tokenKey = stringPreferencesKey("UserToken")
    private val userIdKey = stringPreferencesKey("userId_key")
    private val emailKey = stringPreferencesKey("EmailKey")

    fun getOnBoardStatus(): Flow<Boolean> {
        return dataStore.data.map { pref ->
            pref[onBoardKey] ?: false
        }
    }

    fun getNameKey(): Flow<String> {
        return dataStore.data.map {
            it[nameKey] ?: ""
        }
    }

    fun getEmailKey(): Flow<String> {
        return dataStore.data.map {
            it[emailKey] ?: ""
        }
    }

    fun getTokenKey(): Flow<String> {
        return dataStore.data.map {
            it[tokenKey] ?: ""
        }
    }

    fun getUserId(): Flow<String> {
        return dataStore.data.map {
            it[userIdKey] ?: ""
        }
    }

    suspend fun savePreferences(
        onBoard: Boolean,
        name: String,
        email: String,
        tokenId: String,
        userId: String
    ) {
        dataStore.edit {
            it[nameKey] = name
            it[emailKey] = email
            it[onBoardKey] = onBoard
            it[tokenKey] = tokenId
            it[userIdKey] = userId
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: AuthenticationPreference? = null

        fun getInstance(dataStore: DataStore<Preferences>): AuthenticationPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = AuthenticationPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }

    }


}