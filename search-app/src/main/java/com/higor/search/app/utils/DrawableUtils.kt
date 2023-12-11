package com.higor.search.app.utils

import android.content.Context
import androidx.swiperefreshlayout.widget.CircularProgressDrawable

internal fun createCircularProgressDrawable(context: Context): CircularProgressDrawable {
    val progress = CircularProgressDrawable(context)
    progress.strokeWidth = 8f
    progress.centerRadius = 30f
    progress.setColorSchemeColors(0xFFFFFF)
    progress.start()
    return progress
}
