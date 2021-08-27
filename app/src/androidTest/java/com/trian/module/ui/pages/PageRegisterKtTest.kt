package com.trian.module.ui.pages

import androidx.compose.ui.test.junit4.createComposeRule
import com.trian.module.ui.theme.TesMultiModuleTheme
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PageRegisterKtTest{
    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setUp(){
        composeRule.setContent {
            TesMultiModuleTheme {
                ComponentRegister()
            }
        }
    }

    @Test
    fun `shouldShowRegister`(){

    }

}