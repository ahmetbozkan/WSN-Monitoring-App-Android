package com.intalalab.wsnmonitoring.util.extension

import android.view.View

fun View.goneView() {
    this.visibility = View.GONE
}

fun View.showView() {
    this.visibility = View.VISIBLE
}

fun View.hideView() {
    this.visibility = View.INVISIBLE
}