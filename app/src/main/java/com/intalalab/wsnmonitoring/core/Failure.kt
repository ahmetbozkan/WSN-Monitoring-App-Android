package com.intalalab.wsnmonitoring.core

import java.io.IOException

sealed class Failure : IOException() {

    class UnknownHostError : Failure()
    class HttpError(var code: Int, override var message: String) : Failure()
    class TimeOutError(override var message: String?) : Failure()
    class CustomFailure(override var message: String) : Failure()
    object NoConnectivityError : Failure()
    object NoSuchElementException : Failure()
    object EmptyResponse : Failure()
    class NullResponse(override var message: String?) : Failure()
    class GeneralError(override var message: String?) : Failure()
}