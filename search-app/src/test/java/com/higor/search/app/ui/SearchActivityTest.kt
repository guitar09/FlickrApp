package com.higor.search.app.ui

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.higor.search.app.base.BaseTest
import com.higor.search.app.ui.SearchActivityTestRobot.arrange
import com.higor.search.app.ui.SearchActivityTestRobot.act
import com.higor.search.app.ui.SearchActivityTestRobot.assert
import com.higor.search.app.utils.MainDispatcherRule
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class SearchActivityTest : BaseTest(SearchActivityTestRobot) {

    @get:Rule
    val rule = MainDispatcherRule()

    @After
    fun tearDown() {
        SearchActivityTestRobot.tearDown()
    }

    @Test
    fun `when start activity, should show empty state`() {

        arrange {
            mockSuccess()
            setup()
        }

        assert {
            checkEmptyState()
        }
    }

    @Test
    fun `when search throws error, should show error state`() {

        arrange {
            mockError()
            setup()
        }

        assert {
            checkErrorSate()
        }
    }

    @Test
    fun `when search, should return one image`() {

        arrange {
            mockSuccess()
            setup()
        }

        act {
            search()
        }

        assert {
            checkSearchImageResult()
        }
    }

    @Test
    fun `when click an item, should open detail`() {

        arrange {
            mockSuccess()
            setup()
        }

        act {
            search()
            clickImage()
        }

        assert {
            checkOpenDetail()
        }
    }
}
