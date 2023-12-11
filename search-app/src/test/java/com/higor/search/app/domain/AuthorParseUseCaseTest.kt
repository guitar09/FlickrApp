package com.higor.search.app.domain

import com.google.common.truth.Truth
import com.higor.search.app.domain.usecase.AuthorParseUseCaseImpl
import org.junit.Test

internal class AuthorParseUseCaseTest {

    @Test
    fun `check parse`() {
        val mock = "nobody@flickr.com (\"TursiopsTobie\")"
        val authorParse = AuthorParseUseCaseImpl().invoke(mock)

        val expected = "TursiopsTobie"
        Truth.assertThat(authorParse).isEqualTo(expected)

    }
}
