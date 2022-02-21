package com.intalalab.wsnmonitoring.data.local.model

import com.intalalab.wsnmonitoring.util.extension.EMPTY

data class SensorDataEntity(
    val dateDay: Long = 0,
    val dateHour: Long = 0,
    val dateMinute: Long = 0,
    val dateMonth: Long = 0,
    val dateYear: Long = 0,
    val fullDatetime: String = String.EMPTY,
    val sensorMeasurementValue: String = String.EMPTY
)
