package com.intalalab.wsnmonitoring.ui.wsn

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.intalalab.wsnmonitoring.R
import com.intalalab.wsnmonitoring.base.BaseFragment
import com.intalalab.wsnmonitoring.data.local.model.WSNEntity
import com.intalalab.wsnmonitoring.databinding.FragmentWsnBinding
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
            SweetAlertDialog(requireContext(), SweetAlertDialog.WARNING_TYPE).apply {
                titleText = "Log out"
                contentText = "Are you sure want to exit?"
                confirmText = "Yes"

                setConfirmClickListener {
                    dismiss()
                    logout()
                }
                cancelText = "Cancel"
                setCancelClickListener {
                    dismiss()
                }
                show()
            }
        }
    }

    private fun logout() {
        viewModel.onLogoutClick()

        findNavController().popBackStack()
        findNavController().navigate(
            WSNFragmentDirections.actionGlobalWelcomeFragment()
        )
    }
}