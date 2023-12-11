package com.higor.search.app.domain.usecase.base

import com.higor.search.app.domain.model.ImageSize

internal interface SizeImageParseUseCase {
    operator fun invoke(text: String): ImageSize
}
