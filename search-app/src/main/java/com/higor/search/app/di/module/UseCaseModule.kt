package com.higor.search.app.di.module

import com.higor.search.app.domain.usecase.AuthorParseUseCaseImpl
import com.higor.search.app.domain.usecase.DescriptionImageParseUseCaseImpl
import com.higor.search.app.domain.usecase.GetRecentSearchUseCaseImpl
import com.higor.search.app.domain.usecase.SaveRecentSearchUseCaseImpl
import com.higor.search.app.domain.usecase.SearchImageUseCaseImpl
import com.higor.search.app.domain.usecase.SizeImageParseUseCaseImpl
import com.higor.search.app.domain.usecase.base.AuthorParseUseCase
import com.higor.search.app.domain.usecase.base.DescriptionImageParseUseCase
import com.higor.search.app.domain.usecase.base.GetRecentSearchUseCase
import com.higor.search.app.domain.usecase.base.SaveRecentSearchUseCase
import com.higor.search.app.domain.usecase.base.SearchImageUseCase
import com.higor.search.app.domain.usecase.base.SizeImageParseUseCase
import org.koin.dsl.module

internal val useCaseModule = module {
    factory<SearchImageUseCase> {
        SearchImageUseCaseImpl(
            searchRepository = get(),
            imageParse = get(),
            descriptionParse = get(),
            authorParse = get()
        )
    }

    factory<SizeImageParseUseCase> { SizeImageParseUseCaseImpl() }
    factory<DescriptionImageParseUseCase> { DescriptionImageParseUseCaseImpl() }
    factory<SaveRecentSearchUseCase> { SaveRecentSearchUseCaseImpl(get()) }
    factory<GetRecentSearchUseCase> { GetRecentSearchUseCaseImpl(get()) }
    factory<AuthorParseUseCase> { AuthorParseUseCaseImpl() }
}
