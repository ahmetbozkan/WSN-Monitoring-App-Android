package com.intalalab.wsnmonitoring.data.remote.service

import com.intalalab.wsnmonitoring.data.remote.model.coordinator.CoordinatorRequestBody
import com.intalalab.wsnmonitoring.data.remote.model.coordinator.CoordinatorResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CoordinatorApi {

    @POST("getcoordinatorsbywsn")
    suspend fun getCoordinatorsByWsn(
        @Body coordinatorRequestBody: CoordinatorRequestBody
    ): Response<List<CoordinatorResponseModel>>

}