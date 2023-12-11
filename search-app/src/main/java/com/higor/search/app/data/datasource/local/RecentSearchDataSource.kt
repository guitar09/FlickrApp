package com.higor.search.app.data.datasource.local

internal interface RecentSearchDataSource {
    suspend fun insertSearches(searches: List<String>)
    suspend fun getSearches(): List<String>
}
