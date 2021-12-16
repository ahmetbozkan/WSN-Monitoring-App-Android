package com.intalalab.wsnmonitoring.data.remote.datasource

import com.intalalab.wsnmonitoring.base.BaseDataSource
import com.intalalab.wsnmonitoring.data.remote.model.router.RouterRequestBody
import com.intalalab.wsnmonitoring.data.remote.service.RouterApi
import javax.inject.Inject

class RouterDataSource @Inject constructor(
    private val api: RouterApi
) : BaseDataSource() {

    suspend fun getRoutersByCoordinator(routerRequestBody: RouterRequestBody) =
        handleRequest {
            api.getRoutersByCoordinator(routerRequestBody)
        }

}