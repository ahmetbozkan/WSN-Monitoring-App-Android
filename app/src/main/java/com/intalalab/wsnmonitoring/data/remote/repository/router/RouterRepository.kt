package com.intalalab.wsnmonitoring.data.remote.repository.router

import com.intalalab.wsnmonitoring.core.Resource
import com.intalalab.wsnmonitoring.data.local.model.RouterEntity
import com.intalalab.wsnmonitoring.data.remote.model.router.RouterRequestBody
import com.intalalab.wsnmonitoring.data.remote.model.router.RouterResponseModel

interface RouterRepository {

    /**
     * get routers by using wsn and user info
     * @param routerRequestBody contains coordinatorId & user info model
     * @return resource of Router Model
     */
    suspend fun getRoutersByCoordinator(
        routerRequestBody: RouterRequestBody
    ): Resource<List<RouterEntity>>

}