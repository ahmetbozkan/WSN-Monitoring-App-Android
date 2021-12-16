package com.intalalab.wsnmonitoring.ui.router

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.intalalab.wsnmonitoring.base.BaseViewModel
import com.intalalab.wsnmonitoring.core.Status
import com.intalalab.wsnmonitoring.data.local.model.RouterEntity
import com.intalalab.wsnmonitoring.data.remote.model.login.LoginResponseModel
import com.intalalab.wsnmonitoring.data.remote.model.router.RouterRequestBody
import com.intalalab.wsnmonitoring.domain.usecase.GetRouterByCoordinatorUseCase
import com.intalalab.wsnmonitoring.domain.usecase.GetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RouterViewModel @Inject constructor(
    private val getRouterByCoordinatorUseCase: GetRouterByCoordinatorUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase
) : BaseViewModel() {

    private val _routers = MutableLiveData<List<RouterEntity>>()
    val routers: LiveData<List<RouterEntity>> get() = _routers

    private val _userInfo = MutableLiveData<LoginResponseModel?>()
    val userInfo: LiveData<LoginResponseModel?> get() = _userInfo

    init {
        val userInfo = getUserInfoUseCase.invoke()

        if (userInfo != null) {
            _userInfo.postValue(userInfo)
        }
    }

    fun getRouters(routerRequestBody: RouterRequestBody) =
        viewModelScope.launch(Dispatchers.IO + genericExceptionHandler) {
            enableLoading()

            val response = getRouterByCoordinatorUseCase.invoke(routerRequestBody)

            when (response.status) {
                Status.SUCCESS -> {
                    _routers.postValue(response.data!!)
                }
                Status.ERROR -> {
                    manageException(response.error)
                }
            }

            disableLoading()
        }


}