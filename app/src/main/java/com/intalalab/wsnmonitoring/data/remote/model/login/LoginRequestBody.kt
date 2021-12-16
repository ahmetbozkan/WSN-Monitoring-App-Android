package com.intalalab.wsnmonitoring.data.remote.model.login

import com.google.gson.annotations.SerializedName

data class LoginRequestBody(
    @SerializedName("UserName") val username: String,
    @SerializedName("Password") val password: String
)
