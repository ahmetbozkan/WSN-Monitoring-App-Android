package com.intalalab.wsnmonitoring.data.remote.datasource

import com.intalalab.wsnmonitoring.base.BaseDataSource
import com.intalalab.wsnmonitoring.data.remote.model.coordinator.CoordinatorRequestBody
import com.intalalab.wsnmonitoring.data.remote.service.CoordinatorApi
import javax.inject.Inject

class CoordinatorDataSource @Inject constructor(
    private val api: CoordinatorApi
) : BaseDataSource() {

    suspend fun getCoordinatorsByWsn(coordinatorRequestBody: CoordinatorRequestBody) =
        handleRequest {
            api.getCoordinatorsByWsn(coordinatorRequestBody)
        }

}