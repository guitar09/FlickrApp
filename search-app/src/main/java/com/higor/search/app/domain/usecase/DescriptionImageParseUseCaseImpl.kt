package com.higor.search.app.domain.usecase

import com.higor.search.app.domain.usecase.base.DescriptionImageParseUseCase
import com.higor.search.app.domain.utils.Message

internal class DescriptionImageParseUseCaseImpl : DescriptionImageParseUseCase {
    override fun invoke(text: String): String {
        val description = text.split("</p>")
        if (description.size > 3 && description[2].contains("<p>")) {
            return description[2].replace("<p>", "").trim()
        }

        return Message.NOT_FOUND
    }
}
