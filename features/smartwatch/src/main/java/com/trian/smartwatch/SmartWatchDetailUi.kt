package com.trian.smartwatch

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.github.mikephil.charting.data.Entry
import com.trian.common.utils.route.Routes
import com.trian.component.appbar.AppBarFeature
import com.trian.component.chart.BaseChartView
import com.trian.component.chart.EcgView
import com.trian.component.ui.theme.BluePrimary
import com.trian.component.ui.theme.ColorFontFeatures
import com.trian.component.ui.theme.FontDeviceName
import com.trian.component.ui.theme.TesMultiModuleTheme
import com.trian.component.utils.DetailSmartwatchUI
import com.trian.data.utils.calculateMaxMin
import com.trian.data.utils.explodeBloodPressure
import com.trian.data.viewmodel.SmartWatchViewModel
import compose.icons.Octicons
import compose.icons.octicons.Calendar24
import kotlinx.coroutines.CoroutineScope

@Composable
fun DetailSmartWatchUi(
    modifier:Modifier=Modifier,
    viewModel: SmartWatchViewModel,
    nav:NavHostController,
    scope:CoroutineScope,
    page:String,
    onClickCalender: ()-> Unit
){


    val data = mutableListOf<Entry>()
    val data2 = mutableListOf<Entry>()
    var maxAxis by remember{ mutableStateOf(0f)}
    var minAxis by remember{ mutableStateOf(0f)}
    val satuan = when(page){
        Routes.SmartwatchRoute.DETAIL_BLOOD_PRESSURE->"mmHg"
        Routes.SmartwatchRoute.DETAIL_BLOOD_OXYGEN->"%"
        Routes.SmartwatchRoute.DETAIL_ECG->""
        Routes.SmartwatchRoute.DETAIL_HEART_RATE->"bpm"
        Routes.SmartwatchRoute.DETAIL_RESPIRATION->"times/minute"
        Routes.SmartwatchRoute.DETAIL_TEMPERATURE->"c"
        else -> ""
    }
    when(page){
        Routes.SmartwatchRoute.DETAIL_ECG->{

        }
        Routes.SmartwatchRoute.DETAIL_TEMPERATURE->{
            maxAxis = 50f
            minAxis = 10f
        }
        Routes.SmartwatchRoute.DETAIL_RESPIRATION->{
            maxAxis = 60f
            minAxis = 5f
        }
        Routes.SmartwatchRoute.DETAIL_HEART_RATE->{
            maxAxis = 200f
            minAxis = 20f
        }
        Routes.SmartwatchRoute.DETAIL_BLOOD_OXYGEN->{
            maxAxis = 200f
            minAxis = 30f
        }


    }
    var latest by remember {
        mutableStateOf("0")
    }
    var max by remember {
        mutableStateOf("0")
    }
    var min by remember {
        mutableStateOf("0")
    }
    val scaffoldState = rememberScaffoldState()

    //equivalent `onStart`,`onResume`
    LaunchedEffect(key1 = scaffoldState){


    }

    //equivalent `onDestroy`
    DisposableEffect(key1 = scaffoldState){
        onDispose {
            when(page) {
                Routes.SmartwatchRoute.DETAIL_ECG ->{}

            }
        }
    }

    //calculate min max
    when(page){
        Routes.SmartwatchRoute.DETAIL_TEMPERATURE-> {
            val result by  viewModel.listTemperature
            result.forEachIndexed {
                    index, measurement ->
                data.add(
                    Entry(
                        index.toFloat(),
                         measurement.value.toFloat()

                    )
                )
            }
            result.calculateMaxMin{
                empty, lat, x, n ->
                if(!empty){
                    latest = "${lat!!.value}"
                    max = "${x!!.value}"
                    min = "${n!!.value}"
                }
            }
        }
        Routes.SmartwatchRoute.DETAIL_HEART_RATE-> {
            val result by  viewModel.listHeartRate
            result.forEachIndexed {
                    index, measurement ->
                data.add(
                    Entry(
                        index.toFloat(),
                        measurement.value.toFloat()

                    )
                )

            }
            result.calculateMaxMin{
                    empty, lat, x, n ->
                if(!empty){
                    latest = "${lat!!.value}"
                    max = "${x!!.value}"
                    min = "${n!!.value}"
                }
            }
        }
        Routes.SmartwatchRoute.DETAIL_RESPIRATION-> {
            val result by  viewModel.listRespiration
            result.forEachIndexed {
                    index, measurement ->
                data.add(
                    Entry(
                        index.toFloat(),
                        measurement.value.toFloat()

                    )
                )

            }
            result.calculateMaxMin{
                    empty, lat, x, n ->
                if(!empty){
                    latest = "${lat!!.value}"
                    max = "${x!!.value}"
                    min = "${n!!.value}"
                }
            }
        }
        Routes.SmartwatchRoute.DETAIL_BLOOD_OXYGEN-> {
            val result by  viewModel.listBloodOxygen
            result.forEachIndexed {
                    index, measurement ->
                data.add(
                    Entry(
                        index.toFloat(),
                        measurement.value.toFloat()

                    )
                )

            }
            result.calculateMaxMin{
                    empty, lat, x, n ->
                if(!empty){
                    latest = "${lat!!.value}"
                    max = "${x!!.value}"
                    min = "${n!!.value}"
                }
            }
        }
        Routes.SmartwatchRoute.DETAIL_BLOOD_PRESSURE-> {
            val result by  viewModel.listBloodPressure
            result.forEachIndexed {
                    index, measurement ->
                val bpm = measurement.value.explodeBloodPressure()
                data.add(
                    Entry(
                        index.toFloat(),
                        bpm.systole.toFloat()

                    )
                )
                data2.add(
                    Entry(
                        index.toFloat(),
                        bpm.diastole.toFloat()
                    )
                )

            }
            result.calculateMaxMin{
                    empty, lat, x, n ->
                if(!empty){
                    val bpmLatest = lat!!.value.explodeBloodPressure()
                    val bpmMax = x!!.value.explodeBloodPressure()
                    val bpmMin = n!!.value.explodeBloodPressure()
                    latest = "${bpmLatest.systole}/${bpmLatest.diastole}"
                    max = "${bpmMax.systole}/${bpmMax.diastole}"
                    min = "${bpmMin.systole}/${bpmMin.diastole}"
                }
            }
        }
        Routes.SmartwatchRoute.DETAIL_SLEEP-> {
            val result by  viewModel.listSleep
            result.forEachIndexed {
                    index, measurement ->
                data.add(
                    Entry(
                        index.toFloat(),
                        0f
                    )
                )

            }
        }
        else-> {

        }
    }
            DetailSmartwatchUI(
                appBar = {
                    AppBarFeature(name = "Andi", image ="" , onBackPressed = { /*TODO*/ }, onProfile = {})
                },
                scaffoldState = scaffoldState
            ){
                header {
                    when(page){
                        Routes.SmartwatchRoute.DETAIL_ECG ->{
                            Row(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                                    .background(Color.White),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceAround
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "60", //diastole
                                        fontSize = 24.sp,
                                        color = ColorFontFeatures
                                    )
                                    Text(
                                        text = "bpm",
                                        fontSize = 16.sp,
                                        color = ColorFontFeatures,
                                    )
                                }
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Row() {
                                        Text(
                                            text = "60", //diastole
                                            fontSize = 24.sp,
                                            color = ColorFontFeatures
                                        )
                                        Text(
                                            text = "/",
                                            fontSize = 24.sp,
                                            color = ColorFontFeatures
                                        )
                                        Text(
                                            text = "60", //diastole
                                            fontSize = 24.sp,
                                            color = ColorFontFeatures
                                        )
                                    }

                                    Text(
                                        text = "mmHg",
                                        fontSize = 16.sp,
                                        color = ColorFontFeatures,
                                    )
                                }

                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "60", //diastole
                                        fontSize = 24.sp,
                                        color = ColorFontFeatures
                                    )
                                    Text(
                                        text = "HRV",
                                        fontSize = 16.sp,
                                        color = ColorFontFeatures,
                                    )
                                }

                            }
                        }
                        else ->{
                            //calender
                            Row(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 16.dp),
                            ) {
                                Icon(
                                    Octicons.Calendar24,
                                    contentDescription = "",
                                    tint = ColorFontFeatures,
                                    modifier = modifier.clickable { onClickCalender() }
                                )
                                Text(
                                    text = "Mon, Sep 14",
                                    modifier = modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center,
                                    color = ColorFontFeatures,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )

                            }
                            //latest value
                            Row(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {

                                Text(
                                    text = latest, //diastole
                                    fontSize = 32.sp,
                                    color = ColorFontFeatures
                                )
                                Spacer(modifier = modifier.width(5.dp))
                                Text(
                                    text = satuan,
                                    fontSize = 16.sp,
                                    color = ColorFontFeatures,
                                    modifier = modifier.padding(top = 10.dp)
                                )
                            }
                        }
                    }
                }
                body {
                        when (page) {
                            Routes.SmartwatchRoute.DETAIL_BLOOD_PRESSURE -> {
                                Column(
                                    modifier = modifier
                                        .fillMaxHeight(0.4f)
                                        .background(Color.White)
                                        .padding(horizontal = 16.dp, vertical = 10.dp)

                                ) {
                                    BaseChartView(
                                        data = data,
                                        description = "Systole",
                                        maxAxis = 250f,
                                        minAxis = 70f
                                    )
                                }
                                Column(
                                    modifier = modifier
                                        .fillMaxHeight(0.7f)
                                        .background(Color.White)
                                        .padding(horizontal = 16.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {

                                    BaseChartView(
                                        data = data2,
                                        description = "Diastole",
                                        maxAxis = 150f,
                                        minAxis = 50f
                                    )
                                }
                            }
                            Routes.SmartwatchRoute.DETAIL_ECG->{
                                val ecgwave by viewModel.ecgWave


                               Column(modifier=modifier.fillMaxHeight(0.8f)) {
                                  EcgView(list=ecgwave)
                                 //  EcgView(list=ecgwave)
                               }
                            }
                            else ->{
                                Column(
                                    modifier = modifier
                                        .background(Color.White)
                                        .fillMaxHeight(0.8f)
                                        .padding(horizontal = 16.dp, vertical = 10.dp)

                                ) {
                                    BaseChartView(
                                        data = data,
                                        description = page, //deskripsi heartrate,temperature,SpO2,Respiratory
                                        maxAxis = maxAxis,
                                        minAxis = minAxis
                                    )
                                }
                            }
                        }
                }
                footer {
                        when(page) {
                            Routes.SmartwatchRoute.DETAIL_SLEEP -> {
                                Column(
                                    modifier = modifier
                                        .background(FontDeviceName)
                                        .fillMaxWidth()
                                        .fillMaxHeight(),
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Spacer(modifier = modifier.height(16.dp))
                                    Row(
                                        modifier = modifier
                                            .padding(horizontal = 16.dp)
                                            .fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceAround,
                                        verticalAlignment = Alignment.CenterVertically){
                                        Column(
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                        ) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                            ) {
                                                Text(
                                                    text = "122", //value hour of sleep duration
                                                    fontSize = 26.sp,
                                                    color = ColorFontFeatures
                                                )
                                                Spacer(modifier = modifier.width(2.dp))
                                                Text(
                                                    text= "H",
                                                    fontSize = 14.sp,
                                                    color = ColorFontFeatures,
                                                    modifier = modifier.padding(top = 10.dp)
                                                )
                                                Spacer(modifier = modifier.width(5.dp))
                                                Text(
                                                    text = "122",//value minute of sleep duration
                                                    fontSize = 26.sp,
                                                    color = ColorFontFeatures
                                                )
                                                Spacer(modifier = modifier.width(2.dp))
                                                Text(
                                                    text= "M",
                                                    fontSize = 14.sp,
                                                    color = ColorFontFeatures,
                                                    modifier = modifier.padding(top = 10.dp)
                                                )
                                            }
                                            Text(
                                                text = "Sleep Duration",
                                                fontSize = 14.sp,
                                                color = ColorFontFeatures,
                                            )
                                        }
                                        Column(
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                        ) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                            ) {
                                                Text(
                                                    text = "0", //value wake time
                                                    fontSize = 26.sp,
                                                    color = ColorFontFeatures
                                                )
                                            }
                                            Text(
                                                text = "Wake Time",
                                                fontSize = 14.sp,
                                                color = ColorFontFeatures,
                                            )
                                        }

                                    }
                                    Spacer(modifier = modifier.height(16.dp))
                                    Row(
                                        modifier = modifier
                                            .padding(horizontal = 16.dp)
                                            .fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceAround,
                                        verticalAlignment = Alignment.CenterVertically){
                                        Column(
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                        ) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                            ) {
                                                Text(
                                                    text = "00:00", //value time
                                                    fontSize = 26.sp,
                                                    color = ColorFontFeatures
                                                )

                                            }
                                            Text(
                                                text = "Fall Asleep Time",
                                                fontSize = 14.sp,
                                                color = ColorFontFeatures,
                                            )
                                        }
                                        Column(
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                        ) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                            ) {
                                                Text(
                                                    text = "00:00", //value time Awake Time
                                                    fontSize = 26.sp,
                                                    color = ColorFontFeatures
                                                )
                                            }
                                            Text(
                                                text = "Awake Time",
                                                fontSize = 14.sp,
                                                color = ColorFontFeatures,
                                            )
                                        }

                                    }
                                    Spacer(modifier = modifier.height(16.dp))
                                    Row(
                                        modifier = modifier
                                            .padding(horizontal = 16.dp)
                                            .fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceAround,
                                        verticalAlignment = Alignment.CenterVertically){
                                        Column(
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                        ) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                            ) {
                                                Text(
                                                    text = "00", //value hour of light sleep
                                                    fontSize = 26.sp,
                                                    color = ColorFontFeatures
                                                )
                                                Spacer(modifier = modifier.width(2.dp))
                                                Text(
                                                    text= "H",
                                                    fontSize = 14.sp,
                                                    color = ColorFontFeatures,
                                                    modifier = modifier.padding(top = 10.dp)
                                                )
                                                Spacer(modifier = modifier.width(5.dp))
                                                Text(
                                                    text = "00",//value minute of Light sleep
                                                    fontSize = 26.sp,
                                                    color = ColorFontFeatures
                                                )
                                                Spacer(modifier = modifier.width(2.dp))
                                                Text(
                                                    text= "M",
                                                    fontSize = 14.sp,
                                                    color = ColorFontFeatures,
                                                    modifier = modifier.padding(top = 10.dp)
                                                )
                                            }
                                            Text(
                                                text = "Light Sleep",
                                                fontSize = 14.sp,
                                                color = ColorFontFeatures,
                                            )
                                        }
                                        Column(
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                        ) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                            ) {
                                                Text(
                                                    text = "00", //value hour of deep sleep
                                                    fontSize = 26.sp,
                                                    color = ColorFontFeatures
                                                )
                                                Spacer(modifier = modifier.width(2.dp))
                                                Text(
                                                    text= "H",
                                                    fontSize = 14.sp,
                                                    color = ColorFontFeatures,
                                                    modifier = modifier.padding(top = 10.dp)
                                                )
                                                Spacer(modifier = modifier.width(5.dp))
                                                Text(
                                                    text = "00",//value minute of deep sleep
                                                    fontSize = 26.sp,
                                                    color = ColorFontFeatures
                                                )
                                                Spacer(modifier = modifier.width(2.dp))
                                                Text(
                                                    text= "M",
                                                    fontSize = 14.sp,
                                                    color = ColorFontFeatures,
                                                    modifier = modifier.padding(top = 10.dp)
                                                )
                                            }
                                            Text(
                                                text = "Deep Sleep",
                                                fontSize = 14.sp,
                                                color = ColorFontFeatures,
                                            )
                                        }

                                    }
                                    Spacer(modifier = modifier.height(16.dp))
                                }
                            }
                            Routes.SmartwatchRoute.DETAIL_ECG ->{
                                var recordState by remember {
                                    mutableStateOf(false)
                                }
                                var progress by remember{ mutableStateOf(0.0f)}
                                val animatedProgress = animateFloatAsState(
                                    targetValue = progress,
                                    animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
                                ).value
                                var persentage : Float = (progress/1f)*100
                                var persen = persentage.toInt()
                                var isStart by remember{
                                    mutableStateOf(false)
                                }
                                Row(
                                    modifier = modifier
                                        .background(FontDeviceName)
                                        .fillMaxWidth()
                                        .fillMaxHeight(),
                                    horizontalArrangement = Arrangement.SpaceAround,
                                    verticalAlignment = Alignment.CenterVertically){
                                    Column(
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = modifier
                                            .fillMaxHeight()
                                            .padding(horizontal = 16.dp)
                                    ) {

                                        Text(
                                            text = "$persen %",
                                            modifier = Modifier.fillMaxWidth(),
                                            textAlign = TextAlign.Left,
                                            color = Color.White
                                        )
                                        LinearProgressIndicator(
                                            progress = animatedProgress,
                                            color = BluePrimary,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(10.dp)
                                                .clip(shape = RoundedCornerShape(5.dp))
                                        )
                                        Spacer(modifier = Modifier.height(30.dp))
                                        Button(
                                            onClick = {
                                                if(isStart){
                                                    isStart = false
                                                    viewModel.endEcg()
                                                }else {
                                                    isStart = true
                                                    viewModel.startEcgTest()
                                                }
                                                recordState = !recordState
                                                if(progress <1f) {
                                                    progress +=0.01f
                                                }

                                            },
                                            modifier = modifier
                                                .width(150.dp),
                                        ) {
                                            if(recordState){
                                                Icon(
                                                    Icons.Filled.Stop,
                                                    contentDescription = "play",
                                                    tint = Color.White,
                                                    modifier = modifier.size(24.dp)
                                                )
                                            }else{
                                                Icon(
                                                    Icons.Filled.PlayArrow,
                                                    contentDescription = "stop",
                                                    tint = Color.White,
                                                    modifier = modifier.size(24.dp)
                                                )
                                            }
                                        }

                                    }
                                }
                            }
                            else -> {
                                Row(
                                    modifier = modifier
                                        .background(FontDeviceName)
                                        .fillMaxWidth()
                                        .fillMaxHeight(),
                                    horizontalArrangement = Arrangement.SpaceAround,
                                    verticalAlignment = Alignment.CenterVertically){
                                    Column(
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = modifier.fillMaxHeight()
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                        ) {
                                            Text(
                                                text = max, //value heartrate,temperature,SpO2,Respiratory
                                                fontSize = 26.sp,
                                                color = ColorFontFeatures
                                            )
                                            Spacer(modifier = modifier.width(5.dp))
                                            Text(
                                                text=satuan,
                                                fontSize = 14.sp,
                                                color = ColorFontFeatures,
                                                modifier = modifier.padding(top = 10.dp)
                                            )
                                        }
                                        Text(
                                            text = "Max",
                                            fontSize = 14.sp,
                                            color = ColorFontFeatures,
                                        )
                                    }
                                    Column(
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = modifier.fillMaxHeight()
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                        ) {
                                            Text(
                                                text = min, //value heartrate,temperature,SpO2,Respiratory
                                                fontSize = 26.sp,
                                                color = ColorFontFeatures
                                            )
                                            Spacer(modifier = modifier.width(5.dp))
                                            Text(
                                                text= satuan,//satuan heartrate,temperature,SpO2,Respiratory
                                                fontSize = 14.sp,
                                                color = ColorFontFeatures,
                                                modifier = modifier.padding(top = 10.dp)
                                            )
                                        }
                                        Text(
                                            text = "Min",
                                            fontSize = 14.sp,
                                            color = ColorFontFeatures,
                                        )
                                    }

                                }

                            }
                        }
                }
            }

}

