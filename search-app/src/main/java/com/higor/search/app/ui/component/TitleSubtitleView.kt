package com.higor.search.app.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.higor.search.app.R
import com.higor.search.app.databinding.TitleSubtitleComponentViewBinding

internal class TitleSubtitleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding =
        TitleSubtitleComponentViewBinding.inflate(LayoutInflater.from(context), this, true)

    fun bindView(title: String, subTitle: String, contentDescription: String? = null) {
        binding.tvTitle.text = title
        binding.tvSubtitle.text = subTitle

        contentDescription?.let { description ->
            binding.llViewTitleSubtitle.contentDescription = description
        } ?: run {
            binding.llViewTitleSubtitle.contentDescription =
                context.getString(R.string.title_subtitle_accessibility, title, subTitle)
        }
    }
}
