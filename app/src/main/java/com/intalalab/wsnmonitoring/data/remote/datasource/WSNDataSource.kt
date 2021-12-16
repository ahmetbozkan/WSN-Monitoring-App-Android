package com.intalalab.wsnmonitoring.data.remote.datasource

import com.intalalab.wsnmonitoring.base.BaseDataSource
import com.intalalab.wsnmonitoring.core.Resource
import com.intalalab.wsnmonitoring.data.remote.model.login.LoginResponseModel
import com.intalalab.wsnmonitoring.data.remote.model.wsn.WSNResponseModel
import com.intalalab.wsnmonitoring.data.remote.service.WSNApi
import javax.inject.Inject

class WSNDataSource @Inject constructor(
    private val api: WSNApi
): BaseDataSource() {

    suspend fun getWSN(wsnRequestModel: LoginResponseModel): Resource<List<WSNResponseModel>> =
        handleRequest {
            api.getWSN(wsnRequestModel)
        }

}