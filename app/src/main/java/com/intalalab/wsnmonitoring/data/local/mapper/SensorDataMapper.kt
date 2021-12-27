package com.intalalab.wsnmonitoring.data.local.mapper

import com.intalalab.wsnmonitoring.base.BaseMapper
import com.intalalab.wsnmonitoring.data.local.model.SensorDataEntity
import com.intalalab.wsnmonitoring.data.remote.model.sensor.data.SensorDataResponseModel
import com.intalalab.wsnmonitoring.util.extension.emptyIfNull
import com.intalalab.wsnmonitoring.util.extension.zeroIfNull
import javax.inject.Inject

class SensorDataMapper @Inject constructor() :
    BaseMapper<List<SensorDataResponseModel>, List<SensorDataEntity>> {

    override fun map(type: List<SensorDataResponseModel>?): List<SensorDataEntity> {
        return if (type.isNullOrEmpty()) {
            listOf()
        } else {
            type.map {
                SensorDataEntity(
                    dateDay = it.dateDay.zeroIfNull(),
                    dateHour = it.dateHour.zeroIfNull(),
                    dateMinute = it.dateMinute.zeroIfNull(),
                    dateYear = it.dateYear.zeroIfNull(),
                    dateMonth = it.dateMonth.zeroIfNull(),
                    fullDatetime = it.fullDatetime.emptyIfNull(),
                    sensorMeasurementValue = it.sensorMeasurementValue.emptyIfNull()
                )
            }
        }
    }

}
