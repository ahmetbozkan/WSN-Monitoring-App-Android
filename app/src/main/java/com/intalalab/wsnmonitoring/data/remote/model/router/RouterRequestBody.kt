package com.intalalab.wsnmonitoring.data.remote.model.router

import com.google.gson.annotations.SerializedName
import com.intalalab.wsnmonitoring.data.remote.model.login.LoginResponseModel

data class RouterRequestBody(
    @SerializedName("Id") val id: Long,
    @SerializedName("Login") val userInfo: LoginResponseModel
)
