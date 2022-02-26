package com.intalalab.wsnmonitoring.ui.router

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.intalalab.wsnmonitoring.R
import com.intalalab.wsnmonitoring.data.local.model.RouterEntity
import com.intalalab.wsnmonitoring.databinding.RowRouterItemBinding
import com.intalalab.wsnmonitoring.util.AdapterSelectionType
import javax.inject.Inject

class RouterAdapter @Inject constructor() :
    ListAdapter<RouterEntity, RouterAdapter.RouterViewHolder>(diffUtil) {

    inner class RouterViewHolder(val binding: RowRouterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: RouterEntity) {
            binding.model = model
            binding.index = adapterPosition
        }
    }

    var click: ((entity: RouterEntity, selectionType: AdapterSelectionType) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouterViewHolder =
        RouterViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_router_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RouterViewHolder, position: Int) {
        val item = getItem(position)

        if (item != null) {
            holder.bind(item)

            holder.itemView.setOnClickListener {
                click?.invoke(item, AdapterSelectionType.NAVIGATE_FORWARD)
            }

            holder.binding.tvDetail.setOnClickListener {
                click?.invoke(item, AdapterSelectionType.DETAIL)
            }
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<RouterEntity>() {
            override fun areItemsTheSame(
                oldItem: RouterEntity,
                newItem: RouterEntity
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: RouterEntity,
                newItem: RouterEntity
            ): Boolean =
                oldItem == newItem
        }
    }

}