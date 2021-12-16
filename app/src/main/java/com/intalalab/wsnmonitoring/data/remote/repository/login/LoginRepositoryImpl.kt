package com.intalalab.wsnmonitoring.data.remote.repository.login

import com.intalalab.wsnmonitoring.core.Resource
import com.intalalab.wsnmonitoring.core.Status
import com.intalalab.wsnmonitoring.data.remote.datasource.LoginDataSource
import com.intalalab.wsnmonitoring.data.remote.model.login.LoginRequestBody
import com.intalalab.wsnmonitoring.data.remote.model.login.LoginResponseModel
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginDataSource: LoginDataSource
) : LoginRepository {

    override suspend fun login(loginRequestBody: LoginRequestBody): Resource<LoginResponseModel> {
        val result = loginDataSource.login(loginRequestBody)

        return if(result.status == Status.SUCCESS) {
            Resource.success(result.data!!)
        }
        else {
            Resource.error(null, result.error)
        }
    }

}