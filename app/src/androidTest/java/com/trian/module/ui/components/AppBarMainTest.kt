package com.trian.module.ui.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.trian.component.appbar.AppbarMainPage
import com.trian.component.ui.theme.TesMultiModuleTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AppBarMainTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        composeRule.setContent {
            TesMultiModuleTheme {
                AppbarMainPage("","Trian Damai",{})
            }
        }
    }

    @Test
    fun shouldShowAppBar() {
        composeRule.onNodeWithText("Hello Trian Damai").assertExists()
    }
}