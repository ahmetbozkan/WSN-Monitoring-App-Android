package com.intalalab.wsnmonitoring.data.local.mapper

import com.intalalab.wsnmonitoring.base.BaseMapper
import com.intalalab.wsnmonitoring.data.local.model.RouterEntity
import com.intalalab.wsnmonitoring.data.remote.model.router.RouterResponseModel
import com.intalalab.wsnmonitoring.util.extension.emptyIfNull
import com.intalalab.wsnmonitoring.util.extension.zeroIfNull
import javax.inject.Inject

class RouterMapper @Inject constructor() :
    BaseMapper<List<RouterResponseModel>, List<RouterEntity>> {

    override fun map(type: List<RouterResponseModel>?): List<RouterEntity> {
        return if (type.isNullOrEmpty()) {
            listOf()
        } else {
            type.map {
                RouterEntity(
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