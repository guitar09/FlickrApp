package com.higor.search.app.data.repository

import com.higor.search.app.data.dto.SearchResponse
import com.higor.search.app.data.repository.base.BaseRepository

internal interface SearchRepository : BaseRepository {
    suspend fun searchImages(searchText: String) : SearchResponse
}
