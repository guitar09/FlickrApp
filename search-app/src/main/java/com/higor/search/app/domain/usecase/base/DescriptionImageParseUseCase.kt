package com.higor.search.app.domain.usecase.base

internal interface DescriptionImageParseUseCase {
    operator fun invoke(text: String): String
}
