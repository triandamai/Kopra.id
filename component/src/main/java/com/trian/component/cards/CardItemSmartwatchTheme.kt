package com.trian.component.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.datum.listThemeSmartwatch
import com.trian.component.ui.theme.ColorFontFeatures
import com.trian.component.utils.coloredShadow
import com.trian.domain.models.SmartwatchThemeModel

@Composable
fun CardItemSmartwatchTheme(
    modifier: Modifier = Modifier,
    selected:Boolean = false,
    smartwatchThemeModel: SmartwatchThemeModel,
    onSelect:(index:SmartwatchThemeModel)->Unit
){


                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Box(modifier = modifier
                            .background(
                                color = Color.Transparent,
                                shape = RoundedCornerShape(15.dp)
                            )
                            .padding(10.dp)
                            .height(120.dp)
                        ) {
                            Image(
                                painter = painterResource(id = smartwatchThemeModel.thumb),
                                contentDescription = "",
                                modifier = modifier
                                    .clip(shape = RoundedCornerShape(15.dp))
                                    .border(
                                        2.dp,
                                        when(selected){
                                            true-> ColorFontFeatures
                                            false -> Color.Gray
                                        },
                                        RoundedCornerShape(15.dp)
                                    ).clickable {
                                        onSelect(smartwatchThemeModel)

                                    }
                            )
                            Column(
                                modifier = modifier.padding(start = 5.dp, top = 5.dp)
                            ) {
                                Row(
                                    modifier = modifier
                                        .background(Color.White, shape = CircleShape),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    RadioButton(
                                        selected = selected,
                                        onClick = {
                                            onSelect(smartwatchThemeModel)
                                        },

                                        enabled = true,
                                        colors = RadioButtonDefaults.colors(
                                            selectedColor = ColorFontFeatures,
                                            unselectedColor = Color.Black,
                                            disabledColor = Color.Black
                                        ),
                                    )
                                }
                            }

                        }
                        Text(text = smartwatchThemeModel.name)
                    }

}