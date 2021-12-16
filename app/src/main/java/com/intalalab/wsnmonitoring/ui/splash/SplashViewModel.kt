package com.intalalab.wsnmonitoring.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.intalalab.wsnmonitoring.base.BaseViewModel
import com.intalalab.wsnmonitoring.domain.usecase.GetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase
) : BaseViewModel() {

    private val _isUserLoggedIn = MutableLiveData<Boolean>()
    val isUserLoggedIn: LiveData<Boolean> get() = _isUserLoggedIn

    init {
        getUserInfo()
    }

    private fun getUserInfo() {
        enableLoading()

        val result = getUserInfoUseCase.invoke()

        if(result != null)
            _isUserLoggedIn.postValue(true)
        else
            _isUserLoggedIn.postValue(false)

        disableLoading()
    }

}