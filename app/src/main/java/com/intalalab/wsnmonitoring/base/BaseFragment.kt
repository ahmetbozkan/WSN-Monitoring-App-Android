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
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.DialogFragmentNavigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import com.intalalab.wsnmonitoring.R
import com.intalalab.wsnmonitoring.core.Failure
import com.intalalab.wsnmonitoring.util.extension.hideKeyboard
import com.intalalab.wsnmonitoring.util.extension.makeToast

abstract class BaseFragment<DB : ViewDataBinding, VM : BaseViewModel> : Fragment() {

    private var _binding: DB? = null
    protected val binding: DB get() = _binding!!

    protected abstract val viewModel: VM

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun initialize(savedInstanceState: Bundle?)

    private val navOptions = NavOptions.Builder().setLaunchSingleTop(true).build()

    protected lateinit var navController: NavController

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

        initNavigation()

        observeLiveData()

        initialize(savedInstanceState)
    }

    private fun putDataBinding() {
        binding.setVariable(BR.viewModel, viewModel)
    }

    private fun initNavigation() {
        navController = findNavController()
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

    // ensure the current destination is the parent destination before navigation
    // otherwise it will crash when executing navigate() multiple times at once
    protected fun navigate(directions: NavDirections) {
        val currentDestination =
            (navController.currentDestination as? FragmentNavigator.Destination)?.className
                ?: (navController.currentDestination as? DialogFragmentNavigator.Destination)?.className

        if (currentDestination == this.javaClass.name)
            navController.navigate(directions)
    }

    protected fun navigateToFragment(destination: Int, bundle: Bundle? = null) {
        //avoid to reselect
        if (navController.currentDestination?.id != destination) {
            navController.navigate(destination, bundle, navOptions)
        }
    }

    protected fun popBackStack() {
        findNavController().popBackStack()
    }

    protected fun navigateUp() {
        findNavController().navigateUp()
    }

    protected fun navigateBack() {
        findNavController().navigateUp()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        hideKeyboard()
    }

}