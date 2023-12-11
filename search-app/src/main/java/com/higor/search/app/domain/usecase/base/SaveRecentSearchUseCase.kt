package com.higor.search.app.domain.usecase.base

internal interface SaveRecentSearchUseCase {
    suspend operator fun invoke(searchText: String)
}
