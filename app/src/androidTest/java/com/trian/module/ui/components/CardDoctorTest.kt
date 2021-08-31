package com.trian.module.ui.components

import androidx.compose.ui.test.junit4.createComposeRule
import com.trian.component.appbar.AppbarMain
import com.trian.component.ui.theme.TesMultiModuleTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CardDoctorTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setUp(){
        composeRule.setContent {
            TesMultiModuleTheme {
                AppbarMain()
            }
        }
    }

    @Test
    fun shouldShowAppBar(){

    }
}