package com.higor.search.app.domain

import com.google.common.truth.Truth
import com.higor.search.app.domain.usecase.SizeImageParseUseCaseImpl
import com.higor.search.app.stubs.HtmlStub
import org.junit.Test

internal class SizeImageParseUseCaseTest {

    @Test
    fun `check parse`() {
        val html = HtmlStub.create()
        val sizeImage = SizeImageParseUseCaseImpl().invoke(html)

        val expectedWidth = 240
        val expectedHeight = 160
        Truth.assertThat(sizeImage.width).isEqualTo(expectedWidth)
        Truth.assertThat(sizeImage.height).isEqualTo(expectedHeight)
    }
}
