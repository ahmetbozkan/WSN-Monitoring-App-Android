package com.intalalab.wsnmonitoring.ui.multiselection

import android.os.Parcelable
import com.intalalab.wsnmonitoring.util.extension.EMPTY
import kotlinx.parcelize.Parcelize

@Parcelize
data class MultiSelectionDialogModel(
    val title: String = String.EMPTY,
    val description: String = String.EMPTY,
    val rightButtonText: String = String.EMPTY,
    val leftButtonText: String = String.EMPTY,
    val isLeftButtonVisible: Boolean = false
) : Parcelable {

    companion object {
        const val SINGLE_BUTTON_DIALOG_RETURN_KEY = "single_button"
        const val SINGLE_BUTTON_DIALOG_BUTTON_ACTION_KEY = "dialog_button_action"
    }

}

enum class MultiSelectionType(val value: Int) {
    NOTHING(-1),
    SELECTION_LEFT(0),
    SELECTION_RIGHT(1)
}