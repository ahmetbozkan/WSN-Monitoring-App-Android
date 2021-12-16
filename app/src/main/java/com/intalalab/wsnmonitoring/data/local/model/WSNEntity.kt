package com.intalalab.wsnmonitoring.data.local.model

data class WSNEntity(
    val id: Long = 0,
    val name: String = "",
    val description: String = "",
    val createUserId: String = "",
    val createDate: String = "",
    val domain: Long = 0,
    val isActive: Boolean = true
)
