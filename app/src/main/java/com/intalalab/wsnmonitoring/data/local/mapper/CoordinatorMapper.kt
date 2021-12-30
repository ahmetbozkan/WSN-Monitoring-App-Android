package com.intalalab.wsnmonitoring.data.local.mapper

import com.intalalab.wsnmonitoring.base.BaseMapper
import com.intalalab.wsnmonitoring.data.local.model.CoordinatorEntity
import com.intalalab.wsnmonitoring.data.remote.model.coordinator.CoordinatorResponseModel
import com.intalalab.wsnmonitoring.util.extension.emptyIfNull
import com.intalalab.wsnmonitoring.util.extension.zeroIfNull
import javax.inject.Inject

class CoordinatorMapper @Inject constructor() :
    BaseMapper<List<CoordinatorResponseModel>, List<CoordinatorEntity>> {

    override fun map(type: List<CoordinatorResponseModel>?): List<CoordinatorEntity> {
        return if (type.isNullOrEmpty()) {
            listOf()
        } else {
            type.map {
                CoordinatorEntity(
                    id = it.id.zeroIfNull(),
                    name = it.name.emptyIfNull(),
                    description = it.description.emptyIfNull(),
                    city = it.city.zeroIfNull(),
                    cityName = it.cityName.emptyIfNull(),
                    country = it.country.zeroIfNull(),
                    countryName = it.countryName.emptyIfNull(),
                    county = it.county.zeroIfNull(),
                    countyName = it.countyName.emptyIfNull(),
                    createdBy = it.createdBy.emptyIfNull(),
                    district = it.district.zeroIfNull(),
                    districtName = it.districtName.emptyIfNull(),
                    latitude = it.latitude.zeroIfNull(),
                    longitude = it.longitude.zeroIfNull()
                )
            }
        }
    }

}