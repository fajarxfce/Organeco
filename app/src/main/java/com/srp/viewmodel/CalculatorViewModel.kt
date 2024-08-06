package com.srp.viewmodel

import androidx.lifecycle.ViewModel
import com.srp.model.builder.CalculatorRequest
import com.srp.model.remote.respository.ApiRepository

class CalculatorViewModel(private val apiRepository: ApiRepository) : ViewModel() {

    fun postCalculate(
        request: CalculatorRequest
    ) =
        apiRepository.postCalculator(
            request.temperature,
            request.humidity,
            request.moisture,
            request.soilType,
            request.cropType,
            request.nitrogen,
            request.potassium,
            request.phosphorous
        )


}