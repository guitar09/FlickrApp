package com.higor.search.app.ui

import android.os.Looper
import com.google.common.truth.Truth
import com.higor.search.app.base.BaseRobotTest
import com.higor.search.app.base.robolectricBuilder
import com.higor.search.app.databinding.ActivityDetailBinding
import com.higor.search.app.databinding.TitleSubtitleComponentViewBinding
import com.higor.search.app.stubs.SearchItemUiModelStub
import io.mockk.unmockkAll
import org.koin.core.module.Module
import org.robolectric.Shadows

internal object DetailActivityTestRobot : BaseRobotTest {

    private lateinit var activity: DetailActivity
    private lateinit var binding: ActivityDetailBinding

    override fun getModule(): Module? = null

    override fun tearDown() {
        unmockkAll()
    }

    fun executePendingTransactions() {
        activity.supportFragmentManager.executePendingTransactions()
    }

    fun callShadowOf() {
        Shadows.shadowOf(Looper.getMainLooper()).idle()
    }


    class Arrange {
        fun startActivity() {
            activity = robolectricBuilder(DetailActivity.callThisActivity(context, SearchItemUiModelStub.create()))
            binding = ActivityDetailBinding.bind(Shadows.shadowOf(activity).contentView)
            executePendingTransactions()
        }
    }

    class Act {
    }

    class Assert {
        fun checkSuccess() {
            val expectedTitle = "FAKE_TITLE"
            val expectedDescription = "FAKE_DESCRIPTION"
            val expectedAuthor = "FAKE_AUTHOR"
            val expectedSize = "width: 100 height: 200"

            Truth.assertThat(binding.tvDetailTitle?.text).isEqualTo(expectedTitle)

            binding.tsDetailDescription?.let {
                val bindingDescription = TitleSubtitleComponentViewBinding.bind(it)
                Truth.assertThat(bindingDescription.tvSubtitle.text).isEqualTo(expectedDescription)
            }

            binding.tsDetailSize?.let {
                val bindingDescription = TitleSubtitleComponentViewBinding.bind(it)
                Truth.assertThat(bindingDescription.tvSubtitle.text).isEqualTo(expectedSize)
            }

            binding.tsDetailAuthor?.let {
                val bindingDescription = TitleSubtitleComponentViewBinding.bind(it)
                Truth.assertThat(bindingDescription.tvSubtitle.text).isEqualTo(expectedAuthor)
            }

        }
    }

    fun arrange(func: Arrange.() -> Unit) = Arrange().apply(func)
    fun act(func: Act.() -> Unit) = Act().apply(func)
    fun assert(func: Assert.() -> Unit) = Assert().apply(func)
}