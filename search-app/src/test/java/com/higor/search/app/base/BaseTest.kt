package com.higor.search.app.base

import android.os.Build
import com.higor.search.app.di.SearchKoinContext
import com.higor.search.app.di.SearchKoinModuleProvider
import com.higor.search.app.di.base.BaseKoinContext
import com.higor.search.app.navigation.SearchNavigationExternalRoutesProvider
import io.mockk.mockk
import org.junit.Rule
import org.koin.core.module.Module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.KoinTestRule
import org.robolectric.annotation.Config

@Config(application = TestApplication::class, sdk = [Build.VERSION_CODES.O])
internal open class BaseTest(
    private val robotBaseTest: BaseRobotTest,
    private val app: BaseKoinContext = SearchKoinContext
) : AutoCloseKoinTest() {

    @get:Rule
    val koinRule = KoinTestRule.create {

        val modules = mutableListOf<Module>()
        val navigationMock = mockk<SearchNavigationExternalRoutesProvider>(relaxed = true)
        modules.addAll(SearchKoinModuleProvider().provide(navigationMock))

        robotBaseTest.getModule()?.let {
            modules.add(it)
        }

        this.modules(modules)
        app.koinApp = this
    }
}
