package com.intalalab.wsnmonitoring.data.remote.model.sensor

import com.google.gson.annotations.SerializedName
import com.intalalab.wsnmonitoring.data.remote.model.login.LoginResponseModel

data class SensorRequestBody(
    @SerializedName("Id") val routerId: Long,
    @SerializedName("Login") val userInfo: LoginResponseModel
)
