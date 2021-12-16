package com.intalalab.wsnmonitoring.data.remote.model.sensor

import com.google.gson.annotations.SerializedName

data class SensorResponseModel(
    @SerializedName("SensorId") val id: Long?,
    @SerializedName("SensorName") val name: String?,
    @SerializedName("SensorDescription") val description: String?,
    @SerializedName("SensorMeasurementTypes") val measurementTypes: List<SensorMeasurementTypeResponseModel>?
)

data class SensorMeasurementTypeResponseModel(
    @SerializedName("SensorMeasurementTypeId") val measurementTypeId: Long?,
    @SerializedName("SensorMeasurementDefinitionId") val measurementDefinitionId: Long?,
    @SerializedName("SensorMeasurementTypeName") val measurementTypeName: String?,
    @SerializedName("SensorMeasurementTypeAbbreviation") val measurementTypeAbbreviation: String?
)

