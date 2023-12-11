package com.higor.search.app.domain.usecase.base

internal interface AuthorParseUseCase {
    operator fun invoke(text: String): String
}
