package com.organeco.viewmodel

import androidx.lifecycle.ViewModel
import com.organeco.adapter.UserDetailsAdapter
import com.organeco.model.remote.respository.ApiRepository

class MainViewModel(private val apiRepository: ApiRepository) : ViewModel() {

    fun fetchUserDetails(): UserDetailsAdapter {
        return apiRepository.getUserDetails()
    }
}