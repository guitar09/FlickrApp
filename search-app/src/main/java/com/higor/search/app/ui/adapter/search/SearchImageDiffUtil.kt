package com.higor.search.app.ui.adapter.search

import androidx.recyclerview.widget.DiffUtil
import com.higor.search.app.ui.model.SearchItemUiModel

internal object SearchImageDiffUtil : DiffUtil.ItemCallback<SearchItemUiModel>() {

    override fun areItemsTheSame(
        oldItem: SearchItemUiModel,
        newItem: SearchItemUiModel
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: SearchItemUiModel,
        newItem: SearchItemUiModel
    ): Boolean {
        return oldItem == newItem
    }
}
