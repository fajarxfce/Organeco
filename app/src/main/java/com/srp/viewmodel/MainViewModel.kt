package com.srp.viewmodel

import androidx.lifecycle.ViewModel
import com.srp.adapter.UserDetailsAdapter
import com.srp.model.remote.respository.ApiRepository

class MainViewModel(private val apiRepository: ApiRepository) : ViewModel() {

    fun fetchUserDetails(): UserDetailsAdapter {
        return apiRepository.getUserDetails()
    }
}