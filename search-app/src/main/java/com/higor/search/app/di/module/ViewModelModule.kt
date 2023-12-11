package com.higor.search.app.di.module

import androidx.lifecycle.SavedStateHandle
import com.higor.search.app.ui.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {
    viewModel { (handle: SavedStateHandle) ->
        SearchViewModel(
            handle,
            get(),
            get(),
            get(),
        )
    }
}
