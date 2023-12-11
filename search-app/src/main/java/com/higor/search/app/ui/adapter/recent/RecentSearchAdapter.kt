package com.higor.search.app.ui.adapter.recent

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.higor.search.app.databinding.TipItemViewBinding

internal class RecentSearchAdapter(private val onClickRecent: OnClickRecentListener) :
    ListAdapter<String, RecentSearchHolder>(RecentSearchDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentSearchHolder {
        return RecentSearchHolder(
            TipItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClickRecent
        )
    }

    override fun onBindViewHolder(holder: RecentSearchHolder, position: Int) {
        holder.bind(getItem(position))
    }


}

internal interface OnClickRecentListener {
    fun onClickRecentSearch(searchedText: String)
}
