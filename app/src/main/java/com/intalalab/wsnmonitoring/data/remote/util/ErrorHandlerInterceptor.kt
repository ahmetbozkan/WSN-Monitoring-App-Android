package com.intalalab.wsnmonitoring.data.remote.util

import android.content.Context
import android.util.Log
import com.intalalab.wsnmonitoring.core.Failure
import com.intalalab.wsnmonitoring.util.extension.isNetworkAvailable
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.HttpException
import java.io.EOFException
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ErrorHandlerInterceptor constructor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!context.isNetworkAvailable())
            throw Failure.NoConnectivityError

        val response = try {
            chain.proceed(chain.request())
        }
        catch (exception: Exception) {
            throw when(exception) {
                is UnknownHostException, is IllegalArgumentException -> Failure.UnknownHostError()
                is HttpException -> Failure.HttpError(exception.code(), exception.message())
                is SocketTimeoutException -> Failure.TimeOutError(exception.message)
                is EOFException -> Failure.NullResponse(exception.message)

                else -> Failure.GeneralError(exception.message)
            }
        }

        return when(response.isSuccessful) {
            true -> {
                response.body?.let {
                    response
                } ?: run {
                    throw Failure.EmptyResponse
                }

            }

            false -> throw Failure.HttpError(response.code, response.message)
        }

    }

}