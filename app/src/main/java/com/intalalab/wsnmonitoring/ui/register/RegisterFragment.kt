package com.intalalab.wsnmonitoring.ui.register

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.intalalab.wsnmonitoring.R
import com.intalalab.wsnmonitoring.base.BaseFragment
import com.intalalab.wsnmonitoring.databinding.FragmentRegisterBinding

class RegisterFragment: BaseFragment<FragmentRegisterBinding, RegisterViewModel>() {

    override val viewModel: RegisterViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_register

    override fun initialize(savedInstanceState: Bundle?) {

    }
}