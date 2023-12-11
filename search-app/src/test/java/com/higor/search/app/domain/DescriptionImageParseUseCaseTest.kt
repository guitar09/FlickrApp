package com.higor.search.app.domain

import com.google.common.truth.Truth
import com.higor.search.app.domain.usecase.DescriptionImageParseUseCaseImpl
import com.higor.search.app.stubs.HtmlStub
import org.junit.Test

internal class DescriptionImageParseUseCaseTest {

    @Test
    fun `check parse`() {
        val html = HtmlStub.create()
        val descriptionParsed = DescriptionImageParseUseCaseImpl().invoke(html)

        val expectedDescription = "Please this info is just an example"
        Truth.assertThat(descriptionParsed).isEqualTo(expectedDescription)
    }

    @Test
    fun `check incorrect parse`() {
        val html = "lallalal"
        val descriptionParsed = DescriptionImageParseUseCaseImpl().invoke(html)

        val expectedDescription = "NOT FOUND"
        Truth.assertThat(descriptionParsed).isEqualTo(expectedDescription)
    }
}
