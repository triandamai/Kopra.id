package app.trian.kopra.ui.pages.main

import android.location.Location
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.kopra.MainViewModel
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.trian.component.Routes

import com.trian.component.cards.CardStore
import com.trian.component.ui.theme.GreenPrimary
import com.trian.component.utils.capitalizeWords
import com.trian.component.utils.mediaquery.Dimensions
import com.trian.component.utils.mediaquery.lessThan
import com.trian.component.utils.mediaquery.mediaQuery
import com.trian.data.model.Kurs
import com.trian.data.model.network.GetStatus

import compose.icons.Octicons
import compose.icons.octicons.ArrowRight24
import compose.icons.octicons.Search24
import kotlinx.coroutines.CoroutineScope

/**
 * Page Dashboard Main
 * Author Trian damai
 * Created by Trian Damai
 * 25/10/2021
 */

@Composable
fun PageMain(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    mainViewModel: MainViewModel,
    navHostController: NavHostController,
    scope: CoroutineScope
){
    var currentUser by mainViewModel.currentUser
    val listStore by mainViewModel.recomendationListStore
    val kurs by mainViewModel.kurs.observeAsState(initial = Kurs())
    val currentLocation by mainViewModel.getLocation().observeAsState()
    var searchText by remember { mutableStateOf("")}

    val locationA = Location("my location")
    locationA.latitude = currentLocation?.latitude ?: 0.0
    locationA.longitude = currentLocation?.longitude ?: 0.0

    val sorted = listStore.data?.map {

        val locationB = Location("store location")
        locationB.latitude = it.latitude
        locationB.longitude = it.longitude

        val range = locationA.distanceTo(locationB)
        it.apply {
            distance = range
        }
    }?.sortedBy {
        it.distance
    }?: listOf()

    LaunchedEffect(key1 =scaffoldState){
        mainViewModel.getCurrentUser(){
                success,users->
            if(success){
                currentUser = users
            }
        }
        mainViewModel.getKursToday {  }
        mainViewModel.getListRecomendationStore()
    }
    Column(
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        LazyColumn(content = {
            item{
                Text(
                    text="Hello, ${currentUser?.fullName?.capitalizeWords()} \uD83D\uDC4B",
                    style =  MaterialTheme.typography.h1.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = modifier.height(20.dp))
            }
            item{
                TextField(
                    value = searchText,
                    onValueChange = {searchText=it},
                    placeholder = {
                        Text(text = "Cari kebutuhanmu disini...")
                    },
                    singleLine = true,
                    modifier = modifier
                        .navigationBarsWithImePadding()
                        .fillMaxWidth()
                        .clickable { navHostController.navigate(Routes.SEARCH_STORE) },
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    ),
                    trailingIcon = {
                        Icon(
                            Octicons.Search24,"",
                            tint = Color.White
                        )
                    },
                    readOnly = true,
                    enabled = false,
                )
                Spacer(modifier = modifier.height(30.dp))
            }
            item{
                Card(
                    shape = RoundedCornerShape(10.dp),
                    modifier = modifier.fillMaxWidth(),
                    elevation = 0.1.dp,
                    backgroundColor = GreenPrimary
                ){
                    Column(modifier = modifier.padding(15.dp)) {
                        Text("Kurs dollar",
                            style =  MaterialTheme.typography.h1.copy(
                                color=Color.White,
                                fontSize = 14.sp,
                            )
                        )
                        Text(
                            "Rp ${kurs.IDR ?: "0"}",
                            style =  MaterialTheme.typography.h1.copy(
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp

                            )
                        )
                        Spacer(modifier = modifier.height(10.dp))
                        Divider(
                            color = Color.White
                        )
                        Spacer(modifier = modifier.height(10.dp))
                        Text("Hari ini : $${kurs.USD}",
                            style = TextStyle().mediaQuery(Dimensions.Width lessThan 400.dp,
                                value = MaterialTheme.typography.h1.copy(
                                    color = Color.White,
                                    fontSize = 12.sp
                                )
                            )
                        )
                    }
                }
                Spacer(modifier = modifier.height(30.dp))
            }
            item {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text="Rekomendasi",
                        style = TextStyle().mediaQuery(
                            Dimensions.Width lessThan 400.dp,
                            value = MaterialTheme.typography.h1.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        ),
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier.clickable {
                            navHostController.navigate(Routes.LIST_STORE)
                        }
                    ){
                        Text(
                            text="Detail",
                            style = TextStyle().mediaQuery(
                                Dimensions.Width lessThan 400.dp,
                                value = MaterialTheme.typography.h1.copy(
                                    fontSize = 14.sp
                                )
                            ),
                        )
                        Spacer(modifier = modifier.width(8.dp))
                        Icon(Octicons.ArrowRight24,"",)
                    }
                }
                Spacer(modifier = modifier.height(10.dp))
            }
            when(listStore){
                is GetStatus.HasData -> {
                    items(
                        count = sorted.size,
                        itemContent = {index->
                            CardStore(index = 0,store = sorted[index],onDetail = {
                                    index, store ->
                                navHostController.navigate("${Routes.DETAIL_TOKO}/${store.uid}")
                            },onEdit = {
                                    index, store ->

                            },onDelete = {
                                    index, store ->
                            })
                        }
                    )
                }
                is GetStatus.Idle -> {}
                is GetStatus.Loading -> {}
                is GetStatus.NoData -> {}
            }
            item {
                Spacer(modifier = modifier.height(80.dp))
            }
        })
    }
}

@Preview
@Composable
fun PreviewPageMain(){
    PageMain(
        mainViewModel = viewModel(),
        navHostController = rememberNavController(),
        scope = rememberCoroutineScope() )
}