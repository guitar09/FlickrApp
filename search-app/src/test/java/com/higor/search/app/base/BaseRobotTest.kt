package com.higor.search.app.base

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.koin.core.module.Module

internal interface BaseRobotTest {

    val context : Context
        get() = ApplicationProvider.getApplicationContext()

    fun getModule() : Module?
    fun tearDown()
}