@Composable
fun EcgUiTest(
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
            .background(FontDeviceName)
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxHeight()
                .padding(horizontal = 16.dp)
        ) {
            var recordState by remember {
                mutableStateOf(false)
            }
            var progress by remember{ mutableStateOf(0.0f)}
            val animatedProgress = animateFloatAsState(
                targetValue = progress,
                animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
            ).value
            var persentage : Float = (progress/1f)*100
            var persen = persentage.toInt()
            Text(
                text = "$persen %",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Left,
                color = Color.White
            )
            LinearProgressIndicator(
                progress = animatedProgress,
                color = BluePrimary,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .clip(shape = RoundedCornerShape(5.dp))
            )
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = {
                    recordState = !recordState
                    if(progress <1f) {
                        progress +=0.01f
                    }},
                modifier = modifier
                    .width(150.dp),
            ) {
                if(recordState){
                    Icon(
                        Icons.Filled.Stop,
                        contentDescription = "play",
                        tint = Color.White,
                        modifier = modifier.size(24.dp)
                    )
                }else{
                    Icon(
                        Icons.Filled.PlayArrow,
                        contentDescription = "stop",
                        tint = Color.White,
                        modifier = modifier.size(24.dp)
                    )
                }
            }

            }
        }


    }


@Preview
@Composable
fun DetailSmartWatchUiPreview(){
    TesMultiModuleTheme {
//        DetailSmartWatchUi(
//            onClickCalender = {},
//            page="Bpm",
//            viewModel = viewModel(),
//            scope = rememberCoroutineScope(),
//            nav = rememberNavController()
//        )
        EcgUiTest()
    }
}