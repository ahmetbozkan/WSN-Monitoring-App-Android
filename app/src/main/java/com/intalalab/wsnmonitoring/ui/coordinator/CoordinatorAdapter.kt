package com.intalalab.wsnmonitoring.ui.coordinator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.intalalab.wsnmonitoring.R
import com.intalalab.wsnmonitoring.data.local.model.CoordinatorEntity
import com.intalalab.wsnmonitoring.databinding.RowCoordinatorItemBinding
import javax.inject.Inject

class CoordinatorAdapter @Inject constructor() :
    ListAdapter<CoordinatorEntity, CoordinatorAdapter.CoordinatorViewHolder>(diffUtil) {

    inner class CoordinatorViewHolder(private val binding: RowCoordinatorItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: CoordinatorEntity) {
            binding.model = model
            binding.index = adapterPosition
        }
    }

    var click: ((wsnId: Long) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoordinatorViewHolder =
        CoordinatorViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_coordinator_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CoordinatorViewHolder, position: Int) {
        val item = getItem(position)

        if (item != null) {
            holder.bind(item)

            holder.itemView.setOnClickListener {
                click?.invoke(item.id)
            }
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<CoordinatorEntity>() {
            override fun areItemsTheSame(
                oldItem: CoordinatorEntity,
                newItem: CoordinatorEntity
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: CoordinatorEntity,
                newItem: CoordinatorEntity
            ): Boolean =
                oldItem == newItem
        }
    }

}