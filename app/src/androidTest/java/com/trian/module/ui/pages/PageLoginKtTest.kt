package com.trian.module.ui.pages

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.trian.component.ui.theme.TesMultiModuleTheme
import com.trian.module.ui.pages.auth.PageLogin

import org.junit.Rule
import org.junit.Test

class PageLoginKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()


    @ExperimentalComposeUiApi
    @Test
    fun `shouldShowUsernameAndPassword`() {
        composeTestRule.setContent {
            TesMultiModuleTheme {
                PageLogin(
                    nav = rememberNavController(),
                    scope = rememberCoroutineScope() ,
                    viewModel = viewModel()
                )
            }
        }
        composeTestRule.onNodeWithText("Username").assertExists()
        composeTestRule.onNodeWithText("Password").assertExists()
    }

}