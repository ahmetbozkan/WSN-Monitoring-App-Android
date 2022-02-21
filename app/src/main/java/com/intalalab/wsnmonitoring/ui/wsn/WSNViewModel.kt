package com.intalalab.wsnmonitoring.ui.wsn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.intalalab.wsnmonitoring.base.BaseViewModel
import com.intalalab.wsnmonitoring.core.Status
import com.intalalab.wsnmonitoring.data.local.model.WSNEntity
import com.intalalab.wsnmonitoring.data.remote.model.login.LoginResponseModel
import com.intalalab.wsnmonitoring.domain.usecase.GetUserInfoUseCase
import com.intalalab.wsnmonitoring.domain.usecase.GetWSNUseCase
import com.intalalab.wsnmonitoring.domain.usecase.ResetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WSNViewModel @Inject constructor(
    private val getWSNUseCase: GetWSNUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val resetUserInfoUseCase: ResetUserInfoUseCase
) : BaseViewModel() {

    private val _wsnList = MutableLiveData<List<WSNEntity>>()
    val wsnList: LiveData<List<WSNEntity>> get() = _wsnList

    init {
        val userInfo = getUserInfoUseCase.invoke()

        if (userInfo != null)
            getWSN(userInfo)
    }

    private fun getWSN(wsnRequestModel: LoginResponseModel) =
        viewModelScope.launch(Dispatchers.IO + genericExceptionHandler) {
            enableLoading()

            val response = getWSNUseCase.invoke(wsnRequestModel)

            when (response.status) {
                Status.SUCCESS -> {
                    _wsnList.postValue(response.data!!)
                }
                Status.ERROR -> {
                    manageException(response.error)
                }
            }

            disableLoading()
        }

    fun onLogoutClick() = viewModelScope.launch(Dispatchers.IO + genericExceptionHandler) {
        resetUserInfoUseCase.invoke()
    }

}