package com.higor.search.app.data.repository

import com.higor.search.app.data.repository.base.BaseRepository

internal interface RecentSearchRepository : BaseRepository {
    suspend fun insertSearches(searches: List<String>)
    suspend fun getSearches() : List<String>
}