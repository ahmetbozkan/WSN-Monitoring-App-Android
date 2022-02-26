package com.intalalab.wsnmonitoring.ui.sensor.data

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.intalalab.wsnmonitoring.R
import com.intalalab.wsnmonitoring.base.BaseFragment
import com.intalalab.wsnmonitoring.cv.ClickManager
import com.intalalab.wsnmonitoring.data.local.model.SensorDataEntity
import com.intalalab.wsnmonitoring.data.remote.model.login.LoginResponseModel
import com.intalalab.wsnmonitoring.data.remote.model.sensor.data.SensorDataRequestBody
import com.intalalab.wsnmonitoring.databinding.FragmentSensorDataBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SensorDataFragment : BaseFragment<FragmentSensorDataBinding, SensorDataViewModel>() {

    override val viewModel: SensorDataViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_sensor_data

    private val args: SensorDataFragmentArgs by navArgs()

    @Inject
    lateinit var sensorDataAdapter: SensorDataAdapter

    override fun initialize(savedInstanceState: Bundle?) {

        initRecyclerView()

        observeLiveData()

        manageToolbarClick()

    }

    private fun initRecyclerView() {
        binding.rcvSensorData.apply {
            setHasFixedSize(true)
            adapter = sensorDataAdapter
        }
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
        sensorDataAdapter.submitList(list)
    }

    private fun manageToolbarClick() {
        binding.toolbar.clickManager = object : ClickManager {
            override fun onBackClicked() {
                navigateUp()
            }

            override fun onSearchDone(text: String) {
                TODO("Not yet implemented")
            }

        }
    }

}