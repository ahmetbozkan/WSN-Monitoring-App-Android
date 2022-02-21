package com.intalalab.wsnmonitoring.data.local.model

import android.os.Parcelable
import com.intalalab.wsnmonitoring.util.extension.EMPTY
import kotlinx.parcelize.Parcelize

@Parcelize
data class RouterEntity(
    val id: Long = 0,
    val name: String = String.EMPTY,
    val description: String = String.EMPTY,
    val city: Long = 0,
    val cityName: String = String.EMPTY,
    val country: Long = 0,
    val countryName: String = String.EMPTY,
    val county: Long = 0,
    val countyName: String = String.EMPTY,
    val createdBy: String = String.EMPTY,
    val district: Long = 0,
    val districtName: String = String.EMPTY,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
) : Parcelable