package com.higor.search.app.di.module

import com.higor.search.app.data.repository.RecentSearchRepository
import com.higor.search.app.data.repository.RecentSearchRepositoryImpl
import com.higor.search.app.data.repository.SearchRepository
import com.higor.search.app.data.repository.SearchRepositoryImpl
import org.koin.dsl.module

internal val repositoryModule = module {
    factory<SearchRepository> { SearchRepositoryImpl(searchDataSource = get()) }
    factory<RecentSearchRepository> { RecentSearchRepositoryImpl(dataSource = get()) }
}
