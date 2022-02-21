package com.intalalab.wsnmonitoring.ui.welcome

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.intalalab.wsnmonitoring.R
import com.intalalab.wsnmonitoring.base.BaseFragment
import com.intalalab.wsnmonitoring.databinding.FragmentWelcomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeFragment : BaseFragment<FragmentWelcomeBinding, WelcomeViewModel>() {

    override val viewModel: WelcomeViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_welcome

    override fun initialize(savedInstanceState: Bundle?) {

        manageClick()

    }

    private fun manageClick() {
        binding.btnSignIn.setOnClickListener {
            val action = WelcomeFragmentDirections.actionWelcomeFragmentToLoginFragment()
            findNavController().navigate(action)
        }

        binding.btnSignUp.setOnClickListener {
            val action = WelcomeFragmentDirections.actionWelcomeFragmentToRegisterStepFirstFragment()
            findNavController().navigate(action)
        }
    }
}