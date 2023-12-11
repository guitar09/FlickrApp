package com.higor.search.app.ui

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.higor.search.app.base.BaseTest
import com.higor.search.app.ui.DetailActivityTestRobot.act
import com.higor.search.app.ui.DetailActivityTestRobot.arrange
import com.higor.search.app.ui.DetailActivityTestRobot.assert
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class DetailActivityTest : BaseTest(DetailActivityTestRobot) {

    @After
    fun tearDown() {
        DetailActivityTestRobot.tearDown()
    }

    @Test
    fun `when call detail activity, should show all info correct`() {

        arrange {
            startActivity()
        }
        act {

        }
        assert {
           checkSuccess()
        }
    }
}
