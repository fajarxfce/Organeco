package com.srp.model.builder

class CalculatorRequestBuilder {
    private var temperature: Int = 0
    private var humidity: Int = 0
    private var moisture: Int = 0
    private var soilType: String = ""
    private var cropType: String = ""
    private var nitrogen: Int = 0
    private var potassium: Int = 0
    private var phosphorous: Int = 0

    fun setTemperature(temperature: Int) = apply { this.temperature = temperature }
    fun setHumidity(humidity: Int) = apply { this.humidity = humidity }
    fun setMoisture(moisture: Int) = apply { this.moisture = moisture }
    fun setSoilType(soilType: String) = apply { this.soilType = soilType }
    fun setCropType(cropType: String) = apply { this.cropType = cropType }
    fun setNitrogen(nitrogen: Int) = apply { this.nitrogen = nitrogen }
    fun setPotassium(potassium: Int) = apply { this.potassium = potassium }
    fun setPhosphorous(phosphorous: Int) = apply { this.phosphorous = phosphorous }

    fun build() = CalculatorRequest(
        temperature,
        humidity,
        moisture,
        soilType,
        cropType,
        nitrogen,
        potassium,
        phosphorous
    )
}