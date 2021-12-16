package com.intalalab.wsnmonitoring.data.remote.repository.router

import com.intalalab.wsnmonitoring.core.Resource
import com.intalalab.wsnmonitoring.core.Status
import com.intalalab.wsnmonitoring.data.local.mapper.RouterMapper
import com.intalalab.wsnmonitoring.data.local.model.RouterEntity
import com.intalalab.wsnmonitoring.data.remote.datasource.RouterDataSource
import com.intalalab.wsnmonitoring.data.remote.model.router.RouterRequestBody
import javax.inject.Inject

class RouterRepositoryImpl @Inject constructor(
    private val routerDataSource: RouterDataSource,
    private val routerMapper: RouterMapper
) : RouterRepository {

    override suspend fun getRoutersByCoordinator(routerRequestBody: RouterRequestBody): Resource<List<RouterEntity>> {
        val response = routerDataSource.getRoutersByCoordinator(routerRequestBody)

        return when (response.status) {
            Status.SUCCESS -> {
                Resource.success(routerMapper.map(response.data))
            }
            Status.ERROR -> {
                Resource.error(null, response.error)
            }
        }
    }

}