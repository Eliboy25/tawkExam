package com.gp.tawk.core.extensions

import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.StringRes
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.snackbar.Snackbar
import com.gp.tawk.R



inline fun View.showMessage(
    @StringRes messageRes: Int, length: Int = Snackbar.LENGTH_SHORT,
    f: Snackbar.() -> Unit
) {
    showMessage(resources.getString(messageRes), length, f)
}

inline fun View.showMessage(
    message: String,
    length: Int = Snackbar.LENGTH_SHORT,
    f: Snackbar.() -> Unit
) {
    val snack = Snackbar.make(this, message, length)
    val snackTextView =
        snack.view.findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView
    snackTextView.maxLines = 5
    snack.f()
    snack.show()
}

fun View.showError(@StringRes messageRes: Int, length: Int = Snackbar.LENGTH_SHORT) {
    showError(messageRes, length) { /*EMPTY*/ }
}

fun View.showError(message: String, length: Int = Snackbar.LENGTH_SHORT) {
    showError(message, length) { /*EMPTY*/ }
}

inline fun View.showError(
    @StringRes messageRes: Int, length: Int = Snackbar.LENGTH_SHORT,
    f: Snackbar.() -> Unit
) {
    showError(resources.getString(messageRes), length, f)
}

inline fun View.showError(
    message: String,
    length: Int = Snackbar.LENGTH_SHORT,
    f: Snackbar.() -> Unit
) {
    val snack = Snackbar.make(this, message, length)
    snack.setBackgroundTint(ResourcesCompat.getColor(resources, R.color.warning, null))
    val snackTextView =
        snack.view.findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView
    snackTextView.maxLines = 5
    snack.f()
    snack.show()
}

fun Snackbar.action(@StringRes actionRes: Int, @ColorInt color: Int? = null, listener: (View) -> Unit) {
    action(view.resources.getString(actionRes), color, listener)
}

fun Snackbar.action(action: String, color: Int? = null, listener: (View) -> Unit) {
    setAction(action, listener)
    color?.let { setActionTextColor(color) }
}