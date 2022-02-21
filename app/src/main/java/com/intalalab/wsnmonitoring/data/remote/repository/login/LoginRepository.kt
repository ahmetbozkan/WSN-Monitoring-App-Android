package com.intalalab.wsnmonitoring.data.remote.repository.login

import com.intalalab.wsnmonitoring.core.Resource
import com.intalalab.wsnmonitoring.data.remote.model.register.RegisterResponseModel
import com.intalalab.wsnmonitoring.data.remote.model.login.LoginRequestBody
import com.intalalab.wsnmonitoring.data.remote.model.login.LoginResponseModel
import com.intalalab.wsnmonitoring.data.remote.model.login.RegisterRequestBody

interface LoginRepository {

    /**
     * user login request
     * @param loginRequestBody contains params for login
     * @return resource of LoginResponseModel
     */
    suspend fun login(loginRequestBody: LoginRequestBody): Resource<LoginResponseModel>

    /**
     * register the user
     * @param registerRequestBody contains parameters for register
     * @return resource of RegisterResponseModel
     */
    suspend fun register(registerRequestBody: RegisterRequestBody): Resource<RegisterResponseModel>

}