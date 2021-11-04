package com.trian.kopra.ui.pages.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.trian.common.utils.utils.CollectionUtils
import com.trian.common.utils.utils.capitalizeWords
import com.trian.component.cards.CardStore
import com.trian.component.cards.HeaderDashboard
import com.trian.component.ui.theme.BluePrimary
import com.trian.component.ui.theme.GreenPrimary
import com.trian.component.ui.theme.LightBackground
import com.trian.component.utils.mediaquery.Dimensions
import com.trian.component.utils.mediaquery.lessThan
import com.trian.component.utils.mediaquery.mediaQuery
import com.trian.data.viewmodel.MainViewModel
import com.trian.domain.models.LevelUser
import com.trian.domain.models.Store
import com.trian.domain.models.User
import com.trian.kopra.R
import compose.icons.Octicons
import compose.icons.octicons.DeviceMobile24
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
    var scrollState = rememberScrollState()
    var searchText by remember { mutableStateOf("")}
    val user = User(
        uid = "sssss",
        phoneNumber = "088212381375",
        fullName = "nurkholid",
        username="kholid",
        address = "jl jakarta",
        ttl="000121",
        latitude = 0,
        longitude =0,
        profilePicture = "",
        levelUser = LevelUser.FARMER,
        createdAt = 0,
        updatedAt = 0
    )
    val listProduk = listOf<Store>(Store(
        uid ="", tenantUid = "", storeName = "Toko jaya abadi",
        description = "Tes", addressStore = "Jl jauh", phoneNumber = "088212381375",
        logo = "", banner = "",
        latitude = CollectionUtils.DEFAULT_NULL.toLong(),
        longitude = CollectionUtils.DEFAULT_NULL.toLong(),
        createdAt = CollectionUtils.DEFAULT_NULL.toLong(),
        updatedAt = CollectionUtils.DEFAULT_NULL.toLong()),
        Store(
            uid ="", tenantUid = "", storeName = "Toko jaya abadi", description = "Tes",
            addressStore = "Jl jauh", phoneNumber = "088212381375", logo = "", banner = "",
            latitude = CollectionUtils.DEFAULT_NULL.toLong(),
            longitude = CollectionUtils.DEFAULT_NULL.toLong(),
            createdAt = CollectionUtils.DEFAULT_NULL.toLong(),
            updatedAt = CollectionUtils.DEFAULT_NULL.toLong())
    )

    LaunchedEffect(key1 =scaffoldState){
        mainViewModel.getCurrentUser(){
                success,users->
            if(success){
                currentUser = user
            }

        }
        currentUser = user
    }
    Column(
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            text="Hello, ${user.fullName.capitalizeWords()} \uD83D\uDC4B",
            style = TextStyle().mediaQuery(Dimensions.Width lessThan 400.dp,
                value = MaterialTheme.typography.h1.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ))
        )
        Spacer(modifier = modifier.height(20.dp))
        TextField(
            value = searchText,
            onValueChange = {searchText=it},
            placeholder = {
                Text(text = "Cari kebutuhanmu disini...")
            },
            singleLine = true,
            modifier = modifier
                .navigationBarsWithImePadding()
                .fillMaxWidth(),
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
            }
        )
        Spacer(modifier = modifier.height(20.dp))
        Text(text="Kurs dollar")
        Spacer(modifier = modifier.height(10.dp))
        Text(text="empty")
        Spacer(modifier = modifier.height(20.dp))
        Text(text="Rekomendasi")
        Spacer(modifier = modifier.height(10.dp))
        LazyColumn(content = {
            items(
                count = listProduk.size,
                itemContent = {index->
                    CardStore(
                        storeName = listProduk[index].storeName.capitalizeWords(),
                        address = listProduk[index].addressStore,
                        price = "Rp 10.000/Kg"
                    )
                }
            )
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