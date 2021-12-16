package com.intalalab.wsnmonitoring.ui.router

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.intalalab.wsnmonitoring.R
import com.intalalab.wsnmonitoring.base.BaseFragment
import com.intalalab.wsnmonitoring.data.local.model.RouterEntity
import com.intalalab.wsnmonitoring.data.remote.model.login.LoginResponseModel
import com.intalalab.wsnmonitoring.data.remote.model.router.RouterRequestBody
import com.intalalab.wsnmonitoring.databinding.FragmentRouterBinding
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

        initRecyclerView()

        observeLiveData()

    }

    private fun initRecyclerView() {
        binding.rcvRouter.apply {
            adapter = routerAdapter
            setHasFixedSize(true)
        }

        routerAdapter.click = object : (Long) -> Unit {
            override fun invoke(routerId: Long) {
                val action = RouterFragmentDirections.actionRouterFragmentToSensorFragment(routerId)
                findNavController().navigate(action)
            }

        }
    }

    private fun observeLiveData() {
        viewModel.userInfo.observe(viewLifecycleOwner, ::observeUserInfo)

        viewModel.routers.observe(viewLifecycleOwner, ::observeRouters)
    }

    private fun observeUserInfo(userInfo: LoginResponseModel?) {
        if (userInfo != null)
            viewModel.getRouters(
                RouterRequestBody(id = args.coordinatorId, userInfo = userInfo)
            )
    }

    private fun observeRouters(routers: List<RouterEntity>) {
        routerAdapter.submitList(routers)
    }
}