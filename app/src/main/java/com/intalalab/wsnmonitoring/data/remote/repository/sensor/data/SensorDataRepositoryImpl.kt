package com.intalalab.wsnmonitoring.data.remote.repository.sensor.data

import com.intalalab.wsnmonitoring.core.Resource
import com.intalalab.wsnmonitoring.core.Status
import com.intalalab.wsnmonitoring.data.local.mapper.SensorDataMapper
import com.intalalab.wsnmonitoring.data.local.model.SensorDataEntity
import com.intalalab.wsnmonitoring.data.remote.datasource.SensorDataDataSource
import com.intalalab.wsnmonitoring.data.remote.model.sensor.data.SensorDataRequestBody
import javax.inject.Inject

class SensorDataRepositoryImpl @Inject constructor(
    private val dataSource: SensorDataDataSource,
    private val sensorDataMapper: SensorDataMapper
) : SensorDataRepository {

    override suspend fun getSensorData(
        sensorDataRequestBody: SensorDataRequestBody
    ): Resource<List<SensorDataEntity>> {
        val response = dataSource.getSensorData(sensorDataRequestBody)

        return when (response.status) {
            Status.SUCCESS -> {
                Resource.success(sensorDataMapper.map(response.data))
            }
            Status.ERROR -> {
                Resource.error(null, response.error)
            }
        }
    }
}