package com.intalalab.wsnmonitoring.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.intalalab.wsnmonitoring.base.BaseViewModel
import com.intalalab.wsnmonitoring.core.Status
import com.intalalab.wsnmonitoring.data.remote.model.login.RegisterRequestBody
import com.intalalab.wsnmonitoring.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : BaseViewModel() {

    private val _register = MutableLiveData<Boolean>()
    val register: LiveData<Boolean> get() = _register

    fun register(registerRequestBody: RegisterRequestBody) =
        viewModelScope.launch(Dispatchers.IO + genericExceptionHandler) {
            enableLoading()

            val request = registerUseCase.invoke(registerRequestBody)

            when (request.status) {
                Status.SUCCESS -> {
                    _register.postValue(true)
                }
                Status.ERROR -> {
                    manageException(request.error)
                }
            }

            disableLoading()
        }

}