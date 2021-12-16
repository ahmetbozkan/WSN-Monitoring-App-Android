package com.intalalab.wsnmonitoring.domain.usecase

import com.intalalab.wsnmonitoring.data.local.sharedpreferences.SharedPreferencesHelper
import com.intalalab.wsnmonitoring.data.remote.model.login.LoginResponseModel
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val sharedPreferencesHelper: SharedPreferencesHelper
) {

    fun invoke(): LoginResponseModel? =
        sharedPreferencesHelper.getUserInfo()

}