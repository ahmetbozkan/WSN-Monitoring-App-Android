package com.intalalab.wsnmonitoring.data.remote.model.coordinator

import com.google.gson.annotations.SerializedName
import com.intalalab.wsnmonitoring.data.remote.model.login.LoginResponseModel

data class CoordinatorRequestBody(
    @SerializedName("Id") val id: Long,
    @SerializedName("Login") val userInfo: LoginResponseModel
)
