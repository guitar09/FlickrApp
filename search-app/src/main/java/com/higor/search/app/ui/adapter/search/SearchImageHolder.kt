package com.higor.search.app.ui.adapter.search

import androidx.recyclerview.widget.RecyclerView
import com.higor.search.app.databinding.SearchItemViewBinding
import com.higor.search.app.ui.component.SearchCardView
import com.higor.search.app.ui.model.SearchItemUiModel

internal class SearchImageHolder(private val view: SearchItemViewBinding, private val onClickImage: OnClickImageListener) :
    RecyclerView.ViewHolder(view.root) {
    fun bind(model: SearchItemUiModel) {

        itemView.setOnClickListener {
            onClickImage.onClickImage(model)
        }

        view.searchItem.bindView(
            SearchCardView.SearchCardViewBind(model.imageUrl, model.title)
        )

    }
}
