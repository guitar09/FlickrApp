package com.higor.search.app.base

import android.app.Application
import com.higor.search.app.R

internal class TestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setTheme(R.style.Theme_TestTheme)
    }
}
