package com.example.airports.utils.extensions

import android.view.View
import androidx.core.view.isVisible

fun View.hide() {
    if (isVisible) visibility = View.GONE
}

fun View.show() {
    if (!isVisible) visibility = View.VISIBLE
}