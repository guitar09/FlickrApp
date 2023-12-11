package com.higor.search.app.domain

import com.google.common.truth.Truth
import com.higor.search.app.data.repository.RecentSearchRepository
import com.higor.search.app.domain.usecase.SaveRecentSearchUseCaseImpl
import io.mockk.coEvery
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.slot
import kotlinx.coroutines.test.runTest
import org.junit.Test

class SaveRecentSearchUseCaseTest {

    @Test
    fun `when search new item, should add new item`() {
        runTest {
            val mockRepository = mockk<RecentSearchRepository>()

            val readRecentSearch = slot<List<String>>()
            val mockReturn = listOf("ONE", "TWO")
            coEvery {
                mockRepository.insertSearches(capture(readRecentSearch))
            } just runs

            coEvery {
                mockRepository.getSearches()
            } returns mockReturn

            SaveRecentSearchUseCaseImpl(mockRepository).invoke("THREE")
            Truth.assertThat(readRecentSearch.captured.size).isEqualTo(3)
        }
    }

    @Test
    fun `when search same item, should move item to top`() {
        runTest {
            val mockRepository = mockk<RecentSearchRepository>()

            val readRecentSearch = slot<List<String>>()
            val mockReturn = listOf("ONE", "TWO", "THREE")
            coEvery {
                mockRepository.insertSearches(capture(readRecentSearch))
            } just runs

            coEvery {
                mockRepository.getSearches()
            } returns mockReturn

            SaveRecentSearchUseCaseImpl(mockRepository).invoke("TWO")
            Truth.assertThat(readRecentSearch.captured.last()).isEqualTo("TWO")
        }
    }

    @Test
    fun `when search new item and already have 5 items, should replace the last item`() {
        runTest {
            val mockRepository = mockk<RecentSearchRepository>()

            val readRecentSearch = slot<List<String>>()
            val mockReturn = listOf("ONE", "TWO", "THREE", "FOUR", "FIVE")
            coEvery {
                mockRepository.insertSearches(capture(readRecentSearch))
            } just runs

            coEvery {
                mockRepository.getSearches()
            } returns mockReturn

            SaveRecentSearchUseCaseImpl(mockRepository).invoke("SIX")
            Truth.assertThat(readRecentSearch.captured.last()).isEqualTo("SIX")
            Truth.assertThat(readRecentSearch.captured.size).isEqualTo(5)
        }
    }
}
