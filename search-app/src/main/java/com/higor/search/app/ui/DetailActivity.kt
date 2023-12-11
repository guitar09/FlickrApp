package com.higor.search.app.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import coil.load
import com.higor.search.app.R
import com.higor.search.app.databinding.ActivityDetailBinding
import com.higor.search.app.ui.base.BaseActivityViewBinding
import com.higor.search.app.ui.model.SearchItemUiModel
import com.higor.search.app.utils.AccessibilityUtils.setAsHeadingForAccessibility
import com.higor.search.app.utils.createCircularProgressDrawable
import com.higor.search.app.utils.toHtml

internal class DetailActivity : BaseActivityViewBinding<ActivityDetailBinding>() {

    override fun setupViewBinding(inflater: LayoutInflater) =
        ActivityDetailBinding.inflate(inflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() {
        getDetail()?.let { model ->
            setupImage(model.imageUrl)
            setupTitle(model.title)
            setupDescription(model.description)
            setupAuthor(model.author)
            setupSize(model.width, model.height)
        }
    }

    private fun setupImage(imageUrl: String) {
        binding.imgDetail.load(imageUrl) {
            placeholder(createCircularProgressDrawable(this@DetailActivity))
            error(android.R.drawable.stat_notify_error)
        }
    }

    private fun setupTitle(title: String) {
        binding.tvDetailTitle?.text = title
        binding.tvDetailTitle?.setAsHeadingForAccessibility()
    }

    private fun setupDescription(description: String) {
        binding.tsDetailDescription?.bindView(
            getString(R.string.description),
            description.toHtml().toString()
        )
    }

    private fun setupAuthor(author: String) {
        binding.tsDetailAuthor?.bindView(getString(R.string.author), author)
    }

    private fun setupSize(width: Int, height: Int) {
        binding.tsDetailSize?.bindView(
            getString(R.string.size),
            getString(
                R.string.detail_size, width, height
            ),
            getString(
                R.string.title_subtitle_accessibility,
                getString(R.string.size),
                getString(
                    R.string.detail_size_accessibility,
                    width,
                    height
                )
            )
        )
    }

    private fun getDetail(): SearchItemUiModel? = intent.extras?.getParcelable(BUNDLE_DETAIL)

    companion object {
        private const val BUNDLE_DETAIL = "BUNDLE_DETAIL"
        fun callThisActivity(context: Context, detail: SearchItemUiModel): Intent {
            return Intent(context, DetailActivity::class.java).apply {
                putExtra(BUNDLE_DETAIL, detail)
            }
        }
    }
}
