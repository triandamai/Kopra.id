package com.trian.module.ui.pages

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.trian.component.ui.theme.TesMultiModuleTheme

import org.junit.Rule
import org.junit.Test

class PageLoginKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun `shouldShowUsernameAndPassword`() {
        composeTestRule.setContent {
            TesMultiModuleTheme {
                ComponentLogin {

                }
            }
        }
        composeTestRule.onNodeWithText("Username").assertExists()
        composeTestRule.onNodeWithText("Password").assertExists()
    }

}