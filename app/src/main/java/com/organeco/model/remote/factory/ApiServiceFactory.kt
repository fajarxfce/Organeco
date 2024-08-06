package com.organeco.model.remote.factory

import com.organeco.BuildConfig
import com.organeco.model.remote.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiServiceFactory {

    enum class ApiType {
        DEFAULT, ML
    }

    @Volatile
    private var instance: ApiService? = null

    fun getInstance(type: ApiType): ApiService {
        return instance ?: synchronized(this) {
            instance ?: createApiService(type).also { instance = it }
        }
    }

    fun createApiService(type: ApiType): ApiService {
        val baseUrl = when (type) {
            ApiType.DEFAULT -> BuildConfig.BASE_URL
            ApiType.ML -> "https://organecoo-ml.et.r.appspot.com/"
        }

        val loggingInterceptor =
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(ApiService::class.java)
    }
}