package com.intalalab.wsnmonitoring.data.local.sharedpreferences

import com.intalalab.wsnmonitoring.data.remote.model.login.LoginResponseModel

interface SharedPreferencesHelper {

    /**
     * get the user information stored after login as LoginResponse
     */
    fun getUserInfo(): LoginResponseModel?

    /**
     * store info to local db as string
     * @param userInfo model of the user contains userId, username and password
     */
    suspend fun storeUserInfo(userInfo: LoginResponseModel)

    /**
     * remove all the info in user key (logout)
     */
    suspend fun resetUserInfo()

}