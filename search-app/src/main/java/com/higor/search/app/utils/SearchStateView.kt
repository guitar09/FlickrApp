package com.higor.search.app.utils

internal sealed class SearchStateView<T>

internal data class SuccessState<T>(val data: T) : SearchStateView<T>()
internal data class ErrorState<T>(val error: Throwable) : SearchStateView<T>()
internal data class LoadingState<T>(val searchText: String) : SearchStateView<T>()
internal data class EmptyState<T>(val recentSearch: List<String>) : SearchStateView<T>()
internal class EmptyRecentState<T> : SearchStateView<T>()