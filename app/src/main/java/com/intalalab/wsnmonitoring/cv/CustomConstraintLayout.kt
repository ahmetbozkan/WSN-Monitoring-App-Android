package com.intalalab.wsnmonitoring.cv

import android.content.Context
import android.util.AttributeSet
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import com.intalalab.wsnmonitoring.R

class CustomConstraintLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var constraintLayout: ConstraintLayout

    var progressBar: ProgressBar

    init {
        inflate(context, R.layout.custom_constraint_layout_loading, this)

        constraintLayout = findViewById(R.id.custom_constraint_layout_loading)
        progressBar = findViewById(R.id.custom_progress_bar)
    }
}