package com.higor.search.app.di

import android.content.Context
import com.higor.search.app.di.base.BaseKoinContext
import com.higor.search.app.navigation.SearchNavigationExternalRoutesProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication

internal object SearchKoinContext : BaseKoinContext() {
    fun initialize(applicationContext: Context, navigationProvider: SearchNavigationExternalRoutesProvider) {
        koinApp = koinApplication {
            androidContext(applicationContext)
            modules(SearchKoinModuleProvider().provide(navigationProvider))
        }
    }
}
