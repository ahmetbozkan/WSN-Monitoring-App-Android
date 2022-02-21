package com.intalalab.wsnmonitoring.data.remote.model.login

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.intalalab.wsnmonitoring.util.extension.EMPTY
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegisterRequestBody(
    @SerializedName("Email") val email: String = String.EMPTY,
    @SerializedName("UserName") val username: String = String.EMPTY,
    @SerializedName("Password") val password: String = String.EMPTY,
    @SerializedName("ConfirmPassword") val confirmPassword: String = String.EMPTY,
    @SerializedName("SecurityQuestion") val securityQuestion: String = String.EMPTY,
    @SerializedName("SecurityQuestionAnswer") val securityQuestionAnswer: String = String.EMPTY
): Parcelable
