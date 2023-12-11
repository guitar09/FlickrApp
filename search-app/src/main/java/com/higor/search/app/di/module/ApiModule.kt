package com.higor.search.app.di.module


import com.higor.search.app.data.datasource.remote.ClientNetwork
import com.higor.search.app.data.datasource.remote.ClientNetwork.createRetrofitService
import com.higor.search.app.data.datasource.remote.SearchApi
import org.koin.dsl.module

internal val apiModule = module {
    single {
        createRetrofitService<SearchApi>(
            okHttpClient = ClientNetwork.provideOkHttpClient(),
            baseURL = SearchApi.BASE_URL
        )
    }
}
