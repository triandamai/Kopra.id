package com.trian.module.ui.pages

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.google.accompanist.pager.ExperimentalPagerApi
import com.trian.module.ui.theme.TesMultiModuleTheme
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PageOnBoardingKtTest{
    @get:Rule
    val composeRule = createComposeRule()

    @ExperimentalPagerApi
    @Before
    fun setUp(){
        composeRule.setContent {
            TesMultiModuleTheme {
                ComponentOnBoarding(scope = rememberCoroutineScope(), onNavigate = {})
            }
        }
    }
    @Test
    fun `shouldShowOboarding`(){
        composeRule.onNodeWithText("Page 1").assertIsDisplayed()

    }

    @Test
    fun `shouldSwipeToNextPage`(){
        composeRule.onRoot().performGesture { swipeRight() }
        composeRule.onNodeWithText("Page 2").assertExists()
    }
}