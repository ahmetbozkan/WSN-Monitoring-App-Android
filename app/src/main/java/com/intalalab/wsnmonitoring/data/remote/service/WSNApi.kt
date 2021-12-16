package com.intalalab.wsnmonitoring.data.remote.service

import com.intalalab.wsnmonitoring.data.remote.model.login.LoginResponseModel
import com.intalalab.wsnmonitoring.data.remote.model.wsn.WSNResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface WSNApi {

    @POST("getwsn")
    suspend fun getWSN(@Body requestModel: LoginResponseModel): Response<List<WSNResponseModel>>

}