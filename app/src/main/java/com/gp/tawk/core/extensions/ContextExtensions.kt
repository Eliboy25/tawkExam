package com.gp.tawk.core.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.util.TypedValue
import android.view.inputmethod.InputMethodManager


fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager =
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}