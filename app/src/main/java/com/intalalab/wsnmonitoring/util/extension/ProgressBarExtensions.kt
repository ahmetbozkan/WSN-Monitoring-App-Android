package com.intalalab.wsnmonitoring.util.extension

import android.view.View
import android.widget.ProgressBar
import com.github.ybq.android.spinkit.SpinKitView
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.ThreeBounce


fun setProgress(view: View, id: Int) {
    val progressBar = view.findViewById<SpinKitView>(id) as ProgressBar
    val doubleBounce: Sprite = ThreeBounce()
    progressBar.indeterminateDrawable = doubleBounce
}