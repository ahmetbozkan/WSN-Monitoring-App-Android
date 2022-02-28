package com.intalalab.wsnmonitoring.ui.sensor

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.intalalab.wsnmonitoring.R
import com.intalalab.wsnmonitoring.base.BaseFragment
import com.intalalab.wsnmonitoring.cv.ClickManager
import com.intalalab.wsnmonitoring.data.local.model.SensorEntity
import com.intalalab.wsnmonitoring.data.remote.model.login.LoginResponseModel
import com.intalalab.wsnmonitoring.data.remote.model.sensor.SensorRequestBody
import com.intalalab.wsnmonitoring.databinding.FragmentSensorBinding
import com.intalalab.wsnmonitoring.util.AdapterSelectionType
import com.intalalab.wsnmonitoring.util.extension.setProgress
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

        setFields()

        manageToolbarClick()

        setCustomProgress()

    }

    private fun initRecyclerView() {
        binding.rcvSensor.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
            adapter = sensorAdapter
        }

        sensorAdapter.click = object : (SensorEntity, AdapterSelectionType) -> Unit {
            override fun invoke(sensor: SensorEntity, selection: AdapterSelectionType) {
                if (selection == AdapterSelectionType.DETAIL) {
                    navigateToDetailFragment(sensor)
                }
            }
        }
    }

    private fun navigateToDetailFragment(sensor: SensorEntity) {
        val action = SensorFragmentDirections.actionSensorFragmentToSensorDetailDialogFragment(
            sensor,
            args.router.id
        )
        navigate(action)
    }

    private fun manageToolbarClick() {
        binding.toolbar.clickManager = object : ClickManager {
            override fun onBackClicked() {
                navigateUp()
            }

            override fun onSearchDone(text: String) {

            }
        }
    }

    private fun setFields() {
        binding.routerEntity = args.router
    }

    private fun observeLiveData() {
        viewModel.userInfo.observe(viewLifecycleOwner, ::observeUserInfo)

        viewModel.sensors.observe(viewLifecycleOwner, ::observeSensors)
    }

    private fun observeUserInfo(userResponseModel: LoginResponseModel) {
        viewModel.getSensors(
            SensorRequestBody(routerId = args.router.id, userResponseModel)
        )
    }

    private fun observeSensors(sensors: List<SensorEntity>) {
        sensorAdapter.submitList(sensors)
    }

    private fun setCustomProgress() {
        setProgress(requireView(), R.id.progress_bar)
    }
}