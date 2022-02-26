package com.intalalab.wsnmonitoring.ui.detail

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.intalalab.wsnmonitoring.R
import com.intalalab.wsnmonitoring.base.BaseDialogFragment
import com.intalalab.wsnmonitoring.data.local.model.ItemDetailModel
import com.intalalab.wsnmonitoring.databinding.DialogFragmentItemDetailBinding
import com.intalalab.wsnmonitoring.util.extension.openMap

class ItemDetailDialogFragment :
    BaseDialogFragment<DialogFragmentItemDetailBinding, ItemDetailViewModel>() {

    override val viewModel: ItemDetailViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.dialog_fragment_item_detail

    private val args: ItemDetailDialogFragmentArgs by navArgs()

    private var latitude: Double = 0.0
    private var longitude: Double = 0.0

    override fun initialize(savedInstanceState: Bundle?) {

        getArgs()

        manageClick()

    }

    private fun manageClick() {
        binding.apply {
            imgClose.setOnClickListener {
                navigateUp()
            }

            linearLayoutLocationContainer.setOnClickListener {
                requireContext().openMap(latitude, longitude)
            }
        }
    }

    private fun getArgs() {
        val item = args.model

        setFields(item)
    }

    private fun setFields(item: ItemDetailModel) {
        binding.model = item

        latitude = item.latitude
        longitude = item.longitude
    }
}