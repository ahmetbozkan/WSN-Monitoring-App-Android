package com.intalalab.wsnmonitoring.data.remote.model.sensor.data

import com.google.gson.annotations.SerializedName

data class SensorDataResponseModel(
    @SerializedName("DateDay") val dateDay: Long?,
    @SerializedName("DateHour") val dateHour: Long?,
    @SerializedName("DateMinute") val dateMinute: Long?,
    @SerializedName("DateMonth") val dateMonth: Long?,
    @SerializedName("DateYear") val dateYear: Long?,
    @SerializedName("FullDatetime") val fullDatetime: String?,
    @SerializedName("SensorMeasurementValue") val sensorMeasurementValue: String?
)
