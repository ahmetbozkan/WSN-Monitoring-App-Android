package com.intalalab.wsnmonitoring.data.remote.repository.wsn

import com.intalalab.wsnmonitoring.core.Resource
import com.intalalab.wsnmonitoring.core.Status
import com.intalalab.wsnmonitoring.data.local.mapper.WSNMapper
import com.intalalab.wsnmonitoring.data.local.model.WSNEntity
import com.intalalab.wsnmonitoring.data.remote.datasource.WSNDataSource
import com.intalalab.wsnmonitoring.data.remote.model.login.LoginResponseModel
import javax.inject.Inject

class WSNRepositoryImpl @Inject constructor(
    private val wsnDataSource: WSNDataSource,
    private val wsnMapper: WSNMapper
) : WSNRepository {

    override suspend fun getWSN(wsnRequestModel: LoginResponseModel): Resource<List<WSNEntity>> {
        val response = wsnDataSource.getWSN(wsnRequestModel)

        return if (response.status == Status.SUCCESS) {
            Resource.success(wsnMapper.map(response.data))
        } else {
            Resource.error(null, response.error)
        }
    }

}