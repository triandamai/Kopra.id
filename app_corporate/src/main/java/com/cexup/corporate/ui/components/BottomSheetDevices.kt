package com.cexup.corporate.ui.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope


@ExperimentalMaterialApi
@Composable
fun BottomSheetDialogDevices(coroutineScope: CoroutineScope){
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    BottomSheetScaffold(sheetContent = {}) {

    }
}