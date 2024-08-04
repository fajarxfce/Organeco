package com.organeco.viewmodel

import androidx.lifecycle.ViewModel
import com.organeco.model.builder.CalculatorRequest
import com.organeco.model.remote.respository.ApiRepository

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