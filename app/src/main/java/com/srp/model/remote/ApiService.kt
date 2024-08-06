package com.srp.model.remote

import com.srp.model.remote.response.CalculatorResponse
import com.srp.model.remote.response.LoginResponse
import com.srp.model.remote.response.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun postLogin(
        @Field("email") email: String,
        @Field("password") password: String,
    ): LoginResponse

    @FormUrlEncoded
    @POST("auth/register")
    suspend fun postRegister(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ): RegisterResponse

    @FormUrlEncoded
    @POST("predict")
    suspend fun postCalculator(
        @Field("temparature") temparature: Int,
        @Field("humidity") humidity: Int,
        @Field("moisture") moisture: Int,
        @Field("soil_type") soil_type: String,
        @Field("crop_type") crop_type: String,
        @Field("nitrogen") nitrogen: Int,
        @Field("potassium") potassium: Int,
        @Field("phosphorous") phosphorous: Int
    ): CalculatorResponse
}