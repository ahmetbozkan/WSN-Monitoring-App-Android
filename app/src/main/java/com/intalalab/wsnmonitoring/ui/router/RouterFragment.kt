package com.intalalab.wsnmonitoring.ui.router

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.intalalab.wsnmonitoring.R
import com.intalalab.wsnmonitoring.base.BaseFragment
import com.intalalab.wsnmonitoring.cv.ClickManager
import com.intalalab.wsnmonitoring.data.local.model.ItemDetailModel
import com.intalalab.wsnmonitoring.data.local.model.RouterEntity
import com.intalalab.wsnmonitoring.data.remote.model.login.LoginResponseModel
import com.intalalab.wsnmonitoring.data.remote.model.router.RouterRequestBody
import com.intalalab.wsnmonitoring.databinding.FragmentRouterBinding
import com.intalalab.wsnmonitoring.util.AdapterSelectionType
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RouterFragment : BaseFragment<FragmentRouterBinding, RouterViewModel>() {

    override val viewModel: RouterViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_router

    private val args: RouterFragmentArgs by navArgs()

    @Inject
    lateinit var routerAdapter: RouterAdapter

    override fun initialize(savedInstanceState: Bundle?) {

        getArgs()

        initRecyclerView()

        observeLiveData()

        manageToolbarClick()

    }

    private fun getArgs() {
        val item = args.coordinator

        binding.coordinatorModel = item
    }

    private fun initRecyclerView() {
        binding.rcvRouter.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = routerAdapter
            setHasFixedSize(true)
        }

        routerAdapter.click = object : (RouterEntity, AdapterSelectionType) -> Unit {
            override fun invoke(router: RouterEntity, selectionType: AdapterSelectionType) {
                when (selectionType) {
                    AdapterSelectionType.DETAIL -> {
                        navigateToDetailFragment(router)
                    }
                    AdapterSelectionType.NAVIGATE_FORWARD -> {
                        navigateToSensorFragment(router)
                    }
                }
            }


        }
    }

    private fun navigateToDetailFragment(router: RouterEntity) {
        val action = RouterFragmentDirections.actionGlobalItemDetailDialogFragment(
            ItemDetailModel.parseRouterEntity(router)
        )

        navigate(action)
    }

    private fun navigateToSensorFragment(router: RouterEntity) {
        val action = RouterFragmentDirections.actionRouterFragmentToSensorFragment(router)
        navigate(action)
    }

    private fun observeLiveData() {
        viewModel.userInfo.observe(viewLifecycleOwner, ::observeUserInfo)
        viewModel.routers.observe(viewLifecycleOwner, ::observeRouters)
    }

    private fun observeUserInfo(userInfo: LoginResponseModel?) {
        if (userInfo != null)
            viewModel.getRouters(
                RouterRequestBody(id = args.coordinator.id, userInfo = userInfo)
            )
    }

    private fun observeRouters(routers: List<RouterEntity>) {
        routerAdapter.submitList(routers)
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

}