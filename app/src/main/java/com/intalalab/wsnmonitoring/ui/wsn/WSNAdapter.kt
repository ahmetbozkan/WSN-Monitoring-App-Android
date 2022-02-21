package com.intalalab.wsnmonitoring.ui.wsn

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.intalalab.wsnmonitoring.R
import com.intalalab.wsnmonitoring.data.local.model.WSNEntity
import com.intalalab.wsnmonitoring.databinding.RowWsnItemBinding
import javax.inject.Inject

class WSNAdapter @Inject constructor() :
    ListAdapter<WSNEntity, WSNAdapter.WSNViewHolder>(diffUtil) {

    inner class WSNViewHolder(private val binding: RowWsnItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: WSNEntity) {
            binding.model = model
            binding.index = adapterPosition
        }
    }

    var click: ((wsnId: Long) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WSNViewHolder =
        WSNViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_wsn_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: WSNViewHolder, position: Int) {
        val item = getItem(position)

        if (item != null) {
            holder.bind(item)

            holder.itemView.setOnClickListener {
                click?.invoke(item.id)
            }
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<WSNEntity>() {
            override fun areItemsTheSame(oldItem: WSNEntity, newItem: WSNEntity): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: WSNEntity, newItem: WSNEntity): Boolean =
                oldItem == newItem
        }
    }

}