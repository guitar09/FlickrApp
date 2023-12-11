package com.higor.flickrapp.module.search

import android.content.Context
import com.higor.search.app.SearchStart

internal object SearchConfigModule {
    fun startModule(context: Context) {
        SearchStart.start(context, SearchExternalRoutes())
    }
}
