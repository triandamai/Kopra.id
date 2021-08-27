package com.trian.module.ui.pages

import androidx.compose.material.Text
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.trian.microlife.ui.theme.TesMultiModuleTheme
import com.trian.module.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


//@RunWith(AndroidJUnit4::class)
class PageSplashScreenKtTest{

//    @get:Rule(order = 0)
//    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()


    @Test
    fun shoulCheckTextRun(){
        composeTestRule.setContent {
            TesMultiModuleTheme {
                Text("Ini text")
            }
        }
        composeTestRule.onNodeWithText("Ini text").assertExists()
    }

}