package com.intalalab.wsnmonitoring.data.remote.model.coordinator

import com.google.gson.annotations.SerializedName

data class CoordinatorResponseModel(
    @SerializedName("CoordinatorId") val id: Long?,
    @SerializedName("CoordinatorName") val name: String?,
    @SerializedName("CoordinatorDescription") val description: String?,
    @SerializedName("CoordinatorCity") val city: Long?,
    @SerializedName("CoordinatorCityName") val cityName: String?,
    @SerializedName("CoordinatorCountry") val country: Long?,
    @SerializedName("CoordinatorCountryName") val countryName: String?,
    @SerializedName("CoordinatorCounty") val county: Long?,
    @SerializedName("CoordinatorCountyName") val countyName: String?,
    @SerializedName("CoordinatorCreatedBy") val createdBy: String?,
    @SerializedName("CoordinatorDistrict") val district: Long?,
    @SerializedName("CoordinatorDistrictName") val districtName: String?,
    @SerializedName("CoordinatorLocationLatitude") val latitude: Double?,
    @SerializedName("CoordinatorLocationLongtitude") val longitude: Double?
)
