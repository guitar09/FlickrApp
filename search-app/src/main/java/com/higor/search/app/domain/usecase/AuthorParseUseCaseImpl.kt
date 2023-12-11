package com.higor.search.app.domain.usecase

import com.higor.search.app.domain.usecase.base.AuthorParseUseCase

internal class AuthorParseUseCaseImpl : AuthorParseUseCase {
    override fun invoke(text: String): String {
        return text.substringAfter("(\"").substringBefore("\")")
    }
}
