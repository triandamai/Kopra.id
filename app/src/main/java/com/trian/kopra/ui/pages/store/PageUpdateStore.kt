package com.trian.kopra.ui.pages.store

import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage
import com.trian.common.utils.route.Routes
import com.trian.common.utils.utils.PermissionUtils
import com.trian.common.utils.utils.getBitmap
import com.trian.component.appbar.AppBarFormStore
import com.trian.component.dialog.DialogPickImage
import com.trian.component.dialog.DialogPickMap
import com.trian.component.ui.theme.BluePrimary
import com.trian.component.ui.theme.ColorGray
import com.trian.component.ui.theme.LightBackground
import com.trian.component.utils.mediaquery.Dimensions
import com.trian.component.utils.mediaquery.lessThan
import com.trian.component.utils.mediaquery.mediaQuery
import com.trian.data.viewmodel.MainViewModel
import compose.icons.Octicons
import compose.icons.octicons.Pencil24
import compose.icons.octicons.Person24
import kotlinx.coroutines.CoroutineScope

@ExperimentalComposeUiApi
@Composable
fun PageUpdateToko(
    modifier:Modifier= Modifier,
    scrollState:ScrollState = rememberScrollState(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    permissionUtils:PermissionUtils,
    mainViewModel:MainViewModel,
    navHostController: NavHostController,
    scope:CoroutineScope
){

    val context = LocalContext.current
    val myStore by mainViewModel.myStore

    var nameState by mainViewModel.storeName
    var address by mainViewModel.storeAddress
    var deskripsi by mainViewModel.storeDescription
    var noTelepon by mainViewModel.storePhoneNumber
    var location by mainViewModel.storeLocation
    var storeImageUrl by mainViewModel.storeProfileImageUrl
    val keyboardController = LocalSoftwareKeyboardController.current

    var shouldShowPickLocation by remember {
        mutableStateOf(false)
    }



    var allowUserToPickImage by remember {
        mutableStateOf(permissionUtils.hasPermission())
    }
    var storeImageBitmap by remember {
        mutableStateOf<Bitmap?>(null)
    }
    var shouldShowDialogOptionsPickImage by remember { mutableStateOf(false)}

    val permissionContract = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = {
            val haveSomeNotGranted = it.values.contains(false)
            allowUserToPickImage = !haveSomeNotGranted
        }
    )
    val pickImageGallery = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()){
            uri: Uri?->
        uri?.let {
            val bitmap = it.getBitmap(context.contentResolver)
            storeImageBitmap = bitmap
            mainViewModel.uploadImageProfile(bitmap!!){
                    success, url ->
                if(success) {
                    storeImageUrl = url
                }
            }
        }
    }

    val pickImageCamera = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview() ){
            bitmap: Bitmap? ->
        bitmap?.let {
            storeImageBitmap = it
            mainViewModel.uploadImageProfile(it){
                    success, url ->

                if(success) {
                    storeImageUrl = url
                }
            }
        }

    }
    fun processCreateStore(){
        keyboardController?.hide()
        mainViewModel.createNewStore {
            if(it){
                navHostController.navigate(Routes.DETAIL_MY_TOKO){
                    launchSingleTop=true
                    popUpTo(Routes.UPDATE_TOKO){
                        inclusive=true
                    }
                }
                //todo success
            }
        }
    }
    LaunchedEffect(key1 = scaffoldState){
        mainViewModel.getDetailMyStore()
        nameState = myStore.data?.storeName ?: ""
         address = myStore.data?.addressStore ?: ""
         deskripsi = myStore.data?.description ?: ""
         noTelepon = myStore.data?.phoneNumber ?: ""
         storeImageUrl = myStore.data?.logo ?: ""
    }

    DialogPickImage(
        show = shouldShowDialogOptionsPickImage,
        onCancel = {
            shouldShowDialogOptionsPickImage = false
        },
        onCamera = {
            shouldShowDialogOptionsPickImage = false
            if(allowUserToPickImage) {
                pickImageCamera.launch(null)
            }else{
                permissionContract.launch(
                    permissionUtils.listPermission()
                )
            }

        },
        onGallery = {
            shouldShowDialogOptionsPickImage = false
            if(allowUserToPickImage) {
                pickImageGallery.launch("image/*")
            }else{
                permissionContract.launch(
                    permissionUtils.listPermission()
                )
            }
        }
    )
    DialogPickMap(
        show = shouldShowPickLocation,
        location = location,
        onCancel = {},
        onLocation = {
            location = it
        }
    )

    Scaffold(
        topBar = {
            AppBarFormStore("Update Profil Toko Saya") {
                navHostController.popBackStack()
            }
        },
        bottomBar = {}
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ){
            Box(
                modifier = modifier
                            .width(100.dp)
                            .height(100.dp)
                    .clickable {
                        shouldShowDialogOptionsPickImage = true
                    }
                    .align(alignment = Alignment.CenterHorizontally)
            ){
                Card(
                    shape = CircleShape,
                    border = BorderStroke(width = 1.dp,color = LightBackground)
                ){
                    storeImageBitmap?.let {
                        Image(
                           bitmap=it.asImageBitmap(),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                        )
                    }?: CoilImage(
                        modifier = modifier
                            .clip(RoundedCornerShape(12.dp))
                            .size(80.dp),
                        imageModel = myStore.data?.logo ?: "",
                        // Crop, Fit, Inside, FillHeight, FillWidth, None
                        contentScale = ContentScale.Crop,
                        // shows an image with a circular revealed animation.
                        circularReveal = CircularReveal(duration = 250),
                        // shows a placeholder ImageBitmap when loading.
                        placeHolder = ImageBitmap.imageResource(com.trian.component.R.drawable.dummy_profile),
                        // shows an error ImageBitmap when the request failed.
                        error = ImageBitmap.imageResource(com.trian.component.R.drawable.dummy_doctor)
                    )
                }
                Card(
                    shape = CircleShape,
                    border = BorderStroke(width = 0.5.dp,color = Color.Black),
                    modifier = modifier.align(alignment = Alignment.BottomEnd)
                ) {
                    Icon(
                        Octicons.Pencil24,"",
                        modifier = modifier
                            .padding(5.dp)
                            .height(18.dp),
                    )
                }
            }
            Column(
                modifier = modifier.padding(10.dp)
            ){
                Text(
                    text = "Nama toko",
                    style = TextStyle().mediaQuery(
                        Dimensions.Width lessThan 400.dp,
                        value = MaterialTheme.typography.h1.copy(
                            fontSize = 16.sp,
                            letterSpacing = 0.1.sp,
                            color = ColorGray
                        )
                    )
                )
                Spacer(modifier = modifier.height(5.dp))
                TextField(
                    value = nameState,
                    onValueChange = {nameState=it},
                    placeholder = {
                        Text(text = "Nama toko anda...")
                    },
                    singleLine = true,
                    modifier = modifier
                        .fillMaxWidth()
                        .navigationBarsWithImePadding(),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = BluePrimary.copy(alpha = 0.1f),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    ),
                    leadingIcon = {
                        Icon(Octicons.Person24,"")
                    }
                )
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = "Deskripsi toko",
                    style = TextStyle().mediaQuery(
                        Dimensions.Width lessThan 400.dp,
                        value = MaterialTheme.typography.h1.copy(
                            fontSize = 16.sp,
                            letterSpacing = 0.1.sp,
                            color = ColorGray
                        )
                    )
                )
                Spacer(modifier = modifier.height(5.dp))
                TextField(
                    value = deskripsi,
                    onValueChange = {deskripsi=it},
                    placeholder = {
                        Text(text = "Deskripsi toko")
                    },
                    singleLine = false,
                    modifier = modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .navigationBarsWithImePadding(),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = BluePrimary.copy(alpha = 0.1f),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    ),
                    leadingIcon = {
                        Icon(Octicons.Person24,"")
                    }
                )
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = "Alamat toko",
                    style = TextStyle().mediaQuery(
                        Dimensions.Width lessThan 400.dp,
                        value = MaterialTheme.typography.h1.copy(
                            fontSize = 16.sp,
                            letterSpacing = 0.1.sp,
                            color = ColorGray
                        )
                    )
                )
                Spacer(modifier = modifier.height(5.dp))
                TextField(
                    value = address,
                    onValueChange = {address=it},
                    placeholder = {
                        Text(text = "Alamat toko")
                    },
                    singleLine = false,
                    modifier = modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .navigationBarsWithImePadding(),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = BluePrimary.copy(alpha = 0.1f),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    ),
                    leadingIcon = {
                        Icon(Octicons.Person24,"")
                    }
                )
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = "Nomor telepon",
                    style = TextStyle().mediaQuery(
                        Dimensions.Width lessThan 400.dp,
                        value = MaterialTheme.typography.h1.copy(
                            fontSize = 16.sp,
                            letterSpacing = 0.1.sp,
                            color = ColorGray
                        )
                    )
                )
                Spacer(modifier = modifier.height(5.dp))
                TextField(
                    value = noTelepon,
                    onValueChange = {noTelepon=it},
                    placeholder = {
                        Text(text = "088212122121")
                    },
                    singleLine = true,
                    modifier = modifier
                        .fillMaxWidth()
                        .navigationBarsWithImePadding(),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = BluePrimary.copy(alpha = 0.1f),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    ),
                    leadingIcon = {
                        Icon(Octicons.Person24,"")
                    },
                )
                Spacer(modifier = modifier.height(20.dp))
                Button(
                    onClick ={
                        shouldShowPickLocation = true
                    },
                    modifier = modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = BluePrimary),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "Pilih Lokasi",
                        style = TextStyle().mediaQuery(
                            Dimensions.Width lessThan 400.dp,
                            value = MaterialTheme.typography.h1.copy(
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp,
                                letterSpacing = 1.sp,
                                color = Color.White
                            )
                        ),
                        modifier = modifier.padding(10.dp)
                    )
                }
                Spacer(modifier = modifier.height(20.dp))
                Button(
                    onClick ={
                        processCreateStore()
                    },
                    modifier = modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = BluePrimary),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "Konfirmasi",
                        style = TextStyle().mediaQuery(
                            Dimensions.Width lessThan 400.dp,
                            value = MaterialTheme.typography.h1.copy(
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp,
                                letterSpacing = 1.sp,
                                color = Color.White
                            )
                        ),
                        modifier = modifier.padding(10.dp)
                    )
                }
            }
        }
    }
}