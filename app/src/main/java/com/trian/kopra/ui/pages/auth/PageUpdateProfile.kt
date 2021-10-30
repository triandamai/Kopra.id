package com.trian.kopra.ui.pages

import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.trian.component.dialog.DialogPickImage
import com.trian.component.dialog.MyDatePicker
import com.trian.component.ui.theme.BluePrimary
import com.trian.component.ui.theme.ColorGray
import com.trian.component.utils.mediaquery.Dimensions
import com.trian.component.utils.mediaquery.lessThan
import com.trian.component.utils.mediaquery.mediaQuery
import com.trian.data.viewmodel.MainViewModel
import com.trian.domain.models.User
import com.trian.kopra.R
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24
import compose.icons.octicons.Pencil24
import compose.icons.octicons.Person24
import kotlinx.coroutines.CoroutineScope

@ExperimentalComposeUiApi
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PageUpdateProfile(
    modifier:Modifier = Modifier,
    scaffoldState: ScaffoldState= rememberScaffoldState(),
    permissionUtils: PermissionUtils,
    nav:NavHostController,
    mainViewModel: MainViewModel,
    scope:CoroutineScope
){
    var scrollState = rememberScrollState()
    var context = LocalContext.current
    var imageUrl by remember {
        mutableStateOf<Bitmap?>(null)
    }
    var allowPickImage by remember {
        mutableStateOf(permissionUtils.hasPermission())
    }
    var nameState by mainViewModel.nameUser
    var date by mainViewModel.dateUser
    var address by mainViewModel.addressUser
    var username by mainViewModel.usernameUser

    var isDialogDatePicker by remember { mutableStateOf(false) }
    var onShowDialogUpdateProfile by remember { mutableStateOf(false)}
    val keyboardController = LocalSoftwareKeyboardController.current

    val permissionContract = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = {
           val haveSomeNotGranted = it.values.contains(false)
            allowPickImage = !haveSomeNotGranted
        }
    )
    val pickImageGallery = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()){
        uri:Uri?->
        uri?.let {
            val bitmap = it.getBitmap(context.contentResolver)
            imageUrl = bitmap
            mainViewModel.uploadImage(bitmap!!){
                success, url ->

            }
        }
    }

    val pickImageCamera = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview() ){
        bitmap: Bitmap? ->
        bitmap?.let {
            imageUrl = it
            mainViewModel.uploadImage(it){
                success, url ->

            }
        }

    }

    fun updateProfile(){
        mainViewModel.updateProfile{
            success, message ->
            if(success){
                nav.navigate(Routes.DASHBOARD)
            }
        }
    }

    DialogPickImage(
        show = onShowDialogUpdateProfile,
        onCancel = {
            onShowDialogUpdateProfile = false
        },
        onCamera = {
            onShowDialogUpdateProfile = false
            if(allowPickImage) {
                pickImageCamera.launch(null)
            }else{
                permissionContract.launch(
                    permissionUtils.listPermission()
                )
            }

        },
        onGallery = {
            onShowDialogUpdateProfile = false
            if(allowPickImage) {
                pickImageGallery.launch("image/*")
            }else{
                permissionContract.launch(
                    permissionUtils.listPermission()
                )
            }
        }
    )
    MyDatePicker(
        isDialogDatePicker = isDialogDatePicker,
        onCancel = {
            isDialogDatePicker = false
        }
    ){
        dates->date=dates
        isDialogDatePicker = false
    }

    LaunchedEffect(key1 = scaffoldState){
        if(!permissionUtils.hasPermission()){
            permissionContract.launch(
                permissionUtils.listPermission()
            )
        }else{
            allowPickImage = true
        }
    }
    Scaffold(
        topBar = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(20.dp),
            ){
                Icon(
                    Octicons.ArrowLeft24,"",
                )
                Text(
                    text="Edit Profile",
                    style = TextStyle().mediaQuery(
                        Dimensions.Width lessThan 400.dp,
                        value= MaterialTheme.typography.h1.copy(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.1.sp,))
                )
                Box{}
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
                        .mediaQuery(
                            Dimensions.Width lessThan 400.dp,
                            modifier = modifier
                                .width(100.dp)
                                .height(100.dp)
                        )
                        .clickable {
                            onShowDialogUpdateProfile = true
                        }
                ){
                    Card(
                        shape = CircleShape,
                    ){
                        imageUrl?.let {
                            Image(
                                bitmap = it.asImageBitmap(),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = modifier.padding(10.dp)
                            )
                        }?:Image(
                            painter = painterResource(id = R.drawable.sendsucces),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = modifier.padding(10.dp)
                        )
                    }
                    Icon(Octicons.Pencil24,"")
                }
            Column(
                modifier = modifier.padding(10.dp)
            ){
                Text(
                    text = "Nama",
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
                        Text(text = "Nama anda...")
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
                    text = "Username",
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
                    value = username,
                    onValueChange = {username=it},
                    placeholder = {
                        Text(text = "Username")
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
                    text = "Alamat",
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
                        Text(text = "Alamat anda")
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
                    text = "Tanggal Lahir",
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
                    value = date,
                    onValueChange = {date=it},
                    placeholder = {
                        Text(text = date)
                    },
                    singleLine = true,
                    modifier = modifier
                        .fillMaxWidth()
                        .clickable { isDialogDatePicker = true }
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
                    readOnly = true,
                    enabled = false,
                )
                Spacer(modifier = modifier.height(20.dp))
                Button(
                    onClick ={
                        keyboardController?.hide()
                        updateProfile()
                    },
                    modifier = modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = BluePrimary),
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
}


