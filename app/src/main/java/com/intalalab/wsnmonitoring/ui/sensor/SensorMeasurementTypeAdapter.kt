package com.intalalab.wsnmonitoring.ui.sensor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.intalalab.wsnmonitoring.R
import com.intalalab.wsnmonitoring.data.local.model.SensorMeasurementTypeEntity
import com.intalalab.wsnmonitoring.databinding.RowSensorMeasurementTypeItemBinding
import javax.inject.Inject

class SensorMeasurementTypeAdapter @Inject constructor() :
    ListAdapter<SensorMeasurementTypeEntity, SensorMeasurementTypeAdapter.SensorMeasurementTypeViewHolder>(
        diffUtil
    ) {

    inner class SensorMeasurementTypeViewHolder(private val binding: RowSensorMeasurementTypeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: SensorMeasurementTypeEntity) {
            binding.model = model
        }
    }

    var click: ((wsnId: Long) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SensorMeasurementTypeViewHolder = SensorMeasurementTypeViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.row_sensor_measurement_type_item,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: SensorMeasurementTypeViewHolder, position: Int) {
        val item = getItem(position)

        if (item != null) {
            holder.bind(item)

            holder.itemView.setOnClickListener {
                click?.invoke(item.measurementTypeId)
            }
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<SensorMeasurementTypeEntity>() {
            override fun areItemsTheSame(
                oldItem: SensorMeasurementTypeEntity,
                newItem: SensorMeasurementTypeEntity
            ): Boolean =
                oldItem.measurementTypeId == newItem.measurementTypeId

            override fun areContentsTheSame(
                oldItem: SensorMeasurementTypeEntity,
                newItem: SensorMeasurementTypeEntity
            ): Boolean =
                oldItem == newItem
        }
    }

}