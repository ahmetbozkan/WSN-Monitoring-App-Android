package com.intalalab.wsnmonitoring.ui.sensor

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.intalalab.wsnmonitoring.R
import com.intalalab.wsnmonitoring.base.BaseFragment
import com.intalalab.wsnmonitoring.data.local.model.SensorEntity
import com.intalalab.wsnmonitoring.data.remote.model.login.LoginResponseModel
import com.intalalab.wsnmonitoring.data.remote.model.sensor.SensorRequestBody
import com.intalalab.wsnmonitoring.databinding.FragmentSensorBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SensorFragment : BaseFragment<FragmentSensorBinding, SensorViewModel>() {

    override val viewModel: SensorViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_sensor

    private val args: SensorFragmentArgs by navArgs()

    @Inject
    lateinit var sensorAdapter: SensorAdapter

    override fun initialize(savedInstanceState: Bundle?) {

        initRecyclerView()

        observeLiveData()

    }

    private fun initRecyclerView() {
        binding.rcvSensor.apply {
            setHasFixedSize(true)
            adapter = sensorAdapter
        }
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
        sensorAdapter.submitList(sensors)
    }
}