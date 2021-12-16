package com.intalalab.wsnmonitoring.ui.sensor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.intalalab.wsnmonitoring.base.BaseViewModel
import com.intalalab.wsnmonitoring.core.Status
import com.intalalab.wsnmonitoring.data.local.model.SensorEntity
import com.intalalab.wsnmonitoring.data.local.sharedpreferences.SharedPreferencesHelper
import com.intalalab.wsnmonitoring.data.remote.model.login.LoginResponseModel
import com.intalalab.wsnmonitoring.data.remote.model.sensor.SensorRequestBody
import com.intalalab.wsnmonitoring.domain.usecase.GetSensorsByRouterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SensorViewModel @Inject constructor(
    private val getSensorsByRouterUseCase: GetSensorsByRouterUseCase,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : BaseViewModel() {

    private val _sensors = MutableLiveData<List<SensorEntity>>()
    val sensors: LiveData<List<SensorEntity>> get() = _sensors

    private val _userInfo = MutableLiveData<LoginResponseModel>()
    val userInfo: LiveData<LoginResponseModel> get() = _userInfo

    init {
        val userInfo = sharedPreferencesHelper.getUserInfo()

        if (userInfo != null) {
            _userInfo.postValue(userInfo!!)
        }
    }

    fun getSensors(sensorRequestBody: SensorRequestBody) =
        viewModelScope.launch(Dispatchers.IO + genericExceptionHandler) {
            enableLoading()

            val response = getSensorsByRouterUseCase.invoke(sensorRequestBody)

            when (response.status) {
                Status.SUCCESS -> {
                    _sensors.postValue(response.data!!)
                }
                Status.ERROR -> {
                    manageException(response.error)
                }
            }

            disableLoading()
        }
}