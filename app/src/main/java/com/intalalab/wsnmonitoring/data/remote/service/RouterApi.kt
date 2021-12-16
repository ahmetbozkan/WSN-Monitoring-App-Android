package com.intalalab.wsnmonitoring.data.remote.service

import com.intalalab.wsnmonitoring.data.remote.model.router.RouterRequestBody
import com.intalalab.wsnmonitoring.data.remote.model.router.RouterResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RouterApi {

    @POST("getrouterbycoordinator")
    suspend fun getRoutersByCoordinator(
        @Body routerRequestBody: RouterRequestBody
    ): Response<List<RouterResponseModel>>

}