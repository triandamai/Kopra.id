package com.trian.module.ui.pages

import androidx.compose.material.Text
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.trian.component.ui.theme.TesMultiModuleTheme
import org.junit.Rule
import org.junit.Test


//@RunWith(AndroidJUnit4::class)
class PageSplashScreenKtTest {

//    @get:Rule(order = 0)
//    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()


    @Test
    fun shoulCheckTextRun() {
        composeTestRule.setContent {
            TesMultiModuleTheme {
                Text("Ini text")
            }
        }
        composeTestRule.onNodeWithText("Ini text").assertExists()
    }

}