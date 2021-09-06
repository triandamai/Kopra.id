package com.trian.bmi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trian.component.bottomsheet.ContentBottomSheetDevices
import com.trian.component.bottomsheet.ModalBottomSheetMainscreenView
import com.trian.component.ui.theme.*


class BmiActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContent {
                TesMultiModuleTheme{
                    val modalBottomSheetState = rememberModalBottomSheetState(
                        initialValue =ModalBottomSheetValue.Hidden
                    )
                    val scope = rememberCoroutineScope()
                    ModalBottomSheetLayout(
                        sheetState = modalBottomSheetState,
                        sheetShape = RoundedCornerShape(
                            topStart = 15.dp,
                            topEnd = 15.dp,
                        ),
                        sheetContent = {
                            ContentBottomSheetDevices(devices = listOf(com.trian.domain.models.Devices("Microlife", "F9:CC:DD:F3:ZA", 6)))
                        }
                    ) {
                        ModalBottomSheetMainscreenView(scope, modalBottomSheetState)
                    }
                }
            }
    }
}


@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}

@Preview
@Composable
fun DefaultPreview() {
    TesMultiModuleTheme {
    }
}




