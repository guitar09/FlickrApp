package com.higor.search.app.domain

import com.google.common.truth.Truth
import com.higor.search.app.data.repository.RecentSearchRepository
import com.higor.search.app.domain.usecase.GetRecentSearchUseCaseImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class GetRecentSearchUseCaseTest {

    @Test
    fun `check recent search was reversed`() {
        runTest {
            val mockRepository = mockk<RecentSearchRepository>(relaxed = true)

            val mockReturn = listOf("ONE", "TWO")
            coEvery { mockRepository.getSearches() } returns mockReturn

            val recentSearched = GetRecentSearchUseCaseImpl(mockRepository).invoke()
            Truth.assertThat(recentSearched).isEqualTo(mockReturn.reversed())
        }
    }
}
