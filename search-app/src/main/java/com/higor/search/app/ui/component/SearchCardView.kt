package com.higor.search.app.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import coil.load
import coil.transform.RoundedCornersTransformation
import com.higor.search.app.databinding.CardImageComponentViewBinding
import com.higor.search.app.utils.AccessibilityUtils.setViewAsButtonForAccessibility
import com.higor.search.app.utils.createCircularProgressDrawable

class SearchCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding =
        CardImageComponentViewBinding.inflate(LayoutInflater.from(context), this, true)

    fun bindView(bind: SearchCardViewBind) {
        binding.cardTitle.text = bind.title
        binding.cardImage.load(bind.imageUrl) {
            placeholder(createCircularProgressDrawable(context))
            transformations(
                RoundedCornersTransformation(
                    topLeft = ROUNDED_IMAGE,
                    topRight = ROUNDED_IMAGE,
                    bottomLeft = ROUNDED_IMAGE,
                    bottomRight = ROUNDED_IMAGE
                )
            )
            error(android.R.drawable.stat_notify_error)
        }

        setupAccessibility(bind.title)
    }

    private fun setupAccessibility(text: String) {
        binding.cvCardImage.contentDescription = text
        binding.cvCardImage.setViewAsButtonForAccessibility()
    }

    data class SearchCardViewBind(val imageUrl: String, val title: String)

    private companion object {
        const val ROUNDED_IMAGE = 4.0F
    }
}
