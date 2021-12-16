package com.intalalab.wsnmonitoring.data.remote.model.coordinator

import com.google.gson.annotations.SerializedName

data class CoordinatorResponseModel(
    @SerializedName("CoordinatorId") val id: Long?,
    @SerializedName("CoordinatorName") val name: String?,
    @SerializedName("CoordinatorDescription") val description: String?,
    @SerializedName("CoordinatorCity") val city: Long?,
    @SerializedName("CoordinatorCountry") val country: Long?,
    @SerializedName("CoordinatorCounty") val county: Long?,
    @SerializedName("CoordinatorCreatedBy") val createdBy: String?,
    @SerializedName("CoordinatorDistrict") val district: Long?,
    @SerializedName("CoordinatorLocationLatitude") val latitude: Double?,
    @SerializedName("CoordinatorLocationLongtitude") val longitude: Double?
)
