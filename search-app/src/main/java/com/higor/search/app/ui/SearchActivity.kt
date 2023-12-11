package com.higor.search.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.higor.search.app.databinding.ActivitySearchBinding
import com.higor.search.app.ui.adapter.search.OnClickImageListener
import com.higor.search.app.ui.adapter.recent.OnClickRecentListener
import com.higor.search.app.ui.adapter.search.SearchImageAdapter
import com.higor.search.app.ui.adapter.recent.RecentSearchAdapter
import com.higor.search.app.ui.base.BaseActivityViewBinding
import com.higor.search.app.ui.model.SearchItemUiModel
import com.higor.search.app.ui.viewmodel.SearchViewModel
import com.higor.search.app.utils.EmptyRecentState
import com.higor.search.app.utils.EmptyState
import com.higor.search.app.utils.ErrorState
import com.higor.search.app.utils.LoadingState
import com.higor.search.app.utils.SuccessState
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class SearchActivity : BaseActivityViewBinding<ActivitySearchBinding>(),
    OnClickImageListener, OnClickRecentListener {
    private val searchViewModel: SearchViewModel by viewModel()
    private val searchAdapter: SearchImageAdapter by lazy { SearchImageAdapter(this) }
    private val recentSearchedAdapter: RecentSearchAdapter by lazy { RecentSearchAdapter(this) }

    override fun setupViewBinding(inflater: LayoutInflater) =
        ActivitySearchBinding.inflate(inflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        initView()
        configObservers()
    }

    private fun initView() {
        binding.rvSearchImage.layoutManager = GridLayoutManager(this, 2)
        binding.rvSearchImage.adapter = searchAdapter

        binding.rvRecentSearched.layoutManager = LinearLayoutManager(this)
        binding.rvRecentSearched.adapter = recentSearchedAdapter

        setUpSearchKeyboard()
        setUpBackPress()
    }

    private fun setUpSearchKeyboard() {
        binding.searchView.editText.setOnEditorActionListener { text, action, _ ->
            if (action == IME_ACTION_SEARCH) {
                hideSearchView()
                searchViewModel.search(text.text.toString())
            }

            true
        }
    }

    private fun setUpBackPress() {
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (binding.searchView.isShowing)
                    hideSearchView()
                else {
                    finish()
                }
            }
        }

        onBackPressedDispatcher.addCallback(
            this,
            onBackPressedCallback
        )
    }

    private fun configObservers() {
        searchViewModel.searchImage.observe(this) { stateResponse ->
            when (stateResponse) {
                is SuccessState -> {
                    setSearchImages(stateResponse.data.resultSearch)
                    setRecentSearchedImages(stateResponse.data.recentSearch)
                    showRecentSearches()
                    hideEmptyErrorView()
                    hideProgress()
                    showSearchImageRecycler()
                }
                is LoadingState -> {
                    setTextSearchBar(stateResponse.searchText)
                    showRecentSearches()
                    hideSearchImageRecycler()
                    hideEmptyErrorView()
                    showProgress()
                }
                is ErrorState -> {
                    hideSearchImageRecycler()
                    hideProgress()
                    showErrorLayout()
                }
                is EmptyState -> {
                    setRecentSearchedImages(stateResponse.recentSearch)
                    showRecentSearches()
                    hideSearchImageRecycler()
                    hideProgress()
                    showEmptyLayout()
                }
                is EmptyRecentState -> {
                    hideRecentSearches()
                    hideSearchImageRecycler()
                    hideProgress()
                    showEmptyLayout()
                }
            }
        }
    }

    private fun setSearchImages(resultSearch: List<SearchItemUiModel>) {
        searchAdapter.submitList(resultSearch)
    }
    private fun setTextSearchBar(searchText: String) {
        binding.searchBar.text = searchText
    }

    private fun setRecentSearchedImages(searches: List<String>) {
        recentSearchedAdapter.submitList(searches)
    }

    private fun showEmptyLayout() {
        binding.emptySearch.llyEmpty.isVisible = true
    }

    private fun showErrorLayout() {
        binding.errorSearch.llyError.isVisible = true
    }

    private fun hideProgress() {
        binding.progressBarSearch.isVisible = false
    }

    private fun showProgress() {
        binding.progressBarSearch.isVisible = true
    }

    private fun hideEmptyErrorView() {
        binding.errorSearch.llyError.isVisible = false
        binding.emptySearch.llyEmpty.isVisible = false
    }

    private fun showSearchImageRecycler() {
        binding.rvSearchImage.isVisible = true
    }

    private fun hideSearchImageRecycler() {
        binding.rvSearchImage.isVisible = false
    }

    private fun showRecentSearches() {
        binding.rvRecentSearched.isVisible = true
        binding.tvEmptyRecent.isVisible = false
    }

    private fun hideRecentSearches() {
        binding.rvRecentSearched.isVisible = false
        binding.tvEmptyRecent.isVisible = true
    }

    private fun hideSearchView() {
        binding.searchView.hide()
    }

    override fun onClickImage(searchItem: SearchItemUiModel) {
        startActivity(DetailActivity.callThisActivity(this, searchItem))
    }

    override fun onClickRecentSearch(searchedText: String) {
        searchViewModel.search(searchedText)
        hideSearchView()
    }
}
