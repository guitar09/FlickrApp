package com.higor.search.app.ui.component

import androidx.appcompat.app.AppCompatActivity
import com.google.common.truth.Truth
import com.higor.search.app.base.BaseRobotComponentTest
import com.higor.search.app.databinding.TitleSubtitleComponentViewBinding
import io.mockk.unmockkAll

internal class TitleSubtitleViewTestRobot : BaseRobotComponentTest {

    private lateinit var companyView: TitleSubtitleView
    private lateinit var binding: TitleSubtitleComponentViewBinding

    override fun tearDown() {
        unmockkAll()
    }

    inner class Arrange {
        fun startView(activity: AppCompatActivity) {
            companyView = TitleSubtitleView(activity)
            binding = TitleSubtitleComponentViewBinding.bind(companyView)
        }
    }

    inner class Act {
        fun setUpComponent() {
            companyView.bindView("FAKE_TILE", "SUBTITLE_FAKE")
        }
    }

    inner class Assert {
        fun checkComponent() {
            val expectedTitle = "FAKE_TILE"
            Truth.assertThat(binding.tvTitle.text).isEqualTo(expectedTitle)

            val expectedSubtitle = "SUBTITLE_FAKE"
            Truth.assertThat(binding.tvSubtitle.text).isEqualTo(expectedSubtitle)
        }
    }

    fun arrange(func: Arrange.() -> Unit) = Arrange().apply(func)
    fun act(func: Act.() -> Unit) = Act().apply(func)
    fun assert(func: Assert.() -> Unit) = Assert().apply(func)
}
