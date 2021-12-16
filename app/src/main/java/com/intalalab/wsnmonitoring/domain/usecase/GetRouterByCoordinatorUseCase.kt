package com.intalalab.wsnmonitoring.domain.usecase

import com.intalalab.wsnmonitoring.data.remote.model.router.RouterRequestBody
import com.intalalab.wsnmonitoring.data.remote.repository.router.RouterRepository
import javax.inject.Inject

class GetRouterByCoordinatorUseCase @Inject constructor(
    private val repository: RouterRepository
) {

    suspend operator fun invoke(routerRequestBody: RouterRequestBody) =
        repository.getRoutersByCoordinator(routerRequestBody)

}