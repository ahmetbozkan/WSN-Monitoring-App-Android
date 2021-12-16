package com.intalalab.wsnmonitoring.util.extension

import java.text.SimpleDateFormat
import java.util.*

private const val FORMAT_DATE_HOURS = "dd.MM.yyyy HH:mm"

fun String.formatJsonDate(formatPattern: String = FORMAT_DATE_HOURS): String {
    var time = this.replace("[/Date()]".toRegex(), String.EMPTY)
    time = time.replace("+0300", String.EMPTY)

    val date = Date(time.toLong())

    return SimpleDateFormat(formatPattern, Locale.getDefault()).format(date)
}