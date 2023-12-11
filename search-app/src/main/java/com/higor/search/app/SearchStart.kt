package com.higor.search.app

import android.content.Context
import android.content.Intent
import com.higor.search.app.di.SearchKoinContext
import com.higor.search.app.navigation.SearchNavigationExternalRoutesProvider
import com.higor.search.app.ui.SearchActivity

object SearchStart {

    fun start(context: Context, navigationProvider: SearchNavigationExternalRoutesProvider) {
        SearchKoinContext.initialize(context, navigationProvider)
        context.startActivity(Intent(context, SearchActivity::class.java))
    }
}
