package com.higor.search.app.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.higor.search.app.domain.model.SearchImage
import com.higor.search.app.domain.usecase.base.GetRecentSearchUseCase
import com.higor.search.app.domain.usecase.base.SaveRecentSearchUseCase
import com.higor.search.app.domain.usecase.base.SearchImageUseCase
import com.higor.search.app.ui.mapper.toListSearchUiModel
import com.higor.search.app.ui.model.SearchImageUiModel
import com.higor.search.app.utils.EmptyRecentState
import com.higor.search.app.utils.EmptyState
import com.higor.search.app.utils.ErrorState
import com.higor.search.app.utils.LoadingState
import com.higor.search.app.utils.SearchStateView
import com.higor.search.app.utils.SuccessState
import kotlinx.coroutines.launch

internal class SearchViewModel(
    private val savedStateHandled: SavedStateHandle,
    private val useCaseSearchImage: SearchImageUseCase,
    private val useCaseSaveRecent: SaveRecentSearchUseCase,
    private val useCaseGetRecent: GetRecentSearchUseCase,
) : ViewModel() {

    init {
        initialConfig()
    }

    private val _searchImage = MutableLiveData<SearchStateView<SearchImageUiModel>>()
    val searchImage: LiveData<SearchStateView<SearchImageUiModel>> = _searchImage

    fun search(searchText: String) {
        viewModelScope.launch {
            runCatching {
                processSearch(searchText)
            }.onSuccess { result ->
                processSuccess(searchText, result)
            }.onFailure {
                _searchImage.value = ErrorState(it)
            }
        }
    }

    private suspend fun processSearch(searchText: String): List<SearchImage> {
        saveSearch(searchText)
        useCaseSaveRecent(searchText)
        _searchImage.value = LoadingState(searchText)
        return useCaseSearchImage(searchText)
    }

    private suspend fun processSuccess(searchText: String, images: List<SearchImage>) {
        val recentSearch = useCaseGetRecent()
        if (images.isEmpty()) {
            _searchImage.value = EmptyState(recentSearch)
        } else {
            val search =
                SearchImageUiModel(searchText, recentSearch, images.toListSearchUiModel())
            _searchImage.value = SuccessState(search)
        }
    }

    private fun initialConfig() {
        viewModelScope.launch {
            getSearch()?.let { searchedText ->
                search(searchedText)
            } ?: run {
                getRecentSearches()
            }
        }
    }

    private suspend fun getRecentSearches() {
        runCatching {
            useCaseGetRecent()
        }.onSuccess { recentSearches ->
            if (recentSearches.isNotEmpty()) {
                _searchImage.value = EmptyState(recentSearches)
            } else {
                _searchImage.value = EmptyRecentState()
            }
        }.onFailure {
            _searchImage.value = EmptyRecentState()
        }
    }

    private fun saveSearch(searchedText: String) {
        savedStateHandled[SEARCHED_TEXT] = searchedText
    }

    private fun getSearch(): String? {
        return savedStateHandled.get<String>(SEARCHED_TEXT)
    }

    companion object {
        private const val SEARCHED_TEXT = "SEARCHED_TEXT"
    }
}
