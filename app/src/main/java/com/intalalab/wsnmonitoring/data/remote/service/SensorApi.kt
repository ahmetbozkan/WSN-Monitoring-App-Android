package com.intalalab.wsnmonitoring.data.remote.service

import com.intalalab.wsnmonitoring.data.remote.model.sensor.SensorRequestBody
import com.intalalab.wsnmonitoring.data.remote.model.sensor.SensorResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SensorApi {

    @POST("getsensorsbyrouter")
    suspend fun getSensorsByRouter(
        @Body sensorRequestBody: SensorRequestBody
    ): Response<List<SensorResponseModel>>

}