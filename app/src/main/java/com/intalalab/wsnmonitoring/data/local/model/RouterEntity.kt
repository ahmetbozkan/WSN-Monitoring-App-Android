package com.intalalab.wsnmonitoring.data.local.model

import com.intalalab.wsnmonitoring.util.extension.EMPTY

data class RouterEntity(
    val id: Long = 0,
    val name: String = String.EMPTY,
    val description: String = String.EMPTY,
    val city: Long = 0,
    val country: Long = 0,
    val county: Long = 0,
    val createdBy: String = String.EMPTY,
    val district: Long = 0,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)