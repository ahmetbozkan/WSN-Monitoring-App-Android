package com.intalalab.wsnmonitoring.ui.router

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.intalalab.wsnmonitoring.R
import com.intalalab.wsnmonitoring.base.BaseFragment
import com.intalalab.wsnmonitoring.cv.ClickManage
import com.intalalab.wsnmonitoring.data.local.model.RouterEntity
import com.intalalab.wsnmonitoring.data.remote.model.login.LoginResponseModel
import com.intalalab.wsnmonitoring.data.remote.model.router.RouterRequestBody
import com.intalalab.wsnmonitoring.databinding.FragmentRouterBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
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

        manageToolbarClick()

    }

    private fun initRecyclerView() {
        binding.rcvRouter.apply {
            adapter = routerAdapter
            setHasFixedSize(true)
        }

        routerAdapter.click = object : (Long, RouterEntity, String) -> Unit {
            override fun invoke(routerId: Long, router: RouterEntity, item: String) {
                if (item == "item") {
                    val action = RouterFragmentDirections
                        .actionRouterFragmentToSensorFragment(routerId, router)
                    findNavController().navigate(action)
                } else {
                    val uri = String.format(
                        Locale.ENGLISH,
                        "geo:%f,%f",
                        router.latitude,
                        router.longitude
                    )
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(uri)))
                }

            }

        }
    }

    private fun manageToolbarClick() {
        binding.toolbar.clickManage = object : ClickManage {
            override fun backButtonClicked() {
                findNavController().navigateUp()
            }

            override fun searchDoneClicked(text: String) {

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