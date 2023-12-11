package com.higor.search.app.domain.mapper

import com.higor.search.app.data.dto.SearchItemResponse
import com.higor.search.app.domain.model.ImageSize
import com.higor.search.app.domain.model.SearchImage

internal fun SearchItemResponse.toDomain(
    descriptionText: (description: String) -> String,
    imageSize: (description: String) -> ImageSize,
    author: (author: String) -> String
): SearchImage {
    val size = imageSize.invoke(this.description)
    return SearchImage(
        title = this.title,
        imageUrl = this.media.link,
        descriptionText = descriptionText.invoke(this.description),
        descriptionHTML = this.description,
        author = author.invoke(this.author),
        authorId = this.author_id,
        tags = this.tags,
        width = size.width,
        height = size.height
    )
}


internal fun List<SearchItemResponse>.toListResponseToDomain(
    descriptionText: (description: String) -> String,
    imageSize: (description: String) -> ImageSize,
    author: (author: String) -> String
) = map { item ->
    item.toDomain(descriptionText, imageSize, author)
}
