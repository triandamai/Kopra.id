package app.trian.kopra.ui.pages.store

import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.trian.common.utils.utils.LevelUser
import com.trian.common.utils.utils.PermissionUtils
import com.trian.common.utils.utils.getBitmap
import com.trian.common.utils.utils.getType
import com.trian.component.appbar.AppBarFormStore
import com.trian.component.dialog.DialogPickImage
import com.trian.component.ui.theme.ColorGray
import com.trian.component.ui.theme.GreenPrimary
import com.trian.component.utils.mediaquery.Dimensions
import com.trian.component.utils.mediaquery.lessThan
import com.trian.component.utils.mediaquery.mediaQuery
import com.trian.data.viewmodel.MainViewModel

import compose.icons.Octicons
import compose.icons.octicons.*
import kotlinx.coroutines.CoroutineScope

@Composable
fun PageCreateProduct(
    modifier:Modifier=Modifier,
    scaffoldState: ScaffoldState= rememberScaffoldState(),
    scrollState:ScrollState = rememberScrollState(),
    scope:CoroutineScope = rememberCoroutineScope(),
    permissionUtils:PermissionUtils,
    mainViewModel:MainViewModel,
    navHostController: NavHostController

){

    val ctx = LocalContext.current
    val currentWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp/
            LocalDensity.current.density

    var nameState by mainViewModel.productFullName
    var descState by mainViewModel.productDescription
    var priceState by mainViewModel.productPrice
    var imageUrl by mainViewModel.productImageUrl
    var currentUser by mainViewModel.currentUser
    var idProduct by mainViewModel.productId

    val stroke = Stroke(width = 2f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f),
    )

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
            val bitmap = it.getBitmap(ctx.contentResolver)
            storeImageBitmap = bitmap
            mainViewModel.uploadImageProduct(bitmap!!){
                    success, url ,id->
                if(success) {
                    imageUrl = url
                }
            }
        }
    }

    val pickImageCamera = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview() ){
            bitmap: Bitmap? ->
        bitmap?.let {
            storeImageBitmap = it
            mainViewModel.uploadImageProduct(it){
                    success, url,id ->
                idProduct = id
                if(success) {
                    imageUrl = url
                }
            }
        }

    }

    fun startRequestPermission(){
        permissionContract.launch(
            permissionUtils.listPermission()
        )
    }
    fun  processCreateProduct(){
        mainViewModel.createNewProduct {
            navHostController.popBackStack()
        }
    }

    fun generateId(){
        mainViewModel
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

    LaunchedEffect(key1 = scaffoldState){
        mainViewModel.getCurrentUser { hasUser, user ->
            if(hasUser){
                currentUser = user
            }
        }
        generateId()
        if(!permissionUtils.hasPermission()){
            permissionContract.launch(
                permissionUtils.listPermission()
            )
        }else{
            allowUserToPickImage = true
        }

    }

    Scaffold(
        topBar = {
           AppBarFormStore(
               title = "Tambah Produk",
               backgroundColor = Color.White,
               elevation = 0.dp
           ) {
                navHostController.popBackStack()
           }
        },
        bottomBar = {}
    ) {
        Column(
            modifier = modifier
                .verticalScroll(scrollState)
                .fillMaxWidth()
                .padding(10.dp),
        ) {
            Text("Foto produk")
            Spacer(modifier = modifier.height(10.dp))
            Box(
                modifier
                    .fillMaxWidth()
                    .mediaQuery(
                        Dimensions.Width lessThan 400.dp,
                        modifier = modifier.height(100.dp)
                    )
                    .clickable {
                        if (allowUserToPickImage) {
                            shouldShowDialogOptionsPickImage = true
                        } else {
                            startRequestPermission()
                        }
                    },
                contentAlignment = Alignment.Center
            ){
                Canvas(modifier = modifier.fillMaxSize()) {
                    drawRoundRect(color = ColorGray,style = stroke,cornerRadius = CornerRadius(10.0F,10.0F))
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    storeImageBitmap?.let {
                        Image(bitmap = it.asImageBitmap(), contentDescription = "")
                    }?:Icon(Octicons.Archive24,"")
                    Spacer(modifier = modifier.height(10.dp))
                    Text(
                        text = "Klik disini untuk upload foto")
                }
            }
            Spacer(modifier = modifier.height(20.dp))
            Text("Nama Produk")
            Spacer(modifier = modifier.height(10.dp))
            OutlinedTextField(
                value = nameState,
                onValueChange = {nameState=it},
                placeholder = { Text(text = "nama produk anda...") },
                singleLine = true,
                modifier = modifier
                    .fillMaxWidth()
                    .navigationBarsWithImePadding(),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = ColorGray,
                    focusedIndicatorColor = GreenPrimary,
                    unfocusedIndicatorColor = ColorGray,
                    disabledIndicatorColor = Color.Transparent,
                ),
                leadingIcon = {
                    Icon(Octicons.Person24,"")
                },
            )
            Spacer(modifier = modifier.height(20.dp))
            Text("Deskripsi")
            Spacer(modifier = modifier.height(10.dp))
            OutlinedTextField(
                value = descState,
                onValueChange = {descState=it},
                placeholder = { Text(text = "Deskripsi produk anda...") },
                singleLine = false,
                modifier = modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .navigationBarsWithImePadding(),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = ColorGray,
                    focusedIndicatorColor = GreenPrimary,
                    unfocusedIndicatorColor = ColorGray,
                    disabledIndicatorColor = Color.Transparent,
                ),
                leadingIcon = {
                    Icon(Octicons.Note24,"")
                },
            )
            Spacer(modifier = modifier.height(20.dp))
            Text(
                when(ctx.getType()){
                    LevelUser.TENANT -> "Harga produk/Hari"
                    LevelUser.COLLECTOR -> "Harga produk/Kg"
                    LevelUser.FARMER -> ""
                    LevelUser.UNKNOWN -> ""
                    else->""
                }
            )
            Spacer(modifier = modifier.height(10.dp))

            OutlinedTextField(
                value = priceState.toString(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = {
                    try {
                        priceState=it.toInt()
                    }catch (e:Exception){

                    }

                },
                placeholder = {
                    Text(text = "Harga produk...")
                },
                singleLine = true,
                modifier = modifier
                    .fillMaxWidth()
                    .navigationBarsWithImePadding(),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = ColorGray,
                    focusedIndicatorColor = GreenPrimary,
                    unfocusedIndicatorColor = ColorGray,
                    disabledIndicatorColor = Color.Transparent,
                ),
                leadingIcon = {
                    Text("Rp")
                },
            )
            Spacer(modifier = modifier.height(20.dp))
            Button(
                onClick ={
                         processCreateProduct()
                },
                modifier = modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(backgroundColor = GreenPrimary),
                shape = RoundedCornerShape(10.dp)) {
                Text(
                    text = "Tambah",
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