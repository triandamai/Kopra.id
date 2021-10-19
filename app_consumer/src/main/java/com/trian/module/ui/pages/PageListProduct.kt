package com.trian.module.ui.pages

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.trian.common.utils.network.DataStatus
import com.trian.component.appbar.AppBarDetail
import com.trian.component.cards.CardProduct
import com.trian.component.ui.theme.TesMultiModuleTheme
import com.trian.data.viewmodel.TelemedicineViewModel



@ExperimentalFoundationApi
@Composable
fun PageListProduct(
    m: Modifier = Modifier,
    telemedicineViewModel: TelemedicineViewModel,
    nav : NavController,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    ){

    val product by telemedicineViewModel.productStatus.observeAsState()
    LaunchedEffect(key1 = scaffoldState) {
        telemedicineViewModel.getProduct()
    }

    Scaffold(
        topBar = {
            AppBarDetail(page = "List Product") {

            }
        }
    ) {
       LazyVerticalGrid(
           modifier = m.padding(16.dp),
           cells = GridCells.Fixed(2)
       ){
           when(product){
               is DataStatus.NoData ->{
                   Log.e("Product","Product Empty")
               }
               is DataStatus.Loading ->{

               }
               is DataStatus.HasData -> {
                   items(count = product?.data!!.size){ index ->
                       CardProduct(
                           product = product?.data!![index],
                           index = 1,
                           onClick = {
                           },
                       )
                   }
               }
           }

       }
    }

}


@ExperimentalFoundationApi
@Preview
@Composable
fun PreviewPageListProduct(){
    TesMultiModuleTheme {
//        PageListProduct()
    }
}
