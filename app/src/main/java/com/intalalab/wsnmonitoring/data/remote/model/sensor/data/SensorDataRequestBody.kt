package com.intalalab.wsnmonitoring.data.remote.model.sensor.data

import com.google.gson.annotations.SerializedName
import com.intalalab.wsnmonitoring.data.remote.model.login.LoginResponseModel

data class SensorDataRequestBody(
    @SerializedName("Login") val userInfo: LoginResponseModel,
    @SerializedName("RouterId") val routerId: Long,
    @SerializedName("SensorId") val sensorId: Long,
    @SerializedName("SensorMeasurementTypeId") val sensorMeasurementTypeId: Long
)
