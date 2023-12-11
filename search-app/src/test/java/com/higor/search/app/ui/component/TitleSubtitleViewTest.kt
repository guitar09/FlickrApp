package com.higor.search.app.ui.component

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.higor.search.app.base.BaseComponentTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class TitleSubtitleViewTest : BaseComponentTest() {

    private lateinit var robot: TitleSubtitleViewTestRobot

    @Before
    fun setup() {
        robot = TitleSubtitleViewTestRobot()
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
                checkComponent()
            }
        }
    }
}
