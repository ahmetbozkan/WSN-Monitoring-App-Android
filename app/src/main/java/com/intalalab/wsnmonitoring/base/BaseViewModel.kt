package com.intalalab.wsnmonitoring.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.intalalab.wsnmonitoring.core.Failure
import com.intalalab.wsnmonitoring.util.SingleLiveEvent
import kotlinx.coroutines.CoroutineExceptionHandler

private const val TAG = "BaseViewModel"

abstract class BaseViewModel : ViewModel() {

    private val _failure = SingleLiveEvent<Failure>()
    val failure: LiveData<Failure> get() = _failure

    val genericExceptionHandler = CoroutineExceptionHandler { _, error ->
        disableLoading()

        _failure.postValue(
            Failure.GeneralError(error.message)
        )
    }

    val isLoading = MutableLiveData<Boolean>()

    protected fun manageException(error: Failure?) {
        error?.let {
            _failure.postValue(it)
        }
    }

    protected fun disableLoading() {
        if (isLoading.value == true)
            isLoading.postValue(false)
    }

    protected fun enableLoading() {
        isLoading.postValue(true)
    }

}