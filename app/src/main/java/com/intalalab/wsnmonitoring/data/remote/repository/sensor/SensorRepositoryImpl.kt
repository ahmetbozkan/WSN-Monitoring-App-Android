package com.intalalab.wsnmonitoring.data.remote.repository.sensor

import com.intalalab.wsnmonitoring.core.Resource
import com.intalalab.wsnmonitoring.core.Status
import com.intalalab.wsnmonitoring.data.local.mapper.SensorMapper
import com.intalalab.wsnmonitoring.data.local.model.SensorEntity
import com.intalalab.wsnmonitoring.data.remote.datasource.SensorDataSource
import com.intalalab.wsnmonitoring.data.remote.model.sensor.SensorRequestBody
import javax.inject.Inject

class SensorRepositoryImpl @Inject constructor(
    private val sensorDataSource: SensorDataSource,
    private val sensorMapper: SensorMapper
) : SensorRepository {

    override suspend fun getSensorsByRouter(
        sensorRequestBody: SensorRequestBody
    ): Resource<List<SensorEntity>> {
        val response = sensorDataSource.getSensorsByRouter(sensorRequestBody)

        return when (response.status) {
            Status.SUCCESS -> {
                Resource.success(sensorMapper.map(response.data))
            }
            Status.ERROR -> {
                Resource.error(null, response.error)
            }
        }
    }
}