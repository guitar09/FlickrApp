package com.higor.search.app.domain.usecase

import com.higor.search.app.data.repository.RecentSearchRepository
import com.higor.search.app.domain.usecase.base.GetRecentSearchUseCase

internal class GetRecentSearchUseCaseImpl(private val repository: RecentSearchRepository) :
    GetRecentSearchUseCase {
    override suspend fun invoke(): List<String> = repository.getSearches().asReversed()
}
