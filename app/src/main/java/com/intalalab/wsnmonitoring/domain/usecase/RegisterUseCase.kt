package com.intalalab.wsnmonitoring.domain.usecase

import com.intalalab.wsnmonitoring.data.remote.model.login.RegisterRequestBody
import com.intalalab.wsnmonitoring.data.remote.repository.login.LoginRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: LoginRepository
) {

    suspend operator fun invoke(registerRequestBody: RegisterRequestBody) =
        repository.register(registerRequestBody)
}