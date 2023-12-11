package com.higor.search.app.data.repository

import com.higor.search.app.data.datasource.local.RecentSearchDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class RecentSearchRepositoryImpl(
    private val dataSource: RecentSearchDataSource,
    override val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : RecentSearchRepository {
    override suspend fun insertSearches(searches: List<String>) {
        withContext(dispatcher) {
            dataSource.insertSearches(searches)
        }
    }

    override suspend fun getSearches(): List<String> {
        return withContext(dispatcher) {
            dataSource.getSearches()
        }
    }
}
