package com.srp.di

import android.content.Context
import com.srp.model.local.preferences.AuthenticationPreference
import com.srp.model.local.preferences.dataStore
import com.srp.model.remote.ApiConfig
import com.srp.model.remote.ApiConfigMl
import com.srp.model.remote.respository.ApiRepository

object Injection {
    fun provideRepository(context: Context): ApiRepository {
        return ApiRepository(
            ApiConfig.getInstance(),
            AuthenticationPreference.getInstance(context.dataStore)
        )
    }

    fun provideRepositoryMl(context: Context): ApiRepository {
        return ApiRepository(
            ApiConfigMl.getInstance(),
            AuthenticationPreference.getInstance(context.dataStore)
        )
    }

}