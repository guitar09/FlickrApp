package com.higor.search.app.di

import org.koin.core.Koin
import org.koin.core.component.KoinComponent

internal interface SearchKoinComponent : KoinComponent {
    override fun getKoin(): Koin = SearchKoinContext.koinApp.koin
}
