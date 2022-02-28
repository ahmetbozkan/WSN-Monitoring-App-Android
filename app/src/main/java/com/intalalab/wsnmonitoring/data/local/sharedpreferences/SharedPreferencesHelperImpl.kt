package com.intalalab.wsnmonitoring.data.local.sharedpreferences

import android.content.SharedPreferences
import com.google.gson.Gson

import com.intalalab.wsnmonitoring.data.remote.model.login.LoginResponseModel
import com.intalalab.wsnmonitoring.util.extension.EMPTY
import javax.inject.Inject

class SharedPreferencesHelperImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : SharedPreferencesHelper {

    companion object {
        private const val KEY_USER_ID = "user_id"
        private const val KEY_IS_REMEMBER_ME_CHECKED = "is_remember_me_checked"
    }

    override fun getUserInfo(): LoginResponseModel? {
        val userInfo = sharedPreferences.getString(KEY_USER_ID, String.EMPTY) ?: String.EMPTY

        return if (userInfo != String.EMPTY)
            Gson().fromJson(userInfo, LoginResponseModel::class.java)
        else
            null
    }

    override suspend fun storeUserInfo(userInfo: LoginResponseModel) {
        val userString = Gson().toJson(userInfo)

        sharedPreferences.edit().apply {
            putString(KEY_USER_ID, userString)
        }.apply()
    }

    override suspend fun resetUserInfo() {
        sharedPreferences.edit().apply {
            putString(KEY_USER_ID, String.EMPTY)
        }.apply()
    }

    override fun getIsRememberMeChecked(): Boolean =
        sharedPreferences.getBoolean(KEY_IS_REMEMBER_ME_CHECKED, false)


    override suspend fun storeIsRememberMeChecked(checked: Boolean) {
        sharedPreferences.edit().apply {
            putBoolean(KEY_IS_REMEMBER_ME_CHECKED, checked)
        }.apply()
    }


}