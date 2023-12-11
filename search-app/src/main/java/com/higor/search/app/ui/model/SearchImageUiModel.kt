package com.higor.search.app.ui.model

internal data class SearchImageUiModel(
    val searchText: String,
    val recentSearch: List<String>,
    val resultSearch: List<SearchItemUiModel>
)
