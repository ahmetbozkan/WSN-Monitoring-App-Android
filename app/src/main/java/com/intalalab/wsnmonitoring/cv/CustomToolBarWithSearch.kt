package com.intalalab.wsnmonitoring.cv

import android.content.Context
import android.util.AttributeSet
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.intalalab.wsnmonitoring.R
import com.intalalab.wsnmonitoring.util.extension.goneView
import com.intalalab.wsnmonitoring.util.extension.hideKeyboard
import com.intalalab.wsnmonitoring.util.extension.showView

class CustomToolBarWithSearch(context: Context, attrs: AttributeSet?) :
    LinearLayoutCompat(context, attrs) {


    private val backButton: ImageView
    private val edtSearch: AppCompatEditText
    private val icSearch: AppCompatImageView
    private val tvTitle: AppCompatTextView
    private var title: String = ""
    private var isSearchGone: Boolean = false

    var clickManager: ClickManager? = null

    init {
        inflate(context, R.layout.layout_toolbar_with_search, this)

        backButton = findViewById(R.id.img_back)
        edtSearch = findViewById(R.id.edt_search)
        icSearch = findViewById(R.id.ic_search)
        tvTitle = findViewById(R.id.tv_title)

        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.CustomToolBarWithSearch)

        title = typedArray.getString(R.styleable.CustomToolBarWithSearch_toolbarTitle)
            ?: throw NullPointerException("must enter title")

        isSearchGone =
            typedArray.getBoolean(R.styleable.CustomToolBarWithSearch_isSearchGone, false)

        typedArray.recycle()

        setTitle(title)

        manageSearchIconVisibility()

        manageClick()

        manageDoneClickedOnKeyboard()
    }

    private fun manageSearchIconVisibility() {
        if (isSearchGone) {
            icSearch.goneView()
        }
    }

    fun setTitle(title: String) {
        tvTitle.text = title
    }

    private fun manageDoneClickedOnKeyboard() {
        edtSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {

                edtSearch.hideKeyboard()
                clickManager?.onSearchDone(getSearchText())

                true
            } else false
        }
    }

    private fun manageClick() {
        backButton.setOnClickListener {

            if (isSearchBoxShown())
                hideSearchEditText()
            else
                clickManager?.onBackClicked()
        }

        icSearch.setOnClickListener {
            showSearchEditText()
        }
    }

    private fun isSearchBoxShown(): Boolean =
        edtSearch.isShown

    private fun showSearchEditText() {
        edtSearch.showView()
    }

    private fun hideSearchEditText() {
        edtSearch.goneView()
    }

    fun getSearchText() =
        edtSearch.text.toString()

}

/**
 * manage click on view
 */
interface ClickManager {
    /**
     * back button clicked
     */
    fun onBackClicked()

    /**
     * done button clicked on keyboard for search edittext
     */
    fun onSearchDone(text: String)
}