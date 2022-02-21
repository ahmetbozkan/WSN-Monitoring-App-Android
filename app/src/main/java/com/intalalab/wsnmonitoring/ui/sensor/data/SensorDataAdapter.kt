package com.intalalab.wsnmonitoring.ui.sensor.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.intalalab.wsnmonitoring.R
import com.intalalab.wsnmonitoring.data.local.model.SensorDataEntity
import com.intalalab.wsnmonitoring.databinding.RowSensorDataItemBinding
import javax.inject.Inject

class SensorDataAdapter @Inject constructor() :
    ListAdapter<SensorDataEntity, SensorDataAdapter.RouterViewHolder>(diffUtil) {

    inner class RouterViewHolder(private val binding: RowSensorDataItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: SensorDataEntity) {
            binding.model = model
            binding.index = adapterPosition
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouterViewHolder =
        RouterViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_sensor_data_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RouterViewHolder, position: Int) {
        val item = getItem(position)

        if (item != null) {
            holder.bind(item)
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<SensorDataEntity>() {
            override fun areItemsTheSame(
                oldItem: SensorDataEntity,
                newItem: SensorDataEntity
            ): Boolean = true

            override fun areContentsTheSame(
                oldItem: SensorDataEntity,
                newItem: SensorDataEntity
            ): Boolean = true
        }
    }

}