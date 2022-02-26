package com.intalalab.wsnmonitoring.util.bindadapter

import android.graphics.Color
import android.view.View
import androidx.databinding.BindingAdapter
import com.intalalab.wsnmonitoring.cv.CustomConstraintLayout
import com.intalalab.wsnmonitoring.util.extension.hideView
import com.intalalab.wsnmonitoring.util.extension.showView

@BindingAdapter("isVisible")
fun setProgressVisibility(view: CustomConstraintLayout, isVisible: Boolean) {
    if(isVisible)
        view.progressBar.showView()
    else
        view.progressBar.hideView()
}