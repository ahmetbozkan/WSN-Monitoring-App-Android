package com.intalalab.wsnmonitoring.ui.wsn

import android.os.Bundle
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.intalalab.wsnmonitoring.R
import com.intalalab.wsnmonitoring.base.BaseFragment
import com.intalalab.wsnmonitoring.data.local.model.WSNEntity
import com.intalalab.wsnmonitoring.databinding.FragmentWsnBinding
import com.intalalab.wsnmonitoring.ui.multiselection.MultiSelectionDialogModel
import com.intalalab.wsnmonitoring.ui.multiselection.MultiSelectionType
import com.intalalab.wsnmonitoring.util.extension.setProgress
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WSNFragment : BaseFragment<FragmentWsnBinding, WSNViewModel>() {

    override val viewModel: WSNViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_wsn

    @Inject
    lateinit var wsnAdapter: WSNAdapter

    override fun initialize(savedInstanceState: Bundle?) {

        initRecyclerView()

        observeLiveData()

        manageToolbarClick()

        setCustomProgress()

    }

    private fun observeLiveData() {
        viewModel.wsnList.observe(viewLifecycleOwner, ::observeWSNData)
    }

    private fun observeWSNData(wsnData: List<WSNEntity>) {
        wsnAdapter.submitList(wsnData)
    }

    private fun initRecyclerView() {
        binding.rcvWsn.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = wsnAdapter
            setHasFixedSize(true)
        }

        wsnAdapter.click = object : ((WSNEntity) -> Unit) {
            override fun invoke(model: WSNEntity) {
                val action = WSNFragmentDirections
                    .actionLandingFragmentToCoordinatorFragment(model)

                findNavController().navigate(action)
            }

        }
    }

    private fun manageToolbarClick() {
        binding.imgLogout.setOnClickListener {
            val action = WSNFragmentDirections.actionGlobalMultiSelectionDialogFragment(
                MultiSelectionDialogModel(
                    "LOGOUT",
                    "Are you sure want to logout?",
                    "LOG OUT",
                    "CANCEL",
                    true
                )
            )

            subscribeToFinishLoadProcessDialogFragment()

            navigate(action)
        }
    }

    private fun subscribeToFinishLoadProcessDialogFragment() {
        setFragmentResultListener(MultiSelectionDialogModel.SINGLE_BUTTON_DIALOG_RETURN_KEY) { _, bundle ->
            val selection = bundle.getSerializable(
                MultiSelectionDialogModel.SINGLE_BUTTON_DIALOG_BUTTON_ACTION_KEY
            ) as MultiSelectionType

            if (selection == MultiSelectionType.SELECTION_RIGHT) logout()
        }
    }

    private fun logout() {
        viewModel.onLogoutClick()

        findNavController().popBackStack()
        findNavController().navigate(
            WSNFragmentDirections.actionGlobalWelcomeFragment()
        )
    }

    private fun setCustomProgress() {
        setProgress(requireView(), R.id.progress_bar)
    }
}