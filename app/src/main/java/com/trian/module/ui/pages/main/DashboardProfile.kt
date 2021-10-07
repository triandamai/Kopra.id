package com.trian.module.ui.pages.main

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.trian.component.R
import com.trian.component.bottomsheet.*
import com.trian.component.cards.CardAppVersion
import com.trian.component.dialog.DialogChangeEmail
import com.trian.component.dialog.DialogChangeName
import com.trian.component.dialog.DialogChangePhoneNumber
import com.trian.component.dialog.DialogConfirmationEmailSuccess
import com.trian.component.ui.theme.BluePrimary
import com.trian.component.ui.theme.ColorFontFeatures
import com.trian.component.ui.theme.LightBackground
import com.trian.component.ui.theme.LightBackgroundAccent
import com.trian.component.utils.coloredShadow
import com.trian.data.viewmodel.MainViewModel
import compose.icons.Octicons
import compose.icons.octicons.ArrowRight16
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Dashboard Profile
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 11/09/2021
 */

@Composable
fun PageProfile(
    modifier: Modifier = Modifier,
    listState: LazyListState,
    nav:NavHostController,
    viewModel: MainViewModel,
    scope:CoroutineScope,
    openCamera: () -> Unit,
    openGallery:() -> Unit,
    restartActivity:()->Unit
){
    var dialogChangeProfil by remember { mutableStateOf(false) }
    var dialogChangeEmail by remember { mutableStateOf(false) }
    var dialogChangePhoneNumber by remember { mutableStateOf(false) }
    var dialogChangeName by remember { mutableStateOf(false) }
    var dialogSuccessChangeEmail by remember { mutableStateOf(false) }
    var imageUrl by remember { mutableStateOf<Uri?>(null) }

    val bitmap = remember { mutableStateOf<Bitmap?>(null) }

    val launcherGallery = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUrl = uri
    }
    val context = LocalContext.current

    val user by viewModel.user

    var phone by remember{ mutableStateOf("no number")}
    var name by remember{ mutableStateOf("no number")}
    var email by remember{ mutableStateOf("no number")}
    var username by remember{ mutableStateOf("no number")}
    var hasProfile by remember{ mutableStateOf(false)}
    var dialogLogout by remember{mutableStateOf(false)}

    user?.let {
        if(it.phone_number != ""||it.phone_number != "kosong" ){
            phone = "no phone number"
        }else{
            phone = it.phone_number
        }

        name = it.name
        email = it.email
        username = it.username
        if(it.thumb == "kosong" || it.thumb == ""){
            hasProfile
        }
    }

    fun processSignOut(){

            viewModel.signOut(){
                dialogLogout=false
                restartActivity()
            }
    }
    if(dialogLogout){
        Dialog(onDismissRequest = { }) {
            Surface(
                modifier = modifier
                    .padding(5.dp),
                shape = RoundedCornerShape(5.dp),
                color = Color.White
            ) {
                Column(
                    modifier= modifier
                        .fillMaxWidth()
                        .background(Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Sign Out from app?",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp, horizontal = 16.dp),
                        textAlign = TextAlign.Left

                    )
                
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp, horizontal = 16.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Button(
                            onClick = {
                               processSignOut()
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = BluePrimary,
                                contentColor = Color.White
                            ),
                            modifier = modifier.width(84.dp)
                        ) {
                            Text(
                                text = "SignOut",
                            )
                        }
                        Spacer(modifier = modifier.width(8.dp))
                        Button(
                            onClick = {
                                dialogLogout = false
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.White,
                                contentColor = BluePrimary
                            ),
                            modifier = modifier.width(84.dp)
                        ) {
                            Text(
                                text = "Cancel",
                            )
                        }

                    }

                }

            }
        }

    }


    DialogConfirmationEmailSuccess(show = dialogSuccessChangeEmail) {
        dialogSuccessChangeEmail=false
    }
    DialogChangeEmail(
        show=dialogChangeEmail,
        onConfirm = {
                    dialogSuccessChangeEmail = true
        },onCancel = {
            dialogChangeEmail = false
        }
    )

    DialogChangeName(
        show=dialogChangeName,
        onConfirm = {

        },
        onCancel = {
            dialogChangeName = false
        }
    )
    DialogChangePhoneNumber(
        show=dialogChangePhoneNumber,
        onConfirm = {

        },
        onCancel={
            dialogChangePhoneNumber = false
        }
    )
    DialogChangeProfileImage(
        show = dialogChangeProfil,
        openCamera = openCamera,
        openGallery = {
            launcherGallery.launch("image/*")
        },
        onCancel = {
                   dialogChangeProfil = false
        },
        onConfirm = {}
    )


    Scaffold(
        backgroundColor= LightBackground,
        topBar = {},
    ) {

        LazyColumn(
            state=listState,
            modifier=modifier
                .padding(horizontal = 16.dp),
            content = {
                item{
                    Row(
                        modifier = modifier
                            .padding(top = 20.dp,bottom = 20.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if(imageUrl == null){
                            Image(
                                painter = painterResource(id = R.drawable.dummy_profile),
                                contentDescription = "Profile Picture",
                                contentScale = ContentScale.FillWidth,
                                modifier = modifier
                                    .clip(CircleShape)
                                    .height(80.dp)
                                    .width(80.dp)
                                    .clickable(
                                        onClick = { dialogChangeProfil=true }
                                    )

                            )
                        }else{
                            imageUrl?.let {
                                if (Build.VERSION.SDK_INT < 28) {
                                    bitmap.value = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
                                } else {
                                    val source = ImageDecoder.createSource(context.contentResolver, it)
                                    bitmap.value = ImageDecoder.decodeBitmap(source)
                                }

                                bitmap.value?.let { bitmap ->
                                    Image(
                                        bitmap = bitmap.asImageBitmap(),
                                        contentDescription = "Gallery Image",
                                        contentScale = ContentScale.FillWidth,
                                        modifier = modifier
                                            .clip(CircleShape)
                                            .height(80.dp)
                                            .width(80.dp)
                                            .clickable(
                                                onClick = { dialogChangeProfil = true }
                                            )
                                    )
                                }
                            }
                        }

                        Spacer(modifier = modifier.width(16.dp))
                        Column {
                            Text(
                                text = name,
                                style = TextStyle(fontSize = 20.sp,fontWeight = FontWeight.Bold)
                            )
                            Text(text = username)
                        }

                    }
                }
                item {
                    Column(
                        modifier= modifier
                            .fillMaxWidth()
                            .background(Color.Transparent)
                            .clip(RoundedCornerShape(10.dp))

                    ) {
                       Column(
                           modifier
                               .fillMaxWidth()
                               .coloredShadow(
                                   color = ColorFontFeatures
                               )
                               .clip(RoundedCornerShape(10.dp))
                               .background(Color.White)) {
                           Row(modifier = modifier
                               .padding(
                                   start = 16.dp,
                                   end = 16.dp,
                                   top = 20.dp,
                                   bottom = 8.dp
                               )
                               .fillMaxWidth(),
                               verticalAlignment = Alignment.CenterVertically,
                               horizontalArrangement = Arrangement.SpaceBetween
                           ) {
                               Column {
                                   Text(
                                       text = "Display Name",
                                       color = Color.Gray
                                   )
                                   Text(
                                       text = name,
                                       style = TextStyle(
                                           fontSize = 18.sp,
                                           fontWeight = FontWeight.Bold
                                       )
                                   )
                               }
                               Button(onClick = { dialogChangeName = true },modifier = Modifier.width(90.dp)) {
                                   Text(text = "Edit")
                               }
                           }
                           Row(modifier = modifier
                               .padding(
                                   start = 16.dp,
                                   end = 16.dp,
                                   top = 16.dp,
                                   bottom = 8.dp
                               )
                               .fillMaxWidth(),
                               verticalAlignment = Alignment.CenterVertically,
                               horizontalArrangement = Arrangement.SpaceBetween
                           ) {
                               Column {
                                   Text(
                                       text = "Email",
                                       color = Color.Gray
                                   )
                                   Text(
                                       text = email,
                                       style = TextStyle(
                                           fontSize = 18.sp,
                                           fontWeight = FontWeight.Bold
                                       ))
                               }
                               Button(onClick = { dialogChangeEmail = true},modifier = Modifier.width(90.dp)) {
                                   Text(text = "Edit")
                               }
                           }
                           Row(modifier = modifier
                               .padding(
                                   start = 16.dp,
                                   end = 16.dp,
                                   top = 16.dp,
                                   bottom = 20.dp
                               )
                               .fillMaxWidth(),
                               verticalAlignment = Alignment.CenterVertically,
                               horizontalArrangement = Arrangement.SpaceBetween
                           ) {
                               Column {
                                   Text(
                                       text = "Phone",
                                       color = Color.Gray
                                   )
                                   Text(
                                       text = phone,
                                       style = TextStyle(
                                           fontSize = 18.sp,
                                           fontWeight = FontWeight.Bold
                                       ))
                               }
                               Button(onClick = { dialogChangePhoneNumber = true },modifier = Modifier.width(90.dp)) {
                                   Text(text = "Change")
                               }
                           }
                       }
                    }
                }
                item {
                    Column(
                        modifier= modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                            .background(Color.Transparent)
                            .clip(RoundedCornerShape(10.dp))

                    ) {
                        Column(
                            modifier
                                .fillMaxWidth()
                                .coloredShadow(
                                    color = ColorFontFeatures
                                )
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color.White)) {
                            Row(modifier = modifier
                                .padding(
                                    start = 16.dp,
                                    end = 16.dp,
                                    top = 20.dp,
                                    bottom = 8.dp
                                )
                                .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {

                                    Text(
                                        text = "Device Setting",
                                        style = TextStyle(
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                            }
                            Row(modifier = modifier
                                .padding(
                                    start = 16.dp,
                                    end = 16.dp,
                                    top = 16.dp,
                                    bottom = 8.dp
                                )
                                .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {

                                    Text(
                                        text = "About App",
                                        style = TextStyle(
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold
                                        ))
                                }

                            }
                            Row(modifier = modifier
                                .padding(
                                    start = 16.dp,
                                    end = 16.dp,
                                    top = 16.dp,
                                    bottom = 20.dp
                                )
                                .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {

                                    Text(
                                        text = "+FAQ",
                                        style = TextStyle(
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold
                                        ))
                                }

                            }
                        }
                    }
                }
                item {
                    Column(
                        modifier= modifier
                            .padding(vertical = 16.dp)
                            .fillMaxWidth()
                            .background(Color.Transparent)
                            .clip(RoundedCornerShape(10.dp))
                    ) {

                        Column(modifier = modifier
                            .fillMaxWidth()
                            .coloredShadow(
                                color = ColorFontFeatures
                            )
                            .background(Color.White),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Column(modifier=modifier
                                .padding(horizontal = 16.dp, vertical = 16.dp)) {
                                Text(text = "History")
                                Text(text = "Teleconsultation")
                            }
                            Spacer(modifier = modifier.height(10.dp))
                            Column(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .background(LightBackgroundAccent)
                            ) {
                                Spacer(modifier = modifier.height(16.dp))
                                Row(
                                    modifier = modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Text(text = "See all history",
                                    color = Color.Gray)
                                    Spacer(modifier = modifier
                                        .width(16.dp))
                                    Icon(
                                        imageVector = Octicons.ArrowRight16,
                                        tint= Color.Gray,
                                        contentDescription = "See all")

                                }
                                Spacer(modifier = modifier.height(16.dp))
                            }
                        }

                    }
                }
                item {
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        TextButton(onClick = {
                            dialogLogout = true
                        }) {
                            Text(
                                text = "Sign Out",
                                style=TextStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold
                                ),
                                color = Color.Red
                            )
                        }
                    }
                }
                item {
                    CardAppVersion()
                }
                item {
                    Spacer(modifier =modifier.height(80.dp))
                }
        })
    }
}

@Preview
@Composable
fun PreviewPageProfile(){
//    PageProfile(listState = rememberLazyListState())
}