package com.intalalab.wsnmonitoring.ui.coordinator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.intalalab.wsnmonitoring.base.BaseViewModel
import com.intalalab.wsnmonitoring.core.Status
import com.intalalab.wsnmonitoring.data.local.model.CoordinatorEntity
import com.intalalab.wsnmonitoring.data.remote.model.coordinator.CoordinatorRequestBody
import com.intalalab.wsnmonitoring.data.remote.model.login.LoginResponseModel
import com.intalalab.wsnmonitoring.domain.usecase.GetCoordinatorByWsnUseCase
import com.intalalab.wsnmonitoring.domain.usecase.GetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoordinatorViewModel @Inject constructor(
    private val getCoordinatorByWsnUseCase: GetCoordinatorByWsnUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase
) : BaseViewModel() {

    private val _userInfo = MutableLiveData<LoginResponseModel?>()
    val userInfo: LiveData<LoginResponseModel?> get() = _userInfo

    private val _coordinators = MutableLiveData<List<CoordinatorEntity>>()
    val coordinators: LiveData<List<CoordinatorEntity>> get() = _coordinators

    init {
        val userInfo = getUserInfoUseCase.invoke()

        if (userInfo != null) {
            _userInfo.postValue(userInfo)
        }
    }

    fun getCoordinators(coordinatorRequestBody: CoordinatorRequestBody) =
        viewModelScope.launch(Dispatchers.IO + genericExceptionHandler) {
            enableLoading()

            val response = getCoordinatorByWsnUseCase.invoke(coordinatorRequestBody)

            when (response.status) {
                Status.SUCCESS -> {
                    _coordinators.postValue(response.data!!)
                }
                Status.ERROR -> {
                    manageException(response.error)
                }
            }

            disableLoading()
        }

}