package com.higor.search.app.ui.adapter.recent

import androidx.recyclerview.widget.RecyclerView
import com.higor.search.app.databinding.TipItemViewBinding

internal class RecentSearchHolder(
    private val view: TipItemViewBinding,
    private val onClickRecent: OnClickRecentListener
) :
    RecyclerView.ViewHolder(view.root) {
    fun bind(text: String) {
        itemView.setOnClickListener {
            onClickRecent.onClickRecentSearch(text)
        }

        view.tipItem.bindView(
            text
        )

    }
}
