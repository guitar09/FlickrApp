package com.higor.search.app.ui.component

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.higor.search.app.base.BaseComponentTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class RecentRowSearchViewTest : BaseComponentTest() {

    private lateinit var robot: RecentRowSearchViewTestRobot

    @Before
    fun setup() {
        robot = RecentRowSearchViewTestRobot()
    }

    @After
    fun tearDown() {
        robot.tearDown()
    }

    @Test
    fun `initial config`() {

        with(robot) {
            arrange {
                startView(createViewActivity())
            }
            act {
                setUpComponent()
            }
            assert {
                checkDefaultComponent()
            }
        }
    }

    @Test
    fun `when set an image, should display the correct image`() {

        with(robot) {
            arrange {
                startView(createViewActivity())
            }
            act {
                setUpComponent(android.R.drawable.ic_menu_close_clear_cancel)
            }
            assert {
                checkWasSetImage(android.R.drawable.ic_menu_close_clear_cancel)
            }
        }
    }
}
