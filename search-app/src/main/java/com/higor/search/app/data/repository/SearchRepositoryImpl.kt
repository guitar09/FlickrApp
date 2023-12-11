package com.higor.search.app.data.repository

import com.higor.search.app.data.datasource.remote.SearchDataSource
import com.higor.search.app.data.dto.SearchResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class SearchRepositoryImpl(
    private val searchDataSource: SearchDataSource,
    override val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : SearchRepository {
    override suspend fun searchImages(searchText: String): SearchResponse {
        return withContext(dispatcher) {
            searchDataSource.searchImages(searchText)
        }
    }
}
