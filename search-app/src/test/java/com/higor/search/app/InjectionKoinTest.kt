package com.higor.search.app

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.higor.search.app.di.SearchKoinModuleProvider
import com.higor.search.app.navigation.SearchNavigationExternalRoutesProvider
import io.mockk.mockk
import io.mockk.mockkObject
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.stopKoin
import org.koin.dsl.koinApplication
import org.koin.test.check.checkModules
import org.koin.test.mock.MockProviderRule

@RunWith(AndroidJUnit4::class)
internal class InjectionKoinTest {

    @get:Rule
    val observerRuler = InstantTaskExecutorRule()

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz -> mockkObject(clazz.java) }

    @Before
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `check injections module`() {

        val mockContext = mockk<Application>()
        val navigationMock = mockk<SearchNavigationExternalRoutesProvider>(relaxed = true)
        koinApplication {
            androidContext(mockContext)
            modules(SearchKoinModuleProvider().provide(navigationMock))
        }.checkModules {
            withInstance(mockk<SavedStateHandle>())
        }
    }
}
