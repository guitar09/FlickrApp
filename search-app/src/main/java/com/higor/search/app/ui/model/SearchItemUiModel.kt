package com.higor.search.app.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchItemUiModel(
    val title: String,
    val imageUrl: String,
    val description: String,
    val author: String,
    val width: Int,
    val height: Int
) : Parcelable
