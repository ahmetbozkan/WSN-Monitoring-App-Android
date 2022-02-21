package com.intalalab.wsnmonitoring.ui.register

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.intalalab.wsnmonitoring.R
import com.intalalab.wsnmonitoring.base.BaseFragment
import com.intalalab.wsnmonitoring.databinding.FragmentRegisterStepSecondBinding
import com.intalalab.wsnmonitoring.util.extension.makeToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterStepSecondFragment :
    BaseFragment<FragmentRegisterStepSecondBinding, RegisterViewModel>() {

    override val viewModel: RegisterViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_register_step_second

    private val args: RegisterStepSecondFragmentArgs by navArgs()

    override fun initialize(savedInstanceState: Bundle?) {

        manageClick()

    }

    private fun manageClick() {
        binding.btnContinue.setOnClickListener {
            if (!checkFieldsNotEmpty()) {
                requireContext().makeToast("Fields cannot be empty")
            } else if (getPassword() != getConfirmPassword()) {
                requireContext().makeToast("Passwords must be matched.")
            } else {
                val action =
                    RegisterStepSecondFragmentDirections.actionRegisterStepSecondFragmentToRegisterStepThirdFragment(
                        args.registerRequestBody.copy(
                            password = getPassword(),
                            confirmPassword = getConfirmPassword()
                        )
                    )

                findNavController().navigate(action)
            }
        }
    }

    private fun checkFieldsNotEmpty() = !binding.edtPassword.text.isNullOrEmpty()
            && !binding.edtConfirmPassword.text.isNullOrEmpty()

    private fun getPassword() = binding.edtPassword.text.toString()

    private fun getConfirmPassword() = binding.edtConfirmPassword.text.toString()


}