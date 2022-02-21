package com.intalalab.wsnmonitoring.data.remote.service

import com.intalalab.wsnmonitoring.data.remote.model.register.RegisterResponseModel
import com.intalalab.wsnmonitoring.data.remote.model.login.LoginRequestBody
import com.intalalab.wsnmonitoring.data.remote.model.login.LoginResponseModel
import com.intalalab.wsnmonitoring.data.remote.model.login.RegisterRequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("login")
    suspend fun login(@Body loginRequestBody: LoginRequestBody): Response<LoginResponseModel>

    @POST("register")
    suspend fun register(@Body registerRequestBody: RegisterRequestBody): Response<RegisterResponseModel>

}