package com.higor.search.app.data.repository.base

import kotlinx.coroutines.CoroutineDispatcher

internal interface BaseRepository {
    val dispatcher: CoroutineDispatcher
}
