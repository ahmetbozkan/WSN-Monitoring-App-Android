package com.intalalab.wsnmonitoring.util.extension

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.widget.Toast
import com.intalalab.wsnmonitoring.util.Constants
import java.util.*

fun Context.isNetworkAvailable(): Boolean {
    val cm =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)

        capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    } else {
        cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }
}

fun Context.makeToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.openMap(latitude: Double, longitude: Double) {
    val uri = String.format(
        Locale.ENGLISH,
        Constants.GOOGLE_MAP_ACTION_FORMAT,
        latitude,
        longitude
    )

    this.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(uri)))
}