package com.intalalab.wsnmonitoring.domain.usecase

import com.intalalab.wsnmonitoring.data.remote.model.sensor.data.SensorDataRequestBody
import com.intalalab.wsnmonitoring.data.remote.repository.sensor.data.SensorDataRepository
import javax.inject.Inject

class GetSensorDataByRouterAndSensorUseCase @Inject constructor(
    private val repository: SensorDataRepository
) {

    suspend operator fun invoke(sensorDataRequestBody: SensorDataRequestBody) =
        repository.getSensorData(sensorDataRequestBody)
}