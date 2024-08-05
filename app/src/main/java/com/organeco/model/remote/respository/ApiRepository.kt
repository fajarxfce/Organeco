package com.organeco.model.remote.respository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.organeco.adapter.UserDetailsAdapter
import com.organeco.model.User
import com.organeco.model.local.preferences.AuthenticationPreference
import com.organeco.model.remote.ApiService
import com.organeco.model.remote.factory.ApiServiceFactory
import com.organeco.model.remote.response.CalculatorResponse
import com.organeco.model.remote.response.LoginResponse
import com.organeco.model.remote.response.RegisterResponse
import com.organeco.model.remote.utils.MediatorResult
import com.organeco.view.utils.wrapperIdling

class ApiRepository(
    private val apiService: ApiService = ApiServiceFactory.createApiService(ApiServiceFactory.ApiType.DEFAULT),
    private val preferences: AuthenticationPreference
) {

    suspend fun postLogin(
        email: String,
        password: String
    ): LiveData<MediatorResult<LoginResponse>> = wrapperIdling {
        liveData {
            emit(MediatorResult.Loading)
            try {
                val respon = apiService.postLogin(email, password)
                emit(MediatorResult.Success(respon))
            } catch (e: Exception) {
                emit(MediatorResult.Error(e.message.toString()))
            }
        }
    }

    fun getUserDetails(): UserDetailsAdapter {
        val user = User(
            userName = preferences.getNameKey().asLiveData().value ?: "",
            email = preferences.getEmailKey().asLiveData().value ?: "",
            tokenKey = preferences.getTokenKey().asLiveData().value ?: "",
            userId = preferences.getUserId().asLiveData().value ?: "",
            onBoardStatus = preferences.getOnBoardStatus().asLiveData().value ?: false
        )
        return UserDetailsAdapter(user)
    }

    suspend fun postRegister(
        name: String,
        email: String,
        password: String
    ): LiveData<MediatorResult<RegisterResponse>> = wrapperIdling {
        liveData {
            emit(MediatorResult.Loading)
            try {
                val respon = apiService.postRegister(name, email, password)
                emit(MediatorResult.Success(respon))
            } catch (e: Exception) {
                emit(MediatorResult.Error(e.message.toString()))
            }
        }
    }

    fun postCalculator(
        temperature: Int,
        humidity: Int,
        moisture: Int,
        soil_type: String,
        crop_type: String,
        nitrogen: Int,
        potassium: Int,
        phosphorous: Int
    ): LiveData<MediatorResult<CalculatorResponse>> = wrapperIdling {
        liveData {
            emit(MediatorResult.Loading)
            try {
                val respon = apiService.postCalculator(
                    temperature,
                    humidity,
                    moisture,
                    soil_type,
                    crop_type,
                    nitrogen,
                    potassium,
                    phosphorous
                )
                emit(MediatorResult.Success(respon))
            } catch (e: Exception) {
                emit(MediatorResult.Error(e.message.toString()))
            }
        }
    }

    fun getUserToken(): LiveData<String> = preferences.getTokenKey().asLiveData()
    fun getUserName(): LiveData<String> = preferences.getNameKey().asLiveData()
    fun getIdUser(): LiveData<String> = preferences.getUserId().asLiveData()
    fun getOnBoardStatus(): LiveData<Boolean> = preferences.getOnBoardStatus().asLiveData()
    fun getEmail(): LiveData<String> = preferences.getEmailKey().asLiveData()

    suspend fun saveUser(
        onBoardStatus: Boolean,
        userName: String,
        email: String,
        tokenKey: String,
        userId: String
    ) {
        preferences.savePreferences(onBoardStatus, userName, email, tokenKey, userId)
    }
}