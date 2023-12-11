package com.higor.search.app.domain.usecase.base

internal interface GetRecentSearchUseCase {
    suspend operator fun invoke() : List<String>
}
