package com.intalalab.wsnmonitoring.ui.sensor

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.intalalab.wsnmonitoring.R
import com.intalalab.wsnmonitoring.base.BaseFragment
import com.intalalab.wsnmonitoring.data.local.model.SensorEntity
import com.intalalab.wsnmonitoring.data.remote.model.login.LoginResponseModel
import com.intalalab.wsnmonitoring.data.remote.model.sensor.SensorRequestBody
import com.intalalab.wsnmonitoring.databinding.FragmentSensorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SensorFragment: BaseFragment<FragmentSensorBinding, SensorViewModel>() {

    override val viewModel: SensorViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_sensor
    
    private val args: SensorFragmentArgs by navArgs()

    override fun initialize(savedInstanceState: Bundle?) {

        observeLiveData()

    }

    private fun observeLiveData() {
        viewModel.userInfo.observe(viewLifecycleOwner, ::observeUserInfo)
        
        viewModel.sensors.observe(viewLifecycleOwner, ::observeSensors)
    }

    private fun observeUserInfo(userResponseModel: LoginResponseModel) {
        viewModel.getSensors(
            SensorRequestBody(routerId = args.routerId, userResponseModel)
        )
    }

    private fun observeSensors(sensors: List<SensorEntity>) {
        Log.d("TAG", "observeSensors: $sensors")
    }
}