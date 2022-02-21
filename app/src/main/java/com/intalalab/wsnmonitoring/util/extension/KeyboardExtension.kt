package com.intalalab.wsnmonitoring.util.extension

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

fun Fragment.hideKeyboard(): Boolean {
    val imm = this.requireContext()
        .getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
    return if (imm != null) {
        var focus: View? = this.requireActivity().currentFocus
        if (focus == null) focus = View(this.requireContext())
        imm.hideSoftInputFromWindow(focus.windowToken, 0)
    } else {
        false
    }
}

fun View.hideKeyboard(): Boolean {
    val imm = this.context
        .getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
    return if (imm != null) {
        var focus: View? = this
        if (focus == null) focus = View(this.context)
        imm.hideSoftInputFromWindow(focus.windowToken, 0)
    } else {
        false
    }
}