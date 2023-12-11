package com.higor.search.app.domain.usecase.base

import com.higor.search.app.domain.model.SearchImage

internal interface SearchImageUseCase {
    suspend operator fun invoke(searchText: String): List<SearchImage>
}
