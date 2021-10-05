package com.trian.module

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.core.app.ActivityCompat
import androidx.navigation.NavHostController
import androidx.navigation.plusAssign
import com.google.accompanist.insets.ExperimentalAnimatedInsets
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.navigation
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.trian.common.utils.route.Routes
import com.trian.common.utils.utils.PermissionUtils
import com.trian.component.bottomsheet.*
import com.trian.module.ui.pages.*
import com.trian.component.ui.theme.TesMultiModuleTheme
import com.trian.data.local.Persistence
import com.trian.data.viewmodel.MainViewModel
import com.trian.domain.models.Hospital
import com.trian.domain.models.ServiceType
import com.trian.module.ui.pages.auth.PageLogin
import com.trian.module.ui.pages.auth.PageOnBoarding
import com.trian.module.ui.pages.auth.PageRegister
import com.trian.module.ui.pages.auth.PageSplashScreen
import com.trian.smartwatch.SmartWatchActivity
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import javax.inject.Inject
/**
 * Main Activity
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 28/08/2021
 */
@ExperimentalMaterialNavigationApi
@ExperimentalAnimationApi
@ExperimentalPagerApi
@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var galIntent:Intent
    private lateinit var camIntent:Intent
    private lateinit var file : File
    private lateinit var uri:Uri
    private lateinit var crop:Intent
    @Inject lateinit var permissionUtils:PermissionUtils
    @Inject lateinit var persistence: Persistence

    @ExperimentalComposeUiApi
    @ExperimentalAnimatedInsets
    @RequiresApi(Build.VERSION_CODES.O)
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberAnimatedNavController()
            val coroutineScope = rememberCoroutineScope()
            val bottomSheetNavigator = rememberBottomSheetNavigator()

            //make statusbar custom color
            val systemUiController = rememberSystemUiController()
            val useDarkIcon = MaterialTheme.colors.isLight

           //add bottomsheet to a navigation
            navHostController.navigatorProvider += bottomSheetNavigator
             SideEffect {
                systemUiController.setStatusBarColor(
                    color = Color.White,
                    darkIcons = useDarkIcon
                )
            }

            fun setColorStatusBar(color:Color){
                systemUiController.setStatusBarColor(
                    color = color,
                )
            }
            TesMultiModuleTheme {

                ModalBottomSheetLayout(
                    bottomSheetNavigator
                ) {
                    AnimatedNavHost(
                        navController =navHostController,
                        startDestination = Routes.SPLASH
                    ){

                        composable(Routes.SPLASH,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }
                        ){
                            setColorStatusBar(Color.White)
                            PageSplashScreen(nav=navHostController,scope=coroutineScope,viewModel = viewModel)
                        }
                        composable(Routes.ONBOARD,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }){
                            setColorStatusBar(Color.White)
                            PageOnBoarding(nav=navHostController,scope = coroutineScope)
                        }
                        composable(Routes.LOGIN,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }){
                            setColorStatusBar(Color.White)
                            PageLogin(nav=navHostController,viewModel = viewModel,scope = coroutineScope)
                        }
                        composable(Routes.FORGET_PASSWORD,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }){
                            setColorStatusBar(Color.White)
                            PageForgetPassword(navHostController)
                        }
                        composable(Routes.SUCCESS_FORGET,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }){
                            setColorStatusBar(Color.White)
                            PageCompleteForget(navHostController)
                        }

                        navigation(startDestination = Routes.NESTED_DASHBOARD.HOME ,route = Routes.DASHBOARD){
                            composable(Routes.NESTED_DASHBOARD.HOME){
                                PageDashboard(
                                    nav=navHostController,
                                    scope=coroutineScope,
                                    viewModel=viewModel,
                                    toFeature = {goToFeature(it,navHostController)},
                                    page=Routes.NESTED_DASHBOARD.HOME,
                                    changeStatusBar = {setColorStatusBar(it)},
                                    opCamera = {openCamera()},
                                    opGallery = {openGallery()}
                                )
                            }
                            composable(Routes.NESTED_DASHBOARD.ACCOUNT){
                                PageDashboard(
                                    nav=navHostController,
                                    scope=coroutineScope,
                                    viewModel=viewModel,
                                    toFeature = {goToFeature(it,navHostController)},
                                    page=Routes.NESTED_DASHBOARD.ACCOUNT,
                                    changeStatusBar = {setColorStatusBar(it)},
                                    opCamera = {openCamera()},
                                    opGallery = {openGallery()}
                                )
                            }
                            composable(Routes.NESTED_DASHBOARD.LIST_HOSPITAL){
                                PageDashboard(
                                    nav=navHostController,
                                    scope=coroutineScope,
                                    viewModel=viewModel,
                                    toFeature = {goToFeature(it,navHostController)},
                                    page=Routes.NESTED_DASHBOARD.LIST_HOSPITAL,
                                    changeStatusBar = {setColorStatusBar(it)},
                                    opCamera = {openCamera()},
                                    opGallery = {openGallery()}
                                )
                            }
                            composable(Routes.NESTED_DASHBOARD.LIST_ORDER){
                                PageDashboard(
                                    nav=navHostController,
                                    scope=coroutineScope,
                                    viewModel=viewModel,
                                    toFeature = {goToFeature(it,navHostController)},
                                    page=Routes.NESTED_DASHBOARD.LIST_ORDER,
                                    changeStatusBar = {setColorStatusBar(it)},
                                    opCamera = {openCamera()},
                                    opGallery = {openGallery()}
                                )
                            }
                        }

                        composable(Routes.DETAIL_HOSPITAL,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }){
                            DetailHospital( nav = navHostController)
                        }
                        composable(Routes.REGISTER,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }){
                            PageRegister(navHostController,)
                        }
                        composable(Routes.DETAIL_HEALTH,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }){
                            PageDetailHealthStatus(
                                viewModel = viewModel,
                                nav=navHostController,
                                scope = coroutineScope,
                                changeStatusBar = {setColorStatusBar(it)}
                            )
                        }
                        composable(Routes.MOBILE_NURSE,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }){
                            PageListFeature()
                        }
                        composable(Routes.DETAIL_DOCTOR, enterTransition = {
                                _,_ ->
                            fadeIn(animationSpec = tween(2000))
                        }){
                            PageDetailDoctor(nav =navHostController)
                        }
                        composable(Routes.PRIVACY_POLICY, enterTransition = {
                                _,_ ->
                            fadeIn(animationSpec = tween(2000))
                        }){
                            PagePrivacyPolice()
                        }
                        composable(Routes.DETAIL_ORDER, enterTransition = {
                                _,_ ->
                            fadeIn(animationSpec = tween(2000))
                        }){
                            PageDetailOrder(nav = navHostController,)
                        }
                        composable(Routes.KUESIONER, enterTransition = {
                                _,_ ->
                            fadeIn(animationSpec = tween(2000))
                        }){
                            PageKuisioner()
                        }
                        bottomSheet(Routes.SHEET_SERVICE,){
                            BottomSheetServices(){
                                goToFeature(it,navHostController)
                            }
                        }
                        bottomSheet(Routes.SHEET_CANCELORDER,){
                            BottomSheetCancelOrder()
                        }
                        bottomSheet(Routes.SHEET_FORMORDER,){
                            BottomSheetFormOrder(scope = coroutineScope,nav = navHostController)
                        }
                        bottomSheet(Routes.SHEET_PRIVACYPOLICY){
                            BottomSheetPrivacyPolicy(nav=navHostController)
                        }
                        bottomSheet(Routes.SHEET_DETAILHOSPITAL){
                            BottomSheetHospital(HospitalLogo = painterResource(id = R.drawable.logo_cexup), hospital = Hospital(
                                id = 1,
                                slug = "rs-tele-cexup",
                                description = "",
                                thumb = "",
                                thumbOriginal = "",
                                name = "RS Tele Cexup",
                                address = "Jl. Jakarta Barat RT005/003, Meruya, Kecamatan Meruaya, Kelurahan Meruya, Kota Jakarta",
                                others = "",
                            ),)
                        }
                    }
                }
            }
        }
    }

    /**
     * start activity to each feature
     * **/
   private fun goToFeature(type: ServiceType,nav:NavHostController){
        when(type){
            ServiceType.HEALTH_TRACKER->{
                startActivity(
                    Intent(this@MainActivity,SmartWatchActivity::class.java)
                )
            }
            ServiceType.MEDICAL_RECORD->{ }
            ServiceType.MEDICINE->{}
            ServiceType.COVID_MONITORING->{ }
            ServiceType.MEDICAL_CHECKUP->{}
            ServiceType.RESERVATION->{}
            ServiceType.SHOP->{}
            ServiceType.TELECONSULTATION->{}
            ServiceType.MOBILE_NURSE->{
                nav.navigate(Routes.MOBILE_NURSE)
            }
            else->{}
        }
    }

    private fun openGallery(){
        galIntent = Intent(Intent.ACTION_PICK,
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        startActivityForResult(
            Intent.createChooser(galIntent, "Select Image from gallery"),
            2,
        )
    }

    private fun openCamera(){
        camIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        file = File(
            Environment.getExternalStorageDirectory(),
            "file" + System.currentTimeMillis().toString() + ".jpg",
        )
        uri = Uri.fromFile(file)
        camIntent.putExtra(MediaStore.EXTRA_OUTPUT,uri)
        camIntent.putExtra("return-data",true)
        startActivityForResult(camIntent,0)

    }

    private fun enableRuntimePermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(
                this@MainActivity,
                android.Manifest.permission.CAMERA,
            )){
            Toast.makeText(
                this@MainActivity,
                "Camera Permission allows us to Camera App",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    private fun cropImages(){
        try{
            crop = Intent("com.android.camera.action.Crop")
            crop.setDataAndType(uri, "image/*")
            crop.putExtra("crop", true)
            crop.putExtra("outputX",180)
            crop.putExtra("outputY", 180)
            crop.putExtra("aspectX",3)
            crop.putExtra("aspectY",4)
            crop.putExtra("scaleUpIfNeeded",true)
            crop.putExtra("return-data",true)
            startActivityForResult(crop,1)
        }catch (e:ActivityNotFoundException){
            e.printStackTrace()
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 0 && resultCode == RESULT_OK){
            cropImages()
        }else if(requestCode == 2){
            if(data != null){
                uri = data.data!!
                cropImages()
            }
        }else if(requestCode == 1){
            if(data != null){
                val bundel = data.extras
                val bitmap = bundel!!.getParcelable<Bitmap>("data")
                //
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            RequestPermissionCode -> if(grantResults.size>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this@MainActivity, "Permission, Now your application can access Camera", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this@MainActivity, "Permission, Now your application can access Camera", Toast.LENGTH_SHORT).show()
            }
        }
    }
    companion object {
        const val RequestPermissionCode = 111
    }
}

