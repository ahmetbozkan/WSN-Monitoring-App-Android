package com.intalalab.wsnmonitoring.ui.register

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.intalalab.wsnmonitoring.R
import com.intalalab.wsnmonitoring.base.BaseFragment
import com.intalalab.wsnmonitoring.data.remote.model.login.RegisterRequestBody
import com.intalalab.wsnmonitoring.databinding.FragmentRegisterStepFirstBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterStepFirstFragment :
    BaseFragment<FragmentRegisterStepFirstBinding, RegisterViewModel>() {

    override val viewModel: RegisterViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_register_step_first

    override fun initialize(savedInstanceState: Bundle?) {

        manageClick()

    }

    private fun manageClick() {
        binding.btnContinue.setOnClickListener {
            if (checkFieldsNotEmpty()) {
                val action =
                    RegisterStepFirstFragmentDirections.actionRegisterStepFirstFragmentToRegisterStepSecondFragment(
                        RegisterRequestBody(
                            username = getUsername(),
                            email = getEmail()
                        )
                    )

                findNavController().navigate(action)
            }
        }
    }

    private fun checkFieldsNotEmpty() = !binding.edtEmail.text.isNullOrEmpty()
            && !binding.edtUsername.text.isNullOrEmpty()

    private fun getUsername() = binding.edtUsername.text.toString()

    private fun getEmail() = binding.edtEmail.text.toString()

}