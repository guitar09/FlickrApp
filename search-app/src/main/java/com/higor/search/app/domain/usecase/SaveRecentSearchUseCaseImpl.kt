package com.higor.search.app.domain.usecase

import com.higor.search.app.data.repository.RecentSearchRepository
import com.higor.search.app.domain.usecase.base.SaveRecentSearchUseCase

internal class SaveRecentSearchUseCaseImpl(private val repository: RecentSearchRepository) :
    SaveRecentSearchUseCase {
    override suspend fun invoke(searchText: String) {
        if (searchText.isEmpty()) return

        val searchesTemp = repository.getSearches().toMutableList()

        if (searchesTemp.contains(searchText)) {
            updatePosition(searchText, searchesTemp)
            return
        }

        processSearches(searchText, searchesTemp)
    }

    private suspend fun saveSearches(searches: List<String>) {
        repository.insertSearches(searches)
    }

    private suspend fun updatePosition(searchText: String, searches: MutableList<String>) {
        searches.remove(searchText)
        searches.add(searchText)
        saveSearches(searches)
    }

    private suspend fun processSearches(searchText: String, searches: MutableList<String>) {
        if (searches.count() < 5) {
            searches.add(searchText)
        } else {
            searches.removeAt(0)
            searches.add(searchText)
        }

        saveSearches(searches)
    }
}
