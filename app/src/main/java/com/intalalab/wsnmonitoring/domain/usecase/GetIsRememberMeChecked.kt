package com.intalalab.wsnmonitoring.domain.usecase

import com.intalalab.wsnmonitoring.data.local.sharedpreferences.SharedPreferencesHelper
import javax.inject.Inject

class GetIsRememberMeChecked @Inject constructor(
    private val sharedPreferencesHelper: SharedPreferencesHelper
) {

    fun invoke(): Boolean = sharedPreferencesHelper.getIsRememberMeChecked()

}