package app.trian.kopra.ui.pages.store

import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import app.trian.kopra.MainViewModel
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage
import com.trian.component.R
import com.trian.component.appbar.AppBarFormStore
import com.trian.component.dialog.DialogPickImage
import com.trian.component.ui.theme.ColorGray
import com.trian.component.ui.theme.GreenPrimary
import com.trian.component.utils.PermissionUtils
import com.trian.component.utils.getBitmap
import com.trian.component.utils.mediaquery.Dimensions
import com.trian.component.utils.mediaquery.lessThan
import com.trian.component.utils.mediaquery.mediaQuery
import compose.icons.Octicons
import compose.icons.octicons.*
import kotlinx.coroutines.CoroutineScope

@Composable
fun PageUpdateProduct(
    modifier:Modifier=Modifier,
    scaffoldState: ScaffoldState= rememberScaffoldState(),
    scope: CoroutineScope = rememberCoroutineScope(),
    scrollState: ScrollState = rememberScrollState(),
    permissionUtils: PermissionUtils,
    mainViewModel: MainViewModel,
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
    var detailProduct by mainViewModel.detailProduct

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

    val productId = navHostController.currentBackStackEntry?.arguments?.getString("slug") ?:"no"


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
            mainViewModel.updateImageProduct(detailProduct.data?.uid ?:"",bitmap!!){
                    success, url ->
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
            mainViewModel.updateImageProduct(detailProduct.data?.uid ?:"",it){
                    success, url ->

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
        mainViewModel.updateProduct(productId) {
            navHostController.popBackStack()
        }
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

    SideEffect {
        nameState  = detailProduct.data?.productName ?: ""
        descState = detailProduct.data?.description ?: ""
        priceState =detailProduct.data?.price ?: 0
        imageUrl =detailProduct.data?.thumbnail ?: ""


    }
     LaunchedEffect(key1 = scaffoldState){
        mainViewModel.getDetailProduct(productId)

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
                        Image(
                            bitmap = it.asImageBitmap(),
                            contentDescription = ""
                        )
                    }?:
                    CoilImage(
                        modifier = modifier
                            .clip(RoundedCornerShape(12.dp))
                            .height(50.dp)
                            .width(50.dp),
                        imageModel = imageUrl,
                        // Crop, Fit, Inside, FillHeight, FillWidth, None
                        contentScale = ContentScale.Crop,
                        // shows an image with a circular revealed animation.
                        circularReveal = CircularReveal(duration = 250),
                        // shows a placeholder ImageBitmap when loading.
                        placeHolder = ImageBitmap.imageResource(R.drawable.dummy_profile),
                        // shows an error ImageBitmap when the request failed.
                        error = ImageBitmap.imageResource(R.drawable.dummy_doctor)
                    )

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
            Text("Harga produk")
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
                    text = "Update",
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