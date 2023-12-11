package com.higor.search.app.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.higor.search.app.databinding.RecentRowSearchComponentViewBinding
import com.higor.search.app.utils.AccessibilityUtils.setViewAsButtonForAccessibility

class RecentRowSearchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        RecentRowSearchComponentViewBinding.inflate(LayoutInflater.from(context), this, true)

    fun bindView(text: String, imageResource: Int? = null) {
        binding.tvSearchTitle.text = text

        imageResource?.let { resource ->
            binding.imgIconSearch.setImageResource(resource)
        }

        setUpAccessibility(text)
    }

    private fun setUpAccessibility(text: String) {
        binding.clRecentRowView.contentDescription = text
        binding.clRecentRowView.setViewAsButtonForAccessibility()
    }
}
