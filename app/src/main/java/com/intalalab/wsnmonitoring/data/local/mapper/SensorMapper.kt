package com.intalalab.wsnmonitoring.data.local.mapper

import com.intalalab.wsnmonitoring.core.BaseMapper
import com.intalalab.wsnmonitoring.data.local.model.SensorEntity
import com.intalalab.wsnmonitoring.data.local.model.SensorMeasurementTypeEntity
import com.intalalab.wsnmonitoring.data.remote.model.sensor.SensorMeasurementTypeResponseModel
import com.intalalab.wsnmonitoring.data.remote.model.sensor.SensorResponseModel
import com.intalalab.wsnmonitoring.util.extension.emptyIfNull
import com.intalalab.wsnmonitoring.util.extension.zeroIfNull
import javax.inject.Inject

class SensorMapper @Inject constructor() :
    BaseMapper<List<SensorResponseModel>, List<SensorEntity>> {

    override fun map(type: List<SensorResponseModel>?): List<SensorEntity> {
        return if (type.isNullOrEmpty()) {
            listOf()
        } else {
            type.map {
                SensorEntity(
                    id = it.id.zeroIfNull(),
                    name = it.name.emptyIfNull(),
                    description = it.description.emptyIfNull(),
                    measurementTypes = mapMeasurementTypes(it.measurementTypes)
                )
            }
        }
    }

    private fun mapMeasurementTypes(
        measurementTypes: List<SensorMeasurementTypeResponseModel>?
    ): List<SensorMeasurementTypeEntity> {
        return if (measurementTypes.isNullOrEmpty()) {
            listOf()
        } else {
            measurementTypes.map {
                SensorMeasurementTypeEntity(
                    measurementTypeId = it.measurementTypeId.zeroIfNull(),
                    measurementDefinitionId = it.measurementDefinitionId.zeroIfNull(),
                    measurementTypeName = it.measurementTypeName.emptyIfNull(),
                    measurementTypeAbbreviation = it.measurementTypeAbbreviation.emptyIfNull()
                )
            }
        }
    }


}