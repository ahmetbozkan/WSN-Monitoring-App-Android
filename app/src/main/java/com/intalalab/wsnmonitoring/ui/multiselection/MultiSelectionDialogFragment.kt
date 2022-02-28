package com.intalalab.wsnmonitoring.ui.multiselection

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.intalalab.wsnmonitoring.databinding.DialogFragmentMultiSelectionBinding
import com.intalalab.wsnmonitoring.ui.multiselection.MultiSelectionDialogModel.Companion.SINGLE_BUTTON_DIALOG_BUTTON_ACTION_KEY
import com.intalalab.wsnmonitoring.ui.multiselection.MultiSelectionDialogModel.Companion.SINGLE_BUTTON_DIALOG_RETURN_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MultiSelectionDialogFragment : DialogFragment() {

    private var _binding: DialogFragmentMultiSelectionBinding? = null
    val binding: DialogFragmentMultiSelectionBinding get() = _binding!!

    private val args: MultiSelectionDialogFragmentArgs by navArgs()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)

        return dialog
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NO_TITLE, android.R.style.Theme_Material_Dialog_NoActionBar_MinWidth)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogFragmentMultiSelectionBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFields()

        manageClick()

    }

    private fun setFields() {
        val model = args.model

        binding.model = model
    }

    private fun manageClick() {
        binding.btnRightButton.setOnClickListener {
            setFragmentResultAndDismiss(MultiSelectionType.SELECTION_RIGHT)
        }

        binding.btnLeftButton.setOnClickListener {
            setFragmentResultAndDismiss(MultiSelectionType.SELECTION_LEFT)
        }

        binding.imgClose.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setFragmentResultAndDismiss(selection: MultiSelectionType) {
        findNavController().navigateUp()

        setFragmentResult(
            SINGLE_BUTTON_DIALOG_RETURN_KEY,
            bundleOf(SINGLE_BUTTON_DIALOG_BUTTON_ACTION_KEY to selection)
        )
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}