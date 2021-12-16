package com.intalalab.wsnmonitoring.domain.usecase

import com.intalalab.wsnmonitoring.data.remote.model.login.LoginResponseModel
import com.intalalab.wsnmonitoring.data.remote.repository.wsn.WSNRepository
import javax.inject.Inject

class GetWSNUseCase @Inject constructor(
    private val repository: WSNRepository
) {

    suspend operator fun invoke(wsnRequestModel: LoginResponseModel) =
        repository.getWSN(wsnRequestModel)

}