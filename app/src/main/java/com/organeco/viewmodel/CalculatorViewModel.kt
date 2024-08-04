package com.organeco.viewmodel

import androidx.lifecycle.ViewModel
import com.organeco.model.remote.respository.ApiRepository

class CalculatorViewModel(private val apiRepository: ApiRepository) : ViewModel() {

    fun postCalculate(
        temperature: Int,
        humidity: Int,
        moisture: Int,
        soil_type: String,
        crop_type: String,
        nitrogen: Int,
        potassium: Int,
        phosphorous: Int
    ) =
        apiRepository.postCalculator(
            temperature,
            humidity,
            moisture,
            soil_type,
            crop_type,
            nitrogen,
            potassium,
            phosphorous
        )


}