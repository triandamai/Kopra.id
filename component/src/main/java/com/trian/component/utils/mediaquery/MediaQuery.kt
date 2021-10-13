package com.trian.component.utils.mediaquery

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * `Support Different Screen Size`
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 13/10/2021
 */

sealed class Dimensions {
    object Width:Dimensions()
    object Height:Dimensions()

    sealed class DimensionOperator{
        object LessThan : DimensionOperator()
        object GreaterThan:DimensionOperator()
    }

    class DimensionComparator(
        val operator:DimensionOperator,
        private val dimension: Dimensions,
        val value: Dp
    ){
        fun compare(screenWidth:Dp,screenHeight:Dp):Boolean{
            return if(dimension is Width){
                when(operator){
                    is DimensionOperator.LessThan -> screenWidth < value
                    is DimensionOperator.GreaterThan -> screenWidth > value
                }
            }else{
                when(operator){
                    is DimensionOperator.LessThan -> screenWidth < value
                    is DimensionOperator.GreaterThan -> screenWidth > value
                }
            }
        }
    }
}

@Composable
fun MediaQuery(comparator:Dimensions.DimensionComparator,content :@Composable ()->Unit){
    val screenWidth = LocalContext.current.resources.displayMetrics.widthPixels.dp/ LocalDensity.current.density
    val screenHeight = LocalContext.current.resources.displayMetrics.heightPixels.dp/ LocalDensity.current.density
    if(comparator.compare(screenWidth,screenHeight)){
        content()
    }
}

fun Modifier.mediaQuery(comparator: Dimensions.DimensionComparator,modifier: Modifier):Modifier=composed{
    val screenWidth = LocalContext.current.resources.displayMetrics.widthPixels.dp/ LocalDensity.current.density
    val screenHeight = LocalContext.current.resources.displayMetrics.heightPixels.dp/ LocalDensity.current.density

     if(comparator.compare(screenWidth, screenHeight)){
        this.then(modifier)
    }else this
}

infix fun Dimensions.lessThan(value:Dp):Dimensions.DimensionComparator{
    return Dimensions.DimensionComparator(
        operator = Dimensions.DimensionOperator.LessThan,
        dimension = this,
        value=value
    )
}

infix fun Dimensions.greaterThan(value:Dp):Dimensions.DimensionComparator{
    return Dimensions.DimensionComparator(
        operator = Dimensions.DimensionOperator.GreaterThan,
        dimension = this,
        value=value
    )
}