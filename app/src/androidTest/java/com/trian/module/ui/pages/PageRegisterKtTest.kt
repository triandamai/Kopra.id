package com.trian.module.ui.pages

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ExperimentalAnimatedInsets
import com.trian.component.ui.theme.TesMultiModuleTheme
import com.trian.module.ui.pages.auth.PageRegister
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PageRegisterKtTest{
    @get:Rule
    val composeRule = createComposeRule()

    @ExperimentalComposeUiApi
    @ExperimentalAnimatedInsets
    @Before
    fun setUp(){
        composeRule.setContent {
            TesMultiModuleTheme {
                PageRegister(nav = rememberNavController(), viewModel = viewModel(), scope = rememberCoroutineScope())
            }
        }
    }

    @Test
    fun shouldShowRegister(){

    }

}