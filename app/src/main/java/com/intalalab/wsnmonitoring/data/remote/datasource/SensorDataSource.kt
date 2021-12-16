package com.intalalab.wsnmonitoring.data.remote.datasource

import com.intalalab.wsnmonitoring.base.BaseDataSource
import com.intalalab.wsnmonitoring.data.remote.model.sensor.SensorRequestBody
import com.intalalab.wsnmonitoring.data.remote.service.SensorApi
import javax.inject.Inject

class SensorDataSource @Inject constructor(
    private val api: SensorApi
) : BaseDataSource() {

    suspend fun getSensorsByRouter(sensorRequestBody: SensorRequestBody) =
        handleRequest {
            api.getSensorsByRouter(sensorRequestBody)
        }

}