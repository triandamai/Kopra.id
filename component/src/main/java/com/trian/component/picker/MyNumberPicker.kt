package com.trian.component.picker

import android.widget.NumberPicker
import androidx.appcompat.view.ContextThemeWrapper
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.trian.component.R

@Composable
fun MyNumberPicker(
    min: Int,
    max: Int,
    value:Int=0,
    items: Array<String>,
    onValueChange: (old: Int, new: Int) -> Unit,
) {
    AndroidView({
        NumberPicker(
            ContextThemeWrapper(it, R.style.Chart).apply { }

        )
    }, update = {

            view ->
        view.maxValue = max
        view.minValue = min
        view.displayedValues = items
        view.value = value

        view.setOnValueChangedListener { _, oldval, newval ->
            onValueChange(oldval, newval)
        }
    })
}