package com.higor.search.app.ui.mapper

import com.higor.search.app.domain.model.SearchImage
import com.higor.search.app.ui.model.SearchItemUiModel

internal fun SearchImage.toSearchItemUiModel() = SearchItemUiModel(
    title = this.title,
    imageUrl = this.imageUrl,
    description = this.descriptionText,
    author = this.author,
    width = this.width,
    height = this.height
)

internal fun List<SearchImage>.toListSearchUiModel() = map { item ->
    item.toSearchItemUiModel()
}
