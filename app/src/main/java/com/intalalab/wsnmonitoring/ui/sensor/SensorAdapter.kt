package com.intalalab.wsnmonitoring.ui.sensor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.intalalab.wsnmonitoring.R
import com.intalalab.wsnmonitoring.data.local.model.SensorEntity
import com.intalalab.wsnmonitoring.databinding.RowSensorItemBinding
import com.intalalab.wsnmonitoring.util.extension.goneView
import com.intalalab.wsnmonitoring.util.extension.showView
import javax.inject.Inject

class SensorAdapter @Inject constructor() :
    ListAdapter<SensorEntity, SensorAdapter.SensorViewHolder>(diffUtil) {

    inner class SensorViewHolder(val binding: RowSensorItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: SensorEntity) {
            binding.model = model
        }
    }

    var click: ((sensorId: Long, sensorMeasurementTypeId: Long) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SensorViewHolder =
        SensorViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_sensor_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: SensorViewHolder, position: Int) {
        val item = getItem(position)

        if (item != null) {
            holder.bind(item)

            val measurementTypeAdapter = SensorMeasurementTypeAdapter()

            initSensorMeasurementTypeRecyclerView(holder, item, measurementTypeAdapter)

            measurementTypeAdapter.click = object : (Long) -> Unit {
                override fun invoke(measurementTypeId: Long) {
                    click?.invoke(item.id, measurementTypeId)
                }

            }
        }
    }

    private fun initSensorMeasurementTypeRecyclerView(
        holder: SensorViewHolder,
        item: SensorEntity,
        measurementTypeAdapter: SensorMeasurementTypeAdapter
    ) {
        holder.binding.apply {
            imgExpandMeasurementTypes.setOnClickListener {
                if (rcvSensorMeasurementTypes.visibility == View.GONE)
                    rcvSensorMeasurementTypes.showView()
                else
                    rcvSensorMeasurementTypes.goneView()
            }

            rcvSensorMeasurementTypes.apply {
                setHasFixedSize(true)
                adapter = measurementTypeAdapter
            }
        }

        if (!item.measurementTypes.isNullOrEmpty())
            measurementTypeAdapter.submitList(item.measurementTypes)
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<SensorEntity>() {
            override fun areItemsTheSame(
                oldItem: SensorEntity,
                newItem: SensorEntity
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: SensorEntity,
                newItem: SensorEntity
            ): Boolean =
                oldItem == newItem
        }
    }

}