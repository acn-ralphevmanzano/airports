package com.example.airports.utils.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import com.bumptech.glide.Glide

fun ImageView.setCircleImage(@DrawableRes res: Int) {
    Glide.with(this).load(res).centerInside().into(this)
}