package com.intalalab.wsnmonitoring.data.remote.model.router

import com.google.gson.annotations.SerializedName

data class RouterResponseModel(
    @SerializedName("RouterId") val id: Long?,
    @SerializedName("RouterName") val name: String?,
    @SerializedName("RouterDescription") val description: String?,
    @SerializedName("RouterLocationCity") val city: Long?,
    @SerializedName("RouterLocationCityName") val cityName: String?,
    @SerializedName("RouterLocationCountry") val country: Long?,
    @SerializedName("RouterLocationCountryName") val countryName: String?,
    @SerializedName("RouterLocationCounty") val county: Long?,
    @SerializedName("RouterLocationCountyName") val countyName: String?,
    @SerializedName("RouterCreatedBy") val createdBy: String?,
    @SerializedName("RouterLocationDistrict") val district: Long?,
    @SerializedName("RouterLocationDistrictName") val districtName: String?,
    @SerializedName("RouterLocationLatitude") val latitude: Double?,
    @SerializedName("RouterLocationLongtitude") val longitude: Double?
)
