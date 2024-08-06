package com.srp.view.activity.calculator

import android.os.Parcel
import android.os.Parcelable

data class CalculatorInput(
    val temperature: Number,
    val humidity: Number,
    val moisture: Number,
    val soilType: Number,
    val cropType: Number,
    val nitrogen: Number,
    val potassium: Number,
    val phosphorous: Number,
    val result: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(temperature.toDouble())
        parcel.writeDouble(humidity.toDouble())
        parcel.writeDouble(moisture.toDouble())
        parcel.writeDouble(soilType.toDouble())
        parcel.writeDouble(cropType.toDouble())
        parcel.writeDouble(nitrogen.toDouble())
        parcel.writeDouble(potassium.toDouble())
        parcel.writeDouble(phosphorous.toDouble())
        parcel.writeString(result)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CalculatorInput> {
        override fun createFromParcel(parcel: Parcel): CalculatorInput {
            return CalculatorInput(parcel)
        }

        override fun newArray(size: Int): Array<CalculatorInput?> {
            return arrayOfNulls(size)
        }
    }
}
