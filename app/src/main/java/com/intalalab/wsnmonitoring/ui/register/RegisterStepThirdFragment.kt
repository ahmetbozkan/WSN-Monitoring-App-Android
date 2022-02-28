package com.intalalab.wsnmonitoring.ui.register

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.intalalab.wsnmonitoring.R
import com.intalalab.wsnmonitoring.base.BaseFragment
import com.intalalab.wsnmonitoring.databinding.FragmentRegisterStepThirdBinding
import com.intalalab.wsnmonitoring.util.extension.makeToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterStepThirdFragment :
    BaseFragment<FragmentRegisterStepThirdBinding, RegisterViewModel>() {

    override val viewModel: RegisterViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_register_step_third

    private val args: RegisterStepThirdFragmentArgs by navArgs()

    override fun initialize(savedInstanceState: Bundle?) {

        observeLiveData()

        manageClick()

    }

    private fun observeLiveData() {
        viewModel.register.observe(viewLifecycleOwner, ::observeRegisterResponse)
    }

    private fun observeRegisterResponse(success: Boolean) {
        if (success) {
            val action = RegisterStepThirdFragmentDirections
                .actionRegisterStepThirdFragmentToLoginFragment()
            findNavController().navigate(action)
        }
    }

    private fun manageClick() {
        binding.btnCreateAccount.setOnClickListener {
            if (checkFieldsNotEmpty()) {
                if (binding.checkBoxTermsConditions.isChecked)
                    register()
                else
                    requireActivity().makeToast("You must accept the terms and conditions.")
            } else
                requireActivity().makeToast("You must fill all the fields.")
        }
    }

    private fun register() {
        viewModel.register(
            args.registerRequestBody.copy(
                securityQuestion = getSecurityQuestion(),
                securityQuestionAnswer = getSecurityQuestionAnswer()
            )
        )
    }

    private fun checkFieldsNotEmpty() = !binding.edtSecurityQuestion.text.isNullOrEmpty()
            && !binding.edtSecurityQuestionAnswer.text.isNullOrEmpty()

    private fun getSecurityQuestion() = binding.edtSecurityQuestion.text.toString()

    private fun getSecurityQuestionAnswer() = binding.edtSecurityQuestionAnswer.text.toString()


}