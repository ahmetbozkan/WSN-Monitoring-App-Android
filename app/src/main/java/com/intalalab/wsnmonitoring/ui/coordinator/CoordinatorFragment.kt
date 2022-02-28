package com.intalalab.wsnmonitoring.ui.coordinator

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.intalalab.wsnmonitoring.R
import com.intalalab.wsnmonitoring.base.BaseFragment
import com.intalalab.wsnmonitoring.cv.ClickManager
import com.intalalab.wsnmonitoring.data.local.model.CoordinatorEntity
import com.intalalab.wsnmonitoring.data.local.model.ItemDetailModel
import com.intalalab.wsnmonitoring.data.local.model.WSNEntity
import com.intalalab.wsnmonitoring.data.remote.model.coordinator.CoordinatorRequestBody
import com.intalalab.wsnmonitoring.data.remote.model.login.LoginResponseModel
import com.intalalab.wsnmonitoring.databinding.FragmentCoordinatorBinding
import com.intalalab.wsnmonitoring.util.AdapterSelectionType
import com.intalalab.wsnmonitoring.util.extension.setProgress
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

        getArgs()

        initRecyclerView()

        observeLiveData()

        manageToolbarClick()

        setCustomProgress()

    }

    private fun getArgs() {
        val wsn = args.wsnModel

        setFields(wsn)
    }

    private fun setFields(wsn: WSNEntity) {
        binding.wsnModel = wsn
    }

    private fun initRecyclerView() {
        binding.rcvCoordinator.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = coordinatorAdapter
            setHasFixedSize(true)
        }

        coordinatorAdapter.click = object : (CoordinatorEntity, AdapterSelectionType) -> Unit {
            override fun invoke(entity: CoordinatorEntity, selectionType: AdapterSelectionType) {
                when (selectionType) {
                    AdapterSelectionType.DETAIL -> {
                        navigateToDetailPage(entity)
                    }
                    AdapterSelectionType.NAVIGATE_FORWARD -> {
                        navigateToRouterFragment(entity)
                    }
                }
            }

        }
    }

    private fun navigateToDetailPage(coordinator: CoordinatorEntity) {
        val action = CoordinatorFragmentDirections.actionGlobalItemDetailDialogFragment(
            ItemDetailModel.parseCoordinatorEntity(coordinator)
        )

        navigate(action)
    }

    private fun navigateToRouterFragment(coordinator: CoordinatorEntity) {
        val action = CoordinatorFragmentDirections
            .actionCoordinatorFragmentToRouterFragment(coordinator)

        navigate(action)
    }

    private fun manageToolbarClick() {
        binding.toolbar.clickManager = object : ClickManager {
            override fun onBackClicked() {
                navigateUp()
            }

            override fun onSearchDone(text: String) {
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
                CoordinatorRequestBody(id = args.wsnModel.id, userInfo = userInfo)
            )
    }

    private fun observeCoordinators(list: List<CoordinatorEntity>) {
        coordinatorAdapter.submitList(list)
    }

    private fun setCustomProgress() {
        setProgress(requireView(), R.id.progress_bar)
    }
}