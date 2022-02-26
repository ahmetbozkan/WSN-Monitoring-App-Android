package com.intalalab.wsnmonitoring.data.local.model

import android.os.Parcelable
import com.intalalab.wsnmonitoring.util.extension.EMPTY
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemDetailModel(
    val name: String = String.EMPTY,
    val description: String = String.EMPTY,
    val city: String = String.EMPTY,
    val country: String = String.EMPTY,
    val county: String = String.EMPTY,
    val district: String = String.EMPTY,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
) : Parcelable {

    companion object {
        fun parseCoordinatorEntity(coordinator: CoordinatorEntity) =
            ItemDetailModel(
                coordinator.name,
                coordinator.description,
                coordinator.cityName,
                coordinator.countryName,
                coordinator.countyName,
                coordinator.districtName,
                coordinator.latitude,
                coordinator.longitude
            )

        fun parseRouterEntity(router: RouterEntity) =
            ItemDetailModel(
                router.name,
                router.description,
                router.cityName,
                router.countryName,
                router.countyName,
                router.districtName,
                router.latitude,
                router.longitude
            )
    }

}
