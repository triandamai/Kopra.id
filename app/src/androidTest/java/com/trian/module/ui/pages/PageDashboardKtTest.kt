package com.trian.module.ui.pages

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.trian.module.ui.theme.TesMultiModuleTheme
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PageDashboardKtTest{
    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setUp(){
        composeRule.setContent {
            TesMultiModuleTheme {
                ComponentDashboard(onNavigate = { /*TODO*/ })
            }
        }
    }

    @Test
    fun shouldShowTextAndButton(){
        composeRule.onNodeWithText("To Features").assertExists()
        composeRule.onNodeWithText("Ini dashboard")
    }
}