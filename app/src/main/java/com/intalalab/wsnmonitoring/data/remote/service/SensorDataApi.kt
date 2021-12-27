package com.intalalab.wsnmonitoring.data.remote.service

import com.intalalab.wsnmonitoring.data.remote.model.sensor.data.SensorDataRequestBody
import com.intalalab.wsnmonitoring.data.remote.model.sensor.data.SensorDataResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SensorDataApi {

    @POST("getsensordatabyrouterandsensor")
    suspend fun getSensorDataByRouterAndSensor(
        @Body sensorDataRequestBody: SensorDataRequestBody
    ): Response<List<SensorDataResponseModel>>

}