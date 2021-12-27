package com.intalalab.wsnmonitoring.di

import com.intalalab.wsnmonitoring.data.remote.repository.coordinator.CoordinatorRepository
import com.intalalab.wsnmonitoring.data.remote.repository.coordinator.CoordinatorRepositoryImpl
import com.intalalab.wsnmonitoring.data.remote.repository.login.LoginRepository
import com.intalalab.wsnmonitoring.data.remote.repository.login.LoginRepositoryImpl
import com.intalalab.wsnmonitoring.data.remote.repository.router.RouterRepository
import com.intalalab.wsnmonitoring.data.remote.repository.router.RouterRepositoryImpl
import com.intalalab.wsnmonitoring.data.remote.repository.sensor.SensorRepository
import com.intalalab.wsnmonitoring.data.remote.repository.sensor.SensorRepositoryImpl
import com.intalalab.wsnmonitoring.data.remote.repository.sensor.data.SensorDataRepository
import com.intalalab.wsnmonitoring.data.remote.repository.sensor.data.SensorDataRepositoryImpl
import com.intalalab.wsnmonitoring.data.remote.repository.wsn.WSNRepository
import com.intalalab.wsnmonitoring.data.remote.repository.wsn.WSNRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository

    @Binds
    @ViewModelScoped
    abstract fun bindWSNRepository(wsnRepositoryImpl: WSNRepositoryImpl): WSNRepository

    @Binds
    @ViewModelScoped
    abstract fun bindCoordinatorRepository(coordinatorRepositoryImpl: CoordinatorRepositoryImpl): CoordinatorRepository

    @Binds
    @ViewModelScoped
    abstract fun bindRouterRepository(routerRepositoryImpl: RouterRepositoryImpl): RouterRepository

    @Binds
    @ViewModelScoped
    abstract fun bindSensorRepository(sensorRepositoryImpl: SensorRepositoryImpl): SensorRepository

    @Binds
    @ViewModelScoped
    abstract fun bindSensorMeasurementRepository(sensorMeasurementRepositoryImpl: SensorDataRepositoryImpl): SensorDataRepository

}