package com.higor.search.app.base

import androidx.appcompat.app.AppCompatActivity
import org.robolectric.Robolectric

internal open class BaseComponentTest {
    fun createViewActivity(): AppCompatActivity =
        Robolectric.buildActivity(AppCompatActivity::class.java).create().get()
}
