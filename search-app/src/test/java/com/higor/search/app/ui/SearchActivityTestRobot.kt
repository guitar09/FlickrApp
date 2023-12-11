package com.higor.search.app.ui

import android.content.Intent
import android.os.Looper
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.SavedStateHandle
import com.google.common.truth.Truth
import com.higor.search.app.base.BaseRobotTest
import com.higor.search.app.base.assertNextActivity
import com.higor.search.app.base.onExpand
import com.higor.search.app.base.robolectricBuilder
import com.higor.search.app.data.repository.SearchRepository
import com.higor.search.app.databinding.ActivitySearchBinding
import com.higor.search.app.domain.usecase.base.GetRecentSearchUseCase
import com.higor.search.app.domain.usecase.base.SaveRecentSearchUseCase
import com.higor.search.app.stubs.SearchResponseStub
import com.higor.search.app.ui.adapter.search.SearchImageAdapter
import com.higor.search.app.ui.viewmodel.SearchViewModel
import io.mockk.coEvery
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.unmockkAll
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import org.robolectric.Shadows
import org.robolectric.Shadows.shadowOf
import org.robolectric.shadows.ShadowActivity

internal object SearchActivityTestRobot : BaseRobotTest {

    private lateinit var activity: SearchActivity
    private lateinit var binding: ActivitySearchBinding

    private val useCaseSaveRecent = mockk<SaveRecentSearchUseCase>(relaxed = true)
    private val useCaseGetRecent = mockk<GetRecentSearchUseCase>(relaxed = true)

    val repository = mockk<SearchRepository>(relaxed = true)


    override fun tearDown() {
        unmockkAll()
    }


    override fun getModule(): Module {
        val mockModule = module {
            viewModel { (handle: SavedStateHandle) ->
                SearchViewModel(
                    handle,
                    get(),
                    useCaseSaveRecent,
                    useCaseGetRecent
                )
            }

            factory { repository }
        }

        return mockModule
    }


    fun executePendingTransactions() {
        activity.supportFragmentManager.executePendingTransactions()
    }

    fun callShadowOf() {
        Shadows.shadowOf(Looper.getMainLooper()).idle()
    }

    class Arrange {
        fun setup() {
            activity = robolectricBuilder(Intent(context, SearchActivity::class.java))
            binding = ActivitySearchBinding.bind(shadowOf(activity).contentView)

            executePendingTransactions()
        }

        fun mockSuccess() {
            coEvery { useCaseSaveRecent(any()) } just runs
            coEvery { useCaseSaveRecent(any()) } just runs
            coEvery { repository.searchImages(any()) } returns SearchResponseStub.create()
        }

        fun mockError() {
            coEvery { useCaseSaveRecent(any()) } throws Exception("ERROR")
        }
    }

    class Act {
       fun search() {
           binding.searchView.editText.onEditorAction(EditorInfo.IME_ACTION_SEARCH);
       }

       fun clickImage() {
           val rvLaunch = binding.rvSearchImage.onExpand().findViewHolderForAdapterPosition(0)
           rvLaunch?.itemView?.performClick()
       }
    }

    class Assert {
        fun checkEmptyState() {
            val text =  binding.emptySearch.tvEmpty.text
            val expectedText =  "Empty here"
            Truth.assertThat(text).isEqualTo(expectedText)
        }

        fun checkErrorSate() {
            val text =  binding.errorSearch.tvError.text
            val expectedText =  "Oops, something wrong"
            Truth.assertThat(text).isEqualTo(expectedText)
        }

        fun checkSearchImageResult() {
            val rvSearchImage = binding.rvSearchImage
            val itemsAdapter = (rvSearchImage.adapter as SearchImageAdapter).itemCount
            Truth.assertThat(itemsAdapter).isEqualTo(1)
        }

        fun checkOpenDetail() {
            shadowOf(activity).assertNextActivity(DetailActivity::class)
        }
    }

    fun arrange(func: Arrange.() -> Unit) = Arrange().apply(func)
    fun act(func: Act.() -> Unit) = Act().apply(func)
    fun assert(func: Assert.() -> Unit) = Assert().apply(func)

}
