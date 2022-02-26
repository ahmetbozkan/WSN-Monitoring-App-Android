package com.intalalab.wsnmonitoring.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
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

abstract class BaseDialogFragment<DB : ViewDataBinding, VM : BaseViewModel> : DialogFragment() {

    private var _binding: DB? = null
    val binding: DB get() = _binding!!

    protected abstract val viewModel: VM

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun initialize(savedInstanceState: Bundle?)

    private val navOptions = NavOptions.Builder().setLaunchSingleTop(true).build()

    protected lateinit var navController: NavController

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)

        return dialog
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NO_TITLE, android.R.style.Theme_Material_Dialog_NoActionBar_MinWidth)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        binding.lifecycleOwner = this

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        putViewModel()

        initNavigation()

        observeLiveData()

        initialize(savedInstanceState)
    }

    private fun putViewModel() {
        //binding.setVariable(BR.viewModel, viewModel)
    }

    private fun initNavigation() {
        navController = findNavController()
    }

    private fun observeLiveData() {
        viewModel.failure.observe(viewLifecycleOwner) { failure ->
            failure?.let { error ->
                handleFailure(error)
            }
        }
    }

    private fun handleFailure(error: Failure) {
        when (error) {
            is Failure.EmptyResponse -> {
                requireContext().makeToast(getString(R.string.error_wrong_fields))
            }
            else -> {
                requireContext().makeToast(getString(R.string.error_general))
            }
        }
    }


    protected fun navigateWithId(destination: Int, bundle: Bundle? = null) {
        if (findNavController().currentDestination?.id != destination) {
            popBackStack()
            findNavController().navigate(destination, bundle, navOptions)
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