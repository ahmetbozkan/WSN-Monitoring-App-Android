package com.intalalab.wsnmonitoring.data.remote.datasource

import com.intalalab.wsnmonitoring.base.BaseDataSource
import com.intalalab.wsnmonitoring.data.remote.model.sensor.data.SensorDataRequestBody
import com.intalalab.wsnmonitoring.data.remote.service.SensorDataApi
import javax.inject.Inject

class SensorDataDataSource @Inject constructor(
    private val api: SensorDataApi
) : BaseDataSource() {

    suspend fun getSensorData(sensorDataRequestBody: SensorDataRequestBody) =
        handleRequest {
            api.getSensorDataByRouterAndSensor(sensorDataRequestBody)
        }

}