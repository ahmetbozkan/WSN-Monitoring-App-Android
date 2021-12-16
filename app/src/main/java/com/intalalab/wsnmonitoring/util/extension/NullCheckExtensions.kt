package com.intalalab.wsnmonitoring.util.extension

fun String?.emptyIfNull() =
    this ?: String.EMPTY

fun Long?.zeroIfNull() =
    this ?: 0

fun Boolean?.falseIfNull() =
    this ?: false

fun Int?.zeroIfNull() =
    this ?: 0

fun Double?.zeroIfNull() =
    this ?: 0.0