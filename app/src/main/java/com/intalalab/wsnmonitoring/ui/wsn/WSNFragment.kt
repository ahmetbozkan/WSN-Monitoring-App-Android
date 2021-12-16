package com.intalalab.wsnmonitoring.ui.wsn

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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

    }

    private fun initRecyclerView() {
        binding.rcvWsn.apply {
            adapter = wsnAdapter
            setHasFixedSize(true)
        }

        wsnAdapter.click = object : ((Long) -> Unit) {
            override fun invoke(wsnId: Long) {
                val action = WSNFragmentDirections
                    .actionLandingFragmentToCoordinatorFragment(wsnId)

                findNavController().navigate(action)
            }

        }
    }

    private fun observeLiveData() {
        viewModel.wsnList.observe(viewLifecycleOwner, ::observeWSNData)
    }

    private fun observeWSNData(wsnData: List<WSNEntity>) {
        wsnAdapter.submitList(wsnData)
    }
}