package com.higor.search.app.ui.component

import androidx.appcompat.app.AppCompatActivity
import com.google.common.truth.Truth
import com.higor.search.app.base.BaseRobotComponentTest
import com.higor.search.app.databinding.CardImageComponentViewBinding
import io.mockk.unmockkAll

internal class SearchCardViewTestRobot : BaseRobotComponentTest {

    private lateinit var companyView: SearchCardView
    private lateinit var binding: CardImageComponentViewBinding

    override fun tearDown() {
        unmockkAll()
    }

    inner class Arrange {
        fun startView(activity: AppCompatActivity) {
            companyView = SearchCardView(activity)
            binding = CardImageComponentViewBinding.bind(companyView)
        }
    }

    inner class Act {
        fun setUpComponent() {
            companyView.bindView(SearchCardView.SearchCardViewBind("FAKE_URL", "TITLE_TEXT"))
        }
    }

    inner class Assert {
        fun checkComponent() {
            val expected = "TITLE_TEXT"
            Truth.assertThat(binding.cardTitle.text).isEqualTo(expected)
        }
    }

    fun arrange(func: Arrange.() -> Unit) = Arrange().apply(func)
    fun act(func: Act.() -> Unit) = Act().apply(func)
    fun assert(func: Assert.() -> Unit) = Assert().apply(func)
}
