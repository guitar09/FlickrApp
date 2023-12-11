package com.higor.search.app.domain.usecase

import com.higor.search.app.domain.model.ImageSize
import com.higor.search.app.domain.usecase.base.SizeImageParseUseCase

internal class SizeImageParseUseCaseImpl : SizeImageParseUseCase {
    override fun invoke(text: String): ImageSize {
        val width = text.substringAfter("width=").substringBefore(" ")
        val height = text.substringAfter("height=").substringBefore(" ")

        return if (width.isNotEmpty() && height.isNotEmpty()) ImageSize(
            width = width.replace("\"", "").toInt(),
            height = height.replace("\"", "").toInt()
        ) else {
            ImageSize(width = WIDTH_DEFAULT, height = HEIGHT_DEFAULT)
        }

    }

    private companion object {
        const val WIDTH_DEFAULT = 0
        const val HEIGHT_DEFAULT = 0
    }
}
