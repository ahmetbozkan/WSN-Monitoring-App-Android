package com.intalalab.wsnmonitoring.data.remote.repository.coordinator

import com.intalalab.wsnmonitoring.core.Resource
import com.intalalab.wsnmonitoring.core.Status
import com.intalalab.wsnmonitoring.data.local.mapper.CoordinatorMapper
import com.intalalab.wsnmonitoring.data.local.model.CoordinatorEntity
import com.intalalab.wsnmonitoring.data.remote.datasource.CoordinatorDataSource
import com.intalalab.wsnmonitoring.data.remote.model.coordinator.CoordinatorRequestBody
import com.intalalab.wsnmonitoring.data.remote.model.coordinator.CoordinatorResponseModel
import javax.inject.Inject

class CoordinatorRepositoryImpl @Inject constructor(
    private val coordinatorDataSource: CoordinatorDataSource,
    private val coordinatorMapper: CoordinatorMapper
) : CoordinatorRepository {
    override suspend fun getCoordinatorsByWsn(
        coordinatorRequestBody: CoordinatorRequestBody
    ): Resource<List<CoordinatorEntity>> {
        val response = coordinatorDataSource.getCoordinatorsByWsn(coordinatorRequestBody)

        return if(response.status == Status.SUCCESS) {
            Resource.success(coordinatorMapper.map(response.data))
        }
        else {
            Resource.error(null, response.error)
        }
    }
}