package com.higor.search.app.data.dto

import com.google.gson.annotations.SerializedName

data class SearchMediaResponse(
    @SerializedName("m")
    val link: String
)
