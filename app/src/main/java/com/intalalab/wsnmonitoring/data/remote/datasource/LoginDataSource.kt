package com.intalalab.wsnmonitoring.data.remote.datasource

import com.intalalab.wsnmonitoring.base.BaseDataSource
import com.intalalab.wsnmonitoring.data.remote.service.LoginApi
import com.intalalab.wsnmonitoring.data.remote.model.login.LoginRequestBody
import com.intalalab.wsnmonitoring.data.remote.model.login.RegisterRequestBody
import javax.inject.Inject

class LoginDataSource @Inject constructor(
    private val loginApi: LoginApi
) : BaseDataSource() {

    suspend fun login(loginRequestBody: LoginRequestBody) =
        handleRequest {
            loginApi.login(loginRequestBody)
        }

    suspend fun register(registerRequestBody: RegisterRequestBody) =
        handleRequest {
            loginApi.register(registerRequestBody)
        }

}