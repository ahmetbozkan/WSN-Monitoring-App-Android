package com.intalalab.wsnmonitoring.data.local.model

import com.google.gson.annotations.SerializedName
import com.intalalab.wsnmonitoring.util.extension.EMPTY

data class SensorEntity(
    val id: Long = 0,
    val name: String = String.EMPTY,
    val description: String = String.EMPTY,
    val measurementTypes: List<SensorMeasurementTypeEntity>?
)

data class SensorMeasurementTypeEntity(
    val measurementTypeId: Long = 0,
    val measurementDefinitionId: Long = 0,
    val measurementTypeName: String = String.EMPTY,
    val measurementTypeAbbreviation: String = String.EMPTY
)
