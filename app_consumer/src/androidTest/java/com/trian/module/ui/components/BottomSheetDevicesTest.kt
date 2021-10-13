package com.trian.module.ui.components

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.junit4.createComposeRule
import com.trian.component.bottomsheet.BottomSheetDevices
import com.trian.component.ui.theme.TesMultiModuleTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BottomSheetDevicesTest {
    @get:Rule
    val composeRule = createComposeRule()

    @ExperimentalMaterialApi
    @Before
    fun setUp(){
        composeRule.setContent {
            TesMultiModuleTheme {
                BottomSheetDevices()
            }
        }
    }

    @Test
    fun shouldShowAppBar(){

    }
}