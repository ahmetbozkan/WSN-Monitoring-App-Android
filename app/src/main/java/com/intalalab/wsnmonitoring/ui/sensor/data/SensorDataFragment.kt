package com.intalalab.wsnmonitoring.ui.sensor.data

import android.hardware.Sensor
import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.intalalab.wsnmonitoring.R
import com.intalalab.wsnmonitoring.base.BaseFragment
import com.intalalab.wsnmonitoring.databinding.FragmentSensorDataBinding

class SensorDataFragment: BaseFragment<FragmentSensorDataBinding, SensorDataViewModel>() {

    override val viewModel: SensorDataViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_sensor_data

    private val args: SensorDataFragmentArgs by navArgs()

    override fun initialize(savedInstanceState: Bundle?) {

    }
}