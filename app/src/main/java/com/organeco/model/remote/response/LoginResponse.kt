package com.organeco.model.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @field:SerializedName("data")
    val data: Data,

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String
)

data class Data(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("userId")
    val userId: String,

    @field:SerializedName("token")
    val token: String
)
