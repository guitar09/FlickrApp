package com.higor.search.app.data.datasource.remote

import com.higor.search.app.data.dto.SearchResponse

internal interface SearchDataSource {
    suspend fun searchImages(searchText: String): SearchResponse
}
