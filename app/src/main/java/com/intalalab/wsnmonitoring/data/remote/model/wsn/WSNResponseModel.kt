package com.intalalab.wsnmonitoring.data.remote.model.wsn

import com.google.gson.annotations.SerializedName

// WSN -> Wireless Sensor Network
data class WSNResponseModel(
    @SerializedName("WirelessSensorNetworkId") val id: Long?,
    @SerializedName("WirelessSensorNetworkName") val name: String?,
    @SerializedName("WirelessSensorNetworkDescription") val description: String?,
    @SerializedName("WirelessSensorNetworkCreatedBy") val createUserId: String?,
    @SerializedName("WirelessSensorNetworkCreatedDate") val createDate: String?,
    @SerializedName("WirelessSensorNetworkDomain") val domain: Long?,
    @SerializedName("WirelessSensorNetworkIsActive") val isActive: Boolean?
)

