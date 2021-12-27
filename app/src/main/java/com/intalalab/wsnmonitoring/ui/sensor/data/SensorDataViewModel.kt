package com.intalalab.wsnmonitoring.ui.sensor.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.intalalab.wsnmonitoring.base.BaseViewModel
import com.intalalab.wsnmonitoring.core.Status
import com.intalalab.wsnmonitoring.data.local.model.SensorDataEntity
import com.intalalab.wsnmonitoring.data.local.sharedpreferences.SharedPreferencesHelper
import com.intalalab.wsnmonitoring.data.remote.model.login.LoginResponseModel
import com.intalalab.wsnmonitoring.data.remote.model.sensor.data.SensorDataRequestBody
import com.intalalab.wsnmonitoring.domain.usecase.GetSensorDataByRouterAndSensorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SensorDataViewModel @Inject constructor(
    private val getSensorDataByRouterAndSensorUseCase: GetSensorDataByRouterAndSensorUseCase,
    sharedPreferencesHelper: SharedPreferencesHelper
) : BaseViewModel() {

    private val _userInfo = MutableLiveData<LoginResponseModel>()
    val userInfo: LiveData<LoginResponseModel> get() = _userInfo

    private val _sensorData = MutableLiveData<List<SensorDataEntity>>()
    val sensorData: LiveData<List<SensorDataEntity>> get() = _sensorData

    init {
        val userInfo = sharedPreferencesHelper.getUserInfo()

        if (userInfo != null) {
            _userInfo.postValue(userInfo!!)
        }
    }

    fun getSensorData(sensorDataRequestBody: SensorDataRequestBody) =
        viewModelScope.launch(Dispatchers.IO + genericExceptionHandler) {
            enableLoading()

            val response = getSensorDataByRouterAndSensorUseCase.invoke(sensorDataRequestBody)

            when (response.status) {
                Status.SUCCESS -> {
                    _sensorData.postValue(response.data!!)
                }
                Status.ERROR -> {
                    manageException(response.error)
                }
            }

            disableLoading()
        }
}