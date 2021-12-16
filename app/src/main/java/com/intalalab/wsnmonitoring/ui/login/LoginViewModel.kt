package com.intalalab.wsnmonitoring.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.intalalab.wsnmonitoring.base.BaseViewModel
import com.intalalab.wsnmonitoring.core.Status
import com.intalalab.wsnmonitoring.data.remote.model.login.LoginRequestBody
import com.intalalab.wsnmonitoring.data.remote.model.login.LoginResponseModel
import com.intalalab.wsnmonitoring.domain.usecase.LoginUseCase
import com.intalalab.wsnmonitoring.domain.usecase.StoreUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val storeUserInfoUseCase: StoreUserInfoUseCase
) : BaseViewModel() {

    private val _login = MutableLiveData<Boolean>()
    val login: LiveData<Boolean> get() = _login

    fun login(loginRequestBody: LoginRequestBody) =
        viewModelScope.launch(Dispatchers.IO + genericExceptionHandler) {
            enableLoading()

            val response = loginUseCase.invoke(loginRequestBody)

            when (response.status) {
                Status.SUCCESS -> {
                    storeUserId(response.data!!)

                    _login.postValue(true)
                }
                Status.ERROR -> {
                    manageException(response.error)
                }
            }

            disableLoading()
        }

    private suspend fun storeUserId(userModel: LoginResponseModel) {
        storeUserInfoUseCase.invoke(userModel = userModel)
    }


}