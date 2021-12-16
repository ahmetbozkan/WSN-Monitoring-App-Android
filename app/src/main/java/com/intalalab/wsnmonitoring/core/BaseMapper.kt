package com.intalalab.wsnmonitoring.core

interface BaseMapper<in A, out B> {

    fun map(type: A?): B
}