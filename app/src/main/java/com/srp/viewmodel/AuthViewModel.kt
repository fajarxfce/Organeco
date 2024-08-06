package com.srp.viewmodel

import androidx.lifecycle.ViewModel
import com.srp.model.remote.respository.ApiRepository

class AuthViewModel(private val apiRepository: ApiRepository) : ViewModel() {

    suspend fun postLogin(email: String, password: String) =
        apiRepository.postLogin(email, password)

    suspend fun postRegister(name: String, email: String, password: String) =
        apiRepository.postRegister(name, email, password)
}