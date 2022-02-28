package com.intalalab.wsnmonitoring.domain.usecase

import com.intalalab.wsnmonitoring.data.local.sharedpreferences.SharedPreferencesHelper
import javax.inject.Inject

class StoreIsRememberMeChecked @Inject constructor(
    private val sharedPreferencesHelper: SharedPreferencesHelper
) {

    suspend operator fun invoke(checked: Boolean) {
        sharedPreferencesHelper.storeIsRememberMeChecked(checked)
    }

}