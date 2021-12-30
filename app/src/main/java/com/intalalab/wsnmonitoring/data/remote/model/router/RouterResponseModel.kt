package com.intalalab.wsnmonitoring.data.remote.model.router

import com.google.gson.annotations.SerializedName

data class RouterResponseModel(
    @SerializedName("RouterId") val id: Long?,
    @SerializedName("RouterName") val name: String?,
    @SerializedName("RouterDescription") val description: String?,
    @SerializedName("RouterCity") val city: Long?,
    @SerializedName("RouterCityName") val cityName: String?,
    @SerializedName("RouterCountry") val country: Long?,
    @SerializedName("RouterCountryName") val countryName: String?,
    @SerializedName("RouterCounty") val county: Long?,
    @SerializedName("RouterCountyName") val countyName: String?,
    @SerializedName("RouterCreatedBy") val createdBy: String?,
    @SerializedName("RouterDistrict") val district: Long?,
    @SerializedName("RouterDistrictName") val districtName: String?,
    @SerializedName("RouterLocationLatitude") val latitude: Double?,
    @SerializedName("RouterLocationLongtitude") val longitude: Double?
)
