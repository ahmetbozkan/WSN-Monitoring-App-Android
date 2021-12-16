package com.intalalab.wsnmonitoring.data.remote.repository.sensor

import com.intalalab.wsnmonitoring.core.Resource
import com.intalalab.wsnmonitoring.data.local.model.SensorEntity
import com.intalalab.wsnmonitoring.data.remote.model.sensor.SensorRequestBody
import com.intalalab.wsnmonitoring.data.remote.model.sensor.SensorResponseModel
import retrofit2.Response

interface SensorRepository {

    /**
     * get sensor list by routerId
     * @param sensorRequestBody contains routerId & user info model
     * @return resource of Sensor Model wrapped in List
     */
    suspend fun getSensorsByRouter(
        sensorRequestBody: SensorRequestBody
    ): Resource<List<SensorEntity>>

}