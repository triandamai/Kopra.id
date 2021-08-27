package com.trian.module.ui.pages

import androidx.compose.material.Text
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.trian.microlife.ui.theme.TesMultiModuleTheme
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PageLoginKtTest{
    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun `shouldShowUsernameAndPassword`(){
        composeTestRule.setContent {
            TesMultiModuleTheme {
                ComponentLogin() {

                }
            }
        }
        composeTestRule.onNodeWithText("Username").assertExists()
        composeTestRule.onNodeWithText("Password").assertExists()
    }

}