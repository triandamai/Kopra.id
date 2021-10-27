package com.trian.kopra.ui.pages

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.trian.common.utils.route.Routes
import com.trian.component.ui.theme.TesMultiModuleTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PageDashboardKtTest{
    @get:Rule
    val composeRule = createComposeRule()

    @ExperimentalAnimationApi
    @Before
    fun setUp(){
        composeRule.setContent {
            TesMultiModuleTheme {
                PageDashboard(
                    nav = rememberNavController(),
                    scope = rememberCoroutineScope(),
                    mainViewModel = viewModel(),
                    page = Routes.Dashboard.HOME,
                    toFeature = {},
                    changeStatusBar ={}
                ) {

                }
            }
        }
    }

    @Test
    fun shouldShowTextAndButton(){
        composeRule.onNodeWithText("Hello!").assertExists()
        composeRule.onNodeWithText("Health Status").assertExists()
    }
}