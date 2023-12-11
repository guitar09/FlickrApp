package com.higor.search.app.utils

import android.text.Spanned
import androidx.core.text.HtmlCompat

internal fun String.toHtml(flag: Int = HtmlCompat.FROM_HTML_MODE_LEGACY) : Spanned = HtmlCompat.fromHtml(this, flag)