package com.intalalab.wsnmonitoring.ui.sensor.data

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.intalalab.wsnmonitoring.R
import com.intalalab.wsnmonitoring.base.BaseFragment
import com.intalalab.wsnmonitoring.data.local.model.SensorDataEntity
import com.intalalab.wsnmonitoring.data.remote.model.login.LoginResponseModel
import com.intalalab.wsnmonitoring.data.remote.model.sensor.data.SensorDataRequestBody
import com.intalalab.wsnmonitoring.databinding.FragmentSensorDataBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SensorDataFragment : BaseFragment<FragmentSensorDataBinding, SensorDataViewModel>() {

    override val viewModel: SensorDataViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_sensor_data

    private val args: SensorDataFragmentArgs by navArgs()

    override fun initialize(savedInstanceState: Bundle?) {

        observeLiveData()

    }

    private fun observeLiveData() {
        viewModel.userInfo.observe(viewLifecycleOwner, ::observeUserInfo)

        viewModel.sensorData.observe(viewLifecycleOwner, ::observeSensorData)
    }

    private fun observeUserInfo(userResponseModel: LoginResponseModel) {
        viewModel.getSensorData(
            SensorDataRequestBody(
                userInfo = userResponseModel,
                routerId = args.routerId,
                sensorId = args.sensorId,
                sensorMeasurementTypeId = args.sensorMeasurementTypeId
            )
        )
    }

    private fun observeSensorData(list: List<SensorDataEntity>) {

    }
}