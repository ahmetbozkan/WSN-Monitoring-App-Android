package com.intalalab.wsnmonitoring.ui.sensor.detail

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.intalalab.wsnmonitoring.R
import com.intalalab.wsnmonitoring.base.BaseDialogFragment
import com.intalalab.wsnmonitoring.data.local.model.SensorEntity
import com.intalalab.wsnmonitoring.databinding.DialogFragmentSensorDetailBinding
import com.intalalab.wsnmonitoring.ui.sensor.SensorMeasurementTypeAdapter
import com.intalalab.wsnmonitoring.ui.sensor.SensorViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SensorDetailDialogFragment :
    BaseDialogFragment<DialogFragmentSensorDetailBinding, SensorViewModel>() {

    override val viewModel: SensorViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.dialog_fragment_sensor_detail

    private val args: SensorDetailDialogFragmentArgs by navArgs()

    @Inject
    lateinit var sensorMeasurementTypeAdapter: SensorMeasurementTypeAdapter

    override fun initialize(savedInstanceState: Bundle?) {

        getArgs()

        manageClick()

    }

    private fun getArgs() {
        val sensor = args.sensor

        binding.model = sensor

        initRecyclerView(sensor)
    }

    private fun manageClick() {
        binding.apply {
            imgClose.setOnClickListener {
                navigateUp()
            }
        }
    }

    private fun initRecyclerView(sensor: SensorEntity) {
        binding.rcvSensorMeasurementTypes.apply {
            adapter = sensorMeasurementTypeAdapter
            setHasFixedSize(true)
        }

        sensorMeasurementTypeAdapter.click = object : (Long) -> Unit {
            override fun invoke(id: Long) {
                val action = SensorDetailDialogFragmentDirections
                    .actionSensorDetailDialogFragmentToSensorDataFragment(
                        args.routerId,
                        args.sensor.id,
                        id,
                        args.sensor
                    )

                navigate(action)
            }

        }

        sensorMeasurementTypeAdapter.submitList(sensor.measurementTypes)
    }
}