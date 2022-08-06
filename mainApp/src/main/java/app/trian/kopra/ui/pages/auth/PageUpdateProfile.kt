package app.trian.kopra.ui.pages.auth

import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.util.Log
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import app.trian.kopra.MainViewModel
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage
import com.trian.component.Routes

import com.trian.component.dialog.DialogPickImage
import com.trian.component.dialog.MyDatePicker
import com.trian.component.ui.theme.ColorGray
import com.trian.component.ui.theme.GreenPrimary
import com.trian.component.utils.PermissionUtils
import com.trian.component.utils.formatDayDate
import com.trian.component.utils.getBitmap

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
    var userImageBitmap by remember {
        mutableStateOf<Bitmap?>(null)
    }
    var allowUserToPickImage by remember {
        mutableStateOf(permissionUtils.hasPermission())
    }
    var nameState by mainViewModel.userFullName
    var date by mainViewModel.userBornDate
    var address by mainViewModel.userAddress
    var username by mainViewModel.userUsername
    var profileUrl by mainViewModel.userProfileImageUrl

    var isDialogDatePicker by remember { mutableStateOf(false) }
    var onShowDialogUpdateProfile by remember { mutableStateOf(false)}
    val keyboardController = LocalSoftwareKeyboardController.current

    val permissionContract = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = {
           val haveSomeNotGranted = it.values.contains(false)
            allowUserToPickImage = !haveSomeNotGranted
        }
    )
    val pickImageGallery = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()){
        uri:Uri?->
        uri?.let {
            val bitmap = it.getBitmap(context.contentResolver)
            userImageBitmap = bitmap
            mainViewModel.uploadImageProfile(bitmap!!){
                success, url ->
                if(success) {
                    profileUrl = url
                }
            }
        }
    }

    val pickImageCamera = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview() ){
        bitmap: Bitmap? ->
        bitmap?.let {
            userImageBitmap = it
            mainViewModel.uploadImageProfile(it){
                success, url ->
                if(success) {
                    profileUrl = url
                }
            }
        }

    }

    fun updateProfile(){
        mainViewModel.updateProfile{
            success, message ->
            Log.e("updateProfile",message)
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
            if(allowUserToPickImage) {
                pickImageCamera.launch(null)
            }else{
                permissionContract.launch(
                    permissionUtils.listPermission()
                )
            }

        },
        onGallery = {
            onShowDialogUpdateProfile = false
            if(allowUserToPickImage) {
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
        mainViewModel.getCurrentUser { hasUser, user ->
            if(hasUser){
                nameState = user.fullName
                username = user.username
                address = user.address
                date = user.ttl
                profileUrl = user.profilePicture

            }
        }
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
                    style = MaterialTheme.typography.h1.copy(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.1.sp,))

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
                                .height(100.dp)
                        .align(alignment = Alignment.CenterHorizontally)
                        .clickable {
                            onShowDialogUpdateProfile = true
                        }
                ){
                    Card(
                        shape = CircleShape,
                    ){
                        userImageBitmap?.let {
                            Image(
                                bitmap = it.asImageBitmap(),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = modifier
                                    .padding(10.dp)
                                    .size(10.dp)
                            )
                        }?: CoilImage(
                            modifier = modifier
                                .clip(RoundedCornerShape(12.dp))
                                .padding(10.dp)
                                .size(60.dp),
                            imageModel = profileUrl,
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
                    Icon(
                        Octicons.Pencil24,"",
                        modifier = modifier.align(alignment = Alignment.BottomEnd)
                    )
                }
            Column(
                modifier = modifier.padding(10.dp)
            ){

                //
                Text(
                    text = "Nama",
                    style = MaterialTheme.typography.h1.copy(
                            fontSize = 16.sp,
                            letterSpacing = 0.1.sp,
                            color = ColorGray

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
                        backgroundColor = GreenPrimary.copy(alpha = 0.1f),
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
                    style =  MaterialTheme.typography.h1.copy(
                            fontSize = 16.sp,
                            letterSpacing = 0.1.sp,
                            color = ColorGray

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
                        backgroundColor = GreenPrimary.copy(alpha = 0.1f),
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
                    style = MaterialTheme.typography.h1.copy(
                            fontSize = 16.sp,
                            letterSpacing = 0.1.sp,
                            color = ColorGray

                    )
                )
                Spacer(modifier = modifier.height(5.dp))
                TextField(
                    value = date.formatDayDate(),
                    onValueChange = {

                    },
                    placeholder = {
                        Text(text = date.formatDayDate())
                    },
                    singleLine = true,
                    modifier = modifier
                        .fillMaxWidth()
                        .clickable { isDialogDatePicker = true }
                        .navigationBarsWithImePadding(),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = GreenPrimary.copy(alpha = 0.1f),
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
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = "Alamat",
                    style =  MaterialTheme.typography.h1.copy(
                            fontSize = 16.sp,
                            letterSpacing = 0.1.sp,
                            color = ColorGray

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
                        backgroundColor = GreenPrimary.copy(alpha = 0.1f),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    ),
                    leadingIcon = {
                        Icon(Octicons.Person24,"")
                    }
                )
                Spacer(modifier = modifier.height(20.dp))
                Button(
                    onClick ={
                        keyboardController?.hide()
                        updateProfile()
                    },
                    modifier = modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = GreenPrimary),
                    shape = RoundedCornerShape(10.dp)) {
                    Text(
                        text = "Update",
                        style = MaterialTheme.typography.h1.copy(
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp,
                                letterSpacing = 1.sp,
                                color = Color.White

                        ),
                        modifier = modifier.padding(10.dp)
                    )
                }
            }
        }
    }
}




