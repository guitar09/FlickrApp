package com.higor.search.app.domain.usecase

import com.higor.search.app.data.repository.SearchRepository
import com.higor.search.app.domain.mapper.toListResponseToDomain
import com.higor.search.app.domain.model.SearchImage
import com.higor.search.app.domain.usecase.base.AuthorParseUseCase
import com.higor.search.app.domain.usecase.base.DescriptionImageParseUseCase
import com.higor.search.app.domain.usecase.base.SearchImageUseCase
import com.higor.search.app.domain.usecase.base.SizeImageParseUseCase

internal class SearchImageUseCaseImpl(
    private val searchRepository: SearchRepository,
    private val imageParse: SizeImageParseUseCase,
    private val descriptionParse: DescriptionImageParseUseCase,
    private val authorParse: AuthorParseUseCase,
) : SearchImageUseCase {
    override suspend operator fun invoke(searchText: String): List<SearchImage> {

        val response = searchRepository.searchImages(searchText)
        return response.items.toListResponseToDomain(descriptionText = { description ->
            descriptionParse.invoke(description)
        }, imageSize = { description ->
            imageParse.invoke(description)
        }, author = { author ->
            authorParse.invoke(author)
        })
    }
}
