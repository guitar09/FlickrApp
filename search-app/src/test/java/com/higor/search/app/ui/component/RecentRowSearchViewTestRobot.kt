package com.higor.search.app.ui.component

import androidx.appcompat.app.AppCompatActivity
import com.google.common.truth.Truth
import com.higor.search.app.base.BaseRobotComponentTest
import com.higor.search.app.databinding.RecentRowSearchComponentViewBinding
import io.mockk.unmockkAll
import org.robolectric.Shadows
import com.higor.search.app.R

internal class RecentRowSearchViewTestRobot : BaseRobotComponentTest {

    private lateinit var companyView: RecentRowSearchView
    private lateinit var binding: RecentRowSearchComponentViewBinding

    override fun tearDown() {
        unmockkAll()
    }

    inner class Arrange {
        fun startView(activity: AppCompatActivity) {
            companyView = RecentRowSearchView(activity)
            binding = RecentRowSearchComponentViewBinding.bind(companyView)
        }
    }

    inner class Act {
        fun setUpComponent(imageResource: Int? = null) {
            companyView.bindView("RECENT_SEARCH", imageResource)
        }
    }

    inner class Assert {
        fun checkDefaultComponent() {
            val expected = "RECENT_SEARCH"
            Truth.assertThat(binding.tvSearchTitle.text).isEqualTo(expected)

            val drawableResId: Int =
                Shadows.shadowOf(binding.imgIconSearch.drawable).createdFromResId

            Truth.assertThat(R.drawable.baseline_restore_24).isEqualTo(drawableResId)
        }

        fun checkWasSetImage(checkResource: Int) {
            val expected = "RECENT_SEARCH"
            Truth.assertThat(binding.tvSearchTitle.text).isEqualTo(expected)

            val drawableResId: Int =
                Shadows.shadowOf(binding.imgIconSearch.drawable).createdFromResId

            Truth.assertThat(checkResource).isEqualTo(drawableResId)
        }
    }

    fun arrange(func: Arrange.() -> Unit) = Arrange().apply(func)
    fun act(func: Act.() -> Unit) = Act().apply(func)
    fun assert(func: Assert.() -> Unit) = Assert().apply(func)
}

