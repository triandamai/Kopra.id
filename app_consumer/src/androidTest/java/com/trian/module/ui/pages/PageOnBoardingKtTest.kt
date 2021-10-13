package com.trian.module.ui.pages

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.trian.component.ui.theme.TesMultiModuleTheme
import com.trian.module.ui.pages.auth.PageOnBoarding
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
                PageOnBoarding(nav = rememberNavController(), scope = rememberCoroutineScope())
            }
        }
    }
    @Test
    fun shouldShowOnBoarding(){
        composeRule.onNodeWithText("Page 1").assertIsDisplayed()

    }

    @Test
    fun shouldSwipeToNextPage(){
        composeRule.onRoot().performGesture { swipeRight() }
        composeRule.onNodeWithText("Page 2").assertExists()
    }
}