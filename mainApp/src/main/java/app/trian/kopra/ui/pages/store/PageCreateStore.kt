package app.trian.kopra.ui.pages.store

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.trian.common.utils.route.Routes
import com.trian.common.utils.utils.PermissionUtils
import com.trian.common.utils.utils.getBitmap
import com.trian.component.appbar.AppBarFormStore
import com.trian.component.dialog.DialogPickHaveVehicle
import com.trian.component.dialog.DialogPickImage
import com.trian.component.ui.theme.BluePrimary
import com.trian.component.ui.theme.ColorGray
import com.trian.component.ui.theme.LightBackground
import com.trian.component.utils.mediaquery.Dimensions
import com.trian.component.utils.mediaquery.lessThan
import com.trian.component.utils.mediaquery.mediaQuery
import com.trian.data.viewmodel.MainViewModel
import app.trian.kopra.R
import com.trian.common.utils.utils.LevelUser
import com.trian.common.utils.utils.getType
import compose.icons.Octicons
import compose.icons.octicons.Pencil24
import compose.icons.octicons.Person24
import kotlinx.coroutines.CoroutineScope

@ExperimentalComposeUiApi
@Composable
fun PageCreateToko(
    modifier:Modifier= Modifier,
    scrollState:ScrollState = rememberScrollState(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    permissionUtils:PermissionUtils,
    mainViewModel:MainViewModel,
    navHostController: NavHostController,
    scope:CoroutineScope
){
    val context = LocalContext.current

    var currentUser by mainViewModel.currentUser

    var nameState by mainViewModel.storeName
    var address by mainViewModel.storeAddress
    var deskripsi by mainViewModel.storeDescription
    var noTelepon by mainViewModel.storePhoneNumber
    var haveVehicle by mainViewModel.storeHaveVehicel
    var storeImageUrl by mainViewModel.storeProfileImageUrl
    val keyboardController = LocalSoftwareKeyboardController.current


    var allowUserToPickImage by remember {
        mutableStateOf(permissionUtils.hasPermission())
    }
    var storeImageBitmap by remember {
        mutableStateOf<Bitmap?>(null)
    }
    var dialogPickHaveVehicle by remember{mutableStateOf(false)}
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
            mainViewModel.uploadImageStore(bitmap!!){
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
            mainViewModel.uploadImageStore(it){
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
                    popUpTo(Routes.CREATE_TOKO){
                        inclusive=true
                    }
                }
                //todo success
            }
        }
    }

    LaunchedEffect(key1 = scaffoldState){
        mainViewModel.getDetailMyStore()
        mainViewModel.getCurrentUser {
                hasUser, user ->
            if(hasUser){
                currentUser = user
            }
        }
    }

    DialogPickHaveVehicle(
        show = dialogPickHaveVehicle,
        onPick = {
            haveVehicle = it
            dialogPickHaveVehicle = false
        }
    ) {
        dialogPickHaveVehicle = false
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

    Scaffold(
        topBar = {
            AppBarFormStore("Buat Profile Toko") {
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
                    }?: Image(
                        painter = painterResource(id = R.drawable.sendsucces),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
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
                if(context.getType() == LevelUser.COLLECTOR){
                    Spacer(modifier = modifier.height(5.dp))
                    OutlinedButton(
                        modifier = modifier.fillMaxWidth(),
                        onClick = {
                            dialogPickHaveVehicle = true
                        }) {
                        Text(text = "Kendaraan")
                    }
                }

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
                        Text(text = "0882121xxxxxx")
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