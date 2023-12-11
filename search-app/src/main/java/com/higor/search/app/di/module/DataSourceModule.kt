package com.higor.search.app.di.module

import com.higor.search.app.data.datasource.local.RecentSearchDataSource
import com.higor.search.app.data.datasource.local.RecentSearchDataSourceImpl
import com.higor.search.app.data.datasource.remote.SearchDataSource
import com.higor.search.app.data.datasource.remote.SearchDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

internal val dataSourceModule = module {
    factory<SearchDataSource> { SearchDataSourceImpl(get()) }
    single<RecentSearchDataSource> { RecentSearchDataSourceImpl(androidContext()) }
}
