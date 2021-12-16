package com.intalalab.wsnmonitoring.data.remote.repository.login

import com.intalalab.wsnmonitoring.core.Resource
import com.intalalab.wsnmonitoring.data.remote.model.login.LoginRequestBody
import com.intalalab.wsnmonitoring.data.remote.model.login.LoginResponseModel

interface LoginRepository {

    /**
     * user login request
     * @param loginRequestBody contains params for login
     * @return loginResponseModel
     */
    suspend fun login(loginRequestBody: LoginRequestBody): Resource<LoginResponseModel>

}