package com.intalalab.wsnmonitoring.base

interface BaseMapper<in A, out B> {

    fun map(type: A?): B
}