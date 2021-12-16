package com.intalalab.wsnmonitoring.domain.usecase

import com.intalalab.wsnmonitoring.data.remote.model.coordinator.CoordinatorRequestBody
import com.intalalab.wsnmonitoring.data.remote.repository.coordinator.CoordinatorRepository
import javax.inject.Inject

class GetCoordinatorByWsnUseCase @Inject constructor(
    private val coordinatorRepository: CoordinatorRepository
) {

    suspend operator fun invoke(coordinatorRequestBody: CoordinatorRequestBody) =
        coordinatorRepository.getCoordinatorsByWsn(coordinatorRequestBody)

}