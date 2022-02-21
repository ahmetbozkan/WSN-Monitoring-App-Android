package com.intalalab.wsnmonitoring.ui.splash

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.intalalab.wsnmonitoring.R
import com.intalalab.wsnmonitoring.base.BaseFragment
import com.intalalab.wsnmonitoring.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {

    override val viewModel: SplashViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_splash

    override fun initialize(savedInstanceState: Bundle?) {

        lifecycleScope.launch {
            startDelay()
        }

    }

    private suspend fun startDelay() {
        delay(2000L)

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.isUserLoggedIn.observe(viewLifecycleOwner) { loggedIn ->
            val action = if (loggedIn)
                SplashFragmentDirections.actionSplashFragmentToLandingFragment()
            else
                SplashFragmentDirections.actionSplashFragmentToWelcomeFragment()

            val navOptions = NavOptions.Builder().setPopUpTo(R.id.splashFragment, true).build()

            findNavController().navigate(action, navOptions)
        }
    }
}