package app.trian.kopra.ui.pages.store

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.trian.common.utils.route.Routes
import com.trian.component.appbar.AppBarHistoryTransaction
import com.trian.component.appbar.AppBarListStore
import com.trian.component.appbar.TabLayout
import com.trian.component.cards.CardItemTransaction
import com.trian.component.cards.CardStore
import com.trian.data.viewmodel.MainViewModel
import com.trian.domain.models.Store
import com.trian.domain.models.Transaction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Page Dashboard Profile
 * Author Trian damai
 * Created by Trian Damai
 * 07/11/2021
 */

@ExperimentalPagerApi
@Composable
fun PageSearchStore(
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    mainViewModel: MainViewModel,
    navHostController: NavHostController,
    scope: CoroutineScope
){

   Scaffold(
       scaffoldState =scaffoldState,
       topBar = {
           AppBarListStore(onBackPressed = {
               navHostController.popBackStack()
           }) {

           }
       }
   ) {
       Column {

               LazyColumn(
                   modifier=modifier.padding(horizontal = 16.dp),
                   content = {
                   items(count = 10,itemContent = {
                       index->
                       if(index ==0){
                           Spacer(modifier = modifier.height(10.dp))
                       }
                       CardStore(index = index,store = Store(),onDetail = {
                               index, store ->
                           navHostController.navigate(Routes.DETAIL_TOKO)
                       },onEdit = {
                               index, store ->

                       },onDelete = {
                               index, store ->
                       })
                   })
               })

       }
   }
}