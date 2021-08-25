package com.trian.module

import androidx.compose.material.Text
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class LoginScreenTest{
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `testCompose`(){
        composeTestRule.setContent {
            Text(text = "Ini text")
        }
    }
}