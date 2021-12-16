package com.intalalab.wsnmonitoring.domain.usecase

import com.intalalab.wsnmonitoring.data.remote.model.sensor.SensorRequestBody
import com.intalalab.wsnmonitoring.data.remote.repository.sensor.SensorRepository
import javax.inject.Inject

class GetSensorsByRouterUseCase @Inject constructor(
    private val repository: SensorRepository
) {

    suspend operator fun invoke(sensorRequestBody: SensorRequestBody) =
        repository.getSensorsByRouter(sensorRequestBody)

}