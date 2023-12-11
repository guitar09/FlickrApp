package com.higor.search.app.domain

import com.google.common.truth.Truth
import com.higor.search.app.data.repository.SearchRepository
import com.higor.search.app.domain.usecase.AuthorParseUseCaseImpl
import com.higor.search.app.domain.usecase.DescriptionImageParseUseCaseImpl
import com.higor.search.app.domain.usecase.SearchImageUseCaseImpl
import com.higor.search.app.domain.usecase.SizeImageParseUseCaseImpl
import com.higor.search.app.stubs.SearchResponseStub
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class SearchImageUseCaseTest {

    @Test
    fun `check parse`() {

        runTest {
            val repository = mockk<SearchRepository>(relaxed = true)

            coEvery {
                repository.searchImages(any())
            } returns SearchResponseStub.create()

            val useCase = SearchImageUseCaseImpl(
                repository,
                SizeImageParseUseCaseImpl(),
                DescriptionImageParseUseCaseImpl(),
                AuthorParseUseCaseImpl()
            )

            val resultSearch = useCase.invoke("SEARCH")

            val expectedDescription = "Please this info is just an example"
            val expectedSizeList = 1
            val expectedWidth = 240
            val expectedHeight = 160
            val expectedAuthor = "FAKE_AUTHOR_ITEM"

            Truth.assertThat(resultSearch.size).isEqualTo(expectedSizeList)
            Truth.assertThat(resultSearch[0].descriptionText).isEqualTo(expectedDescription)
            Truth.assertThat(resultSearch[0].width).isEqualTo(expectedWidth)
            Truth.assertThat(resultSearch[0].height).isEqualTo(expectedHeight)
            Truth.assertThat(resultSearch[0].author).isEqualTo(expectedAuthor)

        }
    }
}
