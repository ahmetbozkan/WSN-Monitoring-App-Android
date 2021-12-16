package com.intalalab.wsnmonitoring.data.remote.repository.coordinator

import com.intalalab.wsnmonitoring.core.Resource
import com.intalalab.wsnmonitoring.data.local.model.CoordinatorEntity
import com.intalalab.wsnmonitoring.data.remote.model.coordinator.CoordinatorRequestBody
import com.intalalab.wsnmonitoring.data.remote.model.coordinator.CoordinatorResponseModel

interface CoordinatorRepository {

    /**
     * get coordinators by using wsn and user info
     * @param coordinatorRequestBody contains wsnId & user info model
     * @return resource of Coordinator Model
     */
    suspend fun getCoordinatorsByWsn(
        coordinatorRequestBody: CoordinatorRequestBody
    ): Resource<List<CoordinatorEntity>>

}