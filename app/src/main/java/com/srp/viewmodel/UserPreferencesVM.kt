package com.srp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srp.model.remote.respository.ApiRepository
import kotlinx.coroutines.launch

class UserPreferencesVM(
    private val repository: ApiRepository
) : ViewModel() {

    fun getUserName(): LiveData<String> = repository.getUserName()
    fun getTokenKey(): LiveData<String> = repository.getUserToken()
    fun getUserId(): LiveData<String> = repository.getIdUser()
    fun getOnBoardStatus(): LiveData<Boolean> = repository.getOnBoardStatus()
    fun getEmail(): LiveData<String> = repository.getEmail()

    fun saveUserPreferences(
        onBoardStatus: Boolean,
        userName: String,
        email: String,
        tokenKey: String,
        userId: String
    ) {
        viewModelScope.launch {
            repository.saveUser(onBoardStatus, userName, email, tokenKey, userId)
        }
    }
}