package com.intalalab.wsnmonitoring.domain.usecase

import com.intalalab.wsnmonitoring.core.Resource
import com.intalalab.wsnmonitoring.data.remote.model.login.LoginRequestBody
import com.intalalab.wsnmonitoring.data.remote.model.login.LoginResponseModel
import com.intalalab.wsnmonitoring.data.remote.repository.login.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: LoginRepository
) {

    suspend operator fun invoke(loginRequestBody: LoginRequestBody): Resource<LoginResponseModel> =
        repository.login(loginRequestBody)

}