package com.srp.model.local.fertilizer

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataDummy(
    val id: Int,
    val name: String,
    val plantType: String,
    val description: String,
    val image: Int,
) : Parcelable
