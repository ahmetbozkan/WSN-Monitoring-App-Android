package com.intalalab.wsnmonitoring.ui.login

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.intalalab.wsnmonitoring.R
import com.intalalab.wsnmonitoring.base.BaseFragment
import com.intalalab.wsnmonitoring.data.remote.model.login.LoginRequestBody
import com.intalalab.wsnmonitoring.databinding.FragmentLoginBinding
import com.intalalab.wsnmonitoring.util.extension.setProgress
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    override val viewModel: LoginViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_login

    override fun initialize(savedInstanceState: Bundle?) {

        manageClick()

        observeLiveData()

        setCustomProgress()

    }

    private fun manageClick() {
        binding.btnLogin.setOnClickListener {
            viewModel.login(
                LoginRequestBody(getUsernameText(), getPasswordText()),
                binding.checkboxRememberMe.isChecked
            )
        }
    }

    private fun observeLiveData() {
        viewModel.login.observe(viewLifecycleOwner, ::observeLoginResponse)
    }

    private fun observeLoginResponse(login: Boolean) {
        if (login) {
            val action = LoginFragmentDirections.actionLoginFragmentToLandingFragment()
            findNavController().navigate(action)
        }
    }

    private fun getUsernameText() =
        binding.edtUsername.text.toString()

    private fun getPasswordText() =
        binding.edtPassword.text.toString()

    private fun setCustomProgress() {
        setProgress(requireView(), R.id.progress_bar)
    }
}