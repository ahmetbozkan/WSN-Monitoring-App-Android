package com.intalalab.wsnmonitoring.domain.usecase

import com.intalalab.wsnmonitoring.data.local.sharedpreferences.SharedPreferencesHelper
import com.intalalab.wsnmonitoring.data.remote.model.login.LoginResponseModel
import javax.inject.Inject

class StoreUserInfoUseCase @Inject constructor(
    private val sharedPreferencesHelper: SharedPreferencesHelper
) {

    suspend operator fun invoke(userModel: LoginResponseModel) =
        sharedPreferencesHelper.storeUserInfo(userInfo = userModel)

}