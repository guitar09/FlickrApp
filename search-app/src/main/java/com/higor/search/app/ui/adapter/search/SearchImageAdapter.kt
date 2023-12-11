package com.higor.search.app.ui.adapter.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.higor.search.app.databinding.SearchItemViewBinding
import com.higor.search.app.ui.model.SearchItemUiModel

internal class SearchImageAdapter(private val onClickImage: OnClickImageListener) :
    ListAdapter<SearchItemUiModel, SearchImageHolder>(SearchImageDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchImageHolder {
        return SearchImageHolder(
            SearchItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClickImage
        )
    }

    override fun onBindViewHolder(holder: SearchImageHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

internal interface OnClickImageListener {
    fun onClickImage(searchItem: SearchItemUiModel)
}
