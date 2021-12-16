package com.intalalab.wsnmonitoring.ui.coordinator

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.intalalab.wsnmonitoring.R
import com.intalalab.wsnmonitoring.base.BaseFragment
import com.intalalab.wsnmonitoring.data.local.model.CoordinatorEntity
import com.intalalab.wsnmonitoring.data.remote.model.coordinator.CoordinatorRequestBody
import com.intalalab.wsnmonitoring.data.remote.model.login.LoginResponseModel
import com.intalalab.wsnmonitoring.databinding.FragmentCoordinatorBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CoordinatorFragment : BaseFragment<FragmentCoordinatorBinding, CoordinatorViewModel>() {

    override val viewModel: CoordinatorViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_coordinator

    private val args: CoordinatorFragmentArgs by navArgs()

    @Inject
    lateinit var coordinatorAdapter: CoordinatorAdapter

    override fun initialize(savedInstanceState: Bundle?) {

        initRecyclerView()

        observeLiveData()

    }

    private fun initRecyclerView() {
        binding.rcvCoordinator.apply {
            adapter = coordinatorAdapter
            setHasFixedSize(true)
        }

        coordinatorAdapter.click = object : (Long) -> Unit {
            override fun invoke(coordinatorId: Long) {
                val action = CoordinatorFragmentDirections
                    .actionCoordinatorFragmentToRouterFragment(coordinatorId)

                findNavController().navigate(action)
            }

        }
    }

    private fun observeLiveData() {
        viewModel.userInfo.observe(viewLifecycleOwner, ::observeUserInfo)

        viewModel.coordinators.observe(viewLifecycleOwner, ::observeCoordinators)
    }

    private fun observeUserInfo(userInfo: LoginResponseModel?) {
        if (userInfo != null)
            viewModel.getCoordinators(
                CoordinatorRequestBody(id = args.wsnId, userInfo = userInfo)
            )
    }

    private fun observeCoordinators(list: List<CoordinatorEntity>) {
        coordinatorAdapter.submitList(list)
    }
}