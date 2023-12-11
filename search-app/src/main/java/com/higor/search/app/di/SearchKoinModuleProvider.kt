package com.higor.search.app.di

import com.higor.search.app.di.module.apiModule
import com.higor.search.app.di.module.dataSourceModule
import com.higor.search.app.di.module.repositoryModule
import com.higor.search.app.di.module.useCaseModule
import com.higor.search.app.di.module.viewModelModule
import com.higor.search.app.navigation.SearchNavigationExternalRoutesProvider
import org.koin.dsl.module

internal class SearchKoinModuleProvider {
    fun provide(navigationProvider: SearchNavigationExternalRoutesProvider) =
        listOf(
            apiModule,
            useCaseModule,
            repositoryModule,
            dataSourceModule,
            viewModelModule,
            featureProvide(navigationProvider)
        )

    private fun featureProvide(navigationProvider: SearchNavigationExternalRoutesProvider) =
        module {
            single { navigationProvider }
        }
}
