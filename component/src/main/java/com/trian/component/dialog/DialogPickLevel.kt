package comodifier.trian.component.dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.trian.component.ui.theme.BluePrimary
import com.trian.component.utils.mediaquery.Dimensions
import com.trian.component.utils.mediaquery.lessThan
import com.trian.component.utils.mediaquery.mediaQuery
/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 21/10/2021
 */
@Composable
fun DialogPickLevel(
    modifier: Modifier=Modifier,
    show:Boolean,
    onPick:()->Unit,
    onCancel:()->Unit
) {

        val listStatus = listOf("Petani","Pengepul","Penyewa Kendaraan")
        var mRememberStatusUser by remember{ mutableStateOf("") }
        var sizeBorder by remember{ mutableStateOf(0.dp) }
        var colorBorder by remember{ mutableStateOf(Color.Unspecified) }

    if(show){
        Dialog(onDismissRequest = onCancel) {
            Column(
                modifier = modifier.padding(
                    20.dp
                ),
            ) {
                Text(
                    text = "Pilih status",
                    style = TextStyle().mediaQuery(
                        Dimensions.Width lessThan 400.dp,
                        value= MaterialTheme.typography.h1.copy(
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.1.sp,
                        )
                    ),
                )
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = "Sebelum menggunakan fitur-fitur di aplikasi Kopra.Id, Silahkan konfirmasi status user anda",
                    style = TextStyle().mediaQuery(
                        Dimensions.Width lessThan 400.dp,
                        value= MaterialTheme.typography.h1.copy(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            letterSpacing = 0.1.sp,
                        )
                    ),
                )
                Spacer(modifier = modifier.height(20.dp))
                LazyColumn(content = {
                    items(count = listStatus.size,itemContent = {index->
                        Card(
                            shape = RoundedCornerShape(10.dp),
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                                .selectable(
                                    selected = mRememberStatusUser == listStatus[index],
                                    onClick = {
                                        mRememberStatusUser = listStatus[index]
                                    }
                                ),
                            elevation = 0.dp,
                            backgroundColor = BluePrimary.copy(alpha = 0.1f),
                            border = BorderStroke(width = 1.dp,color = BluePrimary )
                        ) {
                            Row(
                                modifier = modifier
                                    .padding(12.dp)
                            ){
                                RadioButton(
                                    selected = mRememberStatusUser == listStatus[index],
                                    onClick = { mRememberStatusUser = listStatus[index] },
                                    enabled = true,
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = BluePrimary,
                                    ),
                                )
                                Text(text = listStatus[index],modifier = modifier.padding(start = 8.dp))
                            }
                        }
                    })
                })

                Spacer(modifier = modifier.height(20.dp))
                Button(
                    onClick ={
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

@Preview
@Composable
fun PreviewDialogPickLevel(){
    DialogPickLevel(show = false, onPick = { /*TODO*/ }) {
        
    }
}