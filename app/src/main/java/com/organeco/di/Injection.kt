package com.organeco.di

import android.content.Context
import com.organeco.model.local.preferences.AuthenticationPreference
import com.organeco.model.local.preferences.dataStore
import com.organeco.model.remote.ApiConfig
import com.organeco.model.remote.ApiConfigMl
import com.organeco.model.remote.respository.ApiRepository

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