package com.intalalab.wsnmonitoring.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.intalalab.wsnmonitoring.R
import com.intalalab.wsnmonitoring.core.Failure
import com.intalalab.wsnmonitoring.util.extension.makeToast

abstract class BaseFragment<DB : ViewDataBinding, VM : BaseViewModel> : Fragment() {

    private var _binding: DB? = null
    protected val binding: DB get() = _binding!!

    protected abstract val viewModel: VM

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun initialize(savedInstanceState: Bundle?)

    private val navOptions = NavOptions.Builder().setLaunchSingleTop(true).build()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        putDataBinding()

        observeLiveData()

        initialize(savedInstanceState)
    }

    private fun putDataBinding() {
        binding.setVariable(BR.viewModel, viewModel)
    }

    private fun observeLiveData() {
        viewModel.failure.observe(viewLifecycleOwner) { error ->
            when (error) {
                is Failure.EmptyResponse -> {
                    requireContext().makeToast(getString(R.string.error_wrong_fields))
                }
                else -> {
                    requireContext().makeToast(getString(R.string.error_general))
                }
            }
        }
    }

    protected fun navigateToFragment(destination: Int, bundle: Bundle? = null) {

        //avoid to reselect
        if (findNavController().currentDestination?.id != destination) {
            findNavController().navigate(destination, bundle, navOptions)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}