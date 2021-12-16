package com.intalalab.wsnmonitoring.base

import com.intalalab.wsnmonitoring.core.Failure
import com.intalalab.wsnmonitoring.core.Resource
import retrofit2.Response

abstract class BaseDataSource {

    /**
     * handle request
     */
    protected suspend fun <T> handleRequest(callback: suspend () -> Response<T>): Resource<T> {
        return try {
            val response = callback()

            response.body()?.let {
                Resource.success(it)
            } ?: Resource.error(null, Failure.EmptyResponse)

        } catch (exception: Failure) {
            return Resource.error(null, exception)
        }
    }
}