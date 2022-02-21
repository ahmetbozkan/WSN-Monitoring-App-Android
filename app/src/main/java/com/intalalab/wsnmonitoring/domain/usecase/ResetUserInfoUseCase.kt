package com.intalalab.wsnmonitoring.domain.usecase

import com.intalalab.wsnmonitoring.data.local.sharedpreferences.SharedPreferencesHelper
import javax.inject.Inject

class ResetUserInfoUseCase @Inject constructor(
    private val sharedPreferences: SharedPreferencesHelper
) {

    suspend operator fun invoke() {
        sharedPreferences.resetUserInfo()
    }

}