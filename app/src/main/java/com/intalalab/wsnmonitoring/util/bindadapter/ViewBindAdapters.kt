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

@BindingAdapter("itemIndex")
fun setBackgroundColorByItemIndex(view: View, index: Int) {
    val remain = index % 2

    if(remain == 0) {
        view.setBackgroundColor(Color.parseColor("#d2e4cc"))
    }
    else {
        view.setBackgroundColor(Color.parseColor("#da7676"))
    }
}