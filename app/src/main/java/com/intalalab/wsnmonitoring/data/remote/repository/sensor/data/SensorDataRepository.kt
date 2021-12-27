package com.intalalab.wsnmonitoring.data.remote.repository.sensor.data

import com.intalalab.wsnmonitoring.core.Resource
import com.intalalab.wsnmonitoring.data.local.model.SensorDataEntity
import com.intalalab.wsnmonitoring.data.remote.model.sensor.data.SensorDataRequestBody

interface SensorDataRepository {

    /**
     * get sensor measurement data by sensor, router, sensorMeasurementType Id's
     * @param sensorDataRequestBody contains routerId, sensorId, sensorMeasurementTypeId & user info model
     * @return resource of Sensor Data wrapped in List
     */
    suspend fun getSensorData(sensorDataRequestBody: SensorDataRequestBody): Resource<List<SensorDataEntity>>

}