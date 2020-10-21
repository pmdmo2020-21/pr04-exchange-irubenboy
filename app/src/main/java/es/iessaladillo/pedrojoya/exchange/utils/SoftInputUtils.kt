package es.iessaladillo.pedrojoya.exchange.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.NonNull

fun showSoftInput(@NonNull view: View): Boolean {
    if (view.requestFocus()) {
        val imm = view.context.getSystemService(
            Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm != null) {
            return imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }
    return false
}

fun View.hideSoftKeyboard(): Boolean {
    val imm = context.getSystemService(
        Context.INPUT_METHOD_SERVICE) as InputMethodManager
    return imm.hideSoftInputFromWindow(windowToken, 0) ?: false
}