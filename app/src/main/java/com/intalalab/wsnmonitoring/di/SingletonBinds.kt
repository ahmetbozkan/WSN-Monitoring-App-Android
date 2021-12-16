package com.intalalab.wsnmonitoring.di

import com.intalalab.wsnmonitoring.data.local.sharedpreferences.SharedPreferencesHelper
import com.intalalab.wsnmonitoring.data.local.sharedpreferences.SharedPreferencesHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SingletonBinds {

    @Binds
    @Singleton
    abstract fun bindSharedPreferencesHelper(
        sharedPreferencesHelperImpl: SharedPreferencesHelperImpl
    ): SharedPreferencesHelper

}