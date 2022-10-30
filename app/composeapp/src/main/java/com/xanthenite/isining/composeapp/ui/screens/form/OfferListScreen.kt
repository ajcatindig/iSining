package com.xanthenite.isining.composeapp.ui.screens.form

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.xanthenite.isining.composeapp.R
import com.xanthenite.isining.composeapp.component.ConnectivityStatus
import com.xanthenite.isining.composeapp.component.anim.LottieAnimation
import com.xanthenite.isining.composeapp.component.list.transaction.OfferList
import com.xanthenite.isining.composeapp.component.list.transaction.TransactionList
import com.xanthenite.isining.composeapp.component.scaffold.ISiningScaffold
import com.xanthenite.isining.composeapp.component.scaffold.form.OffersTopbar
import com.xanthenite.isining.composeapp.component.scaffold.form.TransactionTopbar
import com.xanthenite.isining.composeapp.utils.collectState
import com.xanthenite.isining.core.model.Offer
import com.xanthenite.isining.view.viewmodel.form.OfferListViewModel

@Composable
fun OfferListScreen(
        onNavigateUp : () -> Unit,
        viewModel : OfferListViewModel)
{
    val state by viewModel.collectState()

    OfferListContent(
            isLoading =  state.isLoading,
            isConnectivityAvailable =  state.isConnectivityAvailable,
            onRefresh = viewModel::getOffers ,
            data = state.data,
            onNavigateUp = onNavigateUp)
}

@Composable
fun OfferListContent(
        isLoading : Boolean,
        isConnectivityAvailable : Boolean?,
        error : String? = null,
        onRefresh : () -> Unit,
        data : List<Offer>,
        onNavigateUp : () -> Unit)
{
    ISiningScaffold(
        error = error,
        topAppBar = {
            OffersTopbar(
                    onNavigateUp = onNavigateUp
                             )
        },
        content = {
            SwipeRefresh(
                    state = rememberSwipeRefreshState(isLoading) ,
                    onRefresh = onRefresh ,
                    swipeEnabled = isConnectivityAvailable == true)
            {
                Column {
                    if (isConnectivityAvailable != null) {
                        ConnectivityStatus(isConnectivityAvailable)
                    }
                    if (data.isNotEmpty()) {
                        OfferList(data = data)
                    } else {
                        Column(modifier = Modifier
                                .fillMaxSize() ,
                               horizontalAlignment = Alignment.CenterHorizontally ,
                               verticalArrangement = Arrangement.Center)
                        {
                            Row(modifier = Modifier
                                    .fillMaxWidth() ,
                                horizontalArrangement = Arrangement.Center ,
                                verticalAlignment = Alignment.CenterVertically)
                            {
                                LottieAnimation(resId = R.raw.no_artworks ,
                                                modifier = Modifier.size(250.dp))
                            }
                            Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp , vertical = 8.dp) ,
                                horizontalArrangement = Arrangement.Center ,
                                verticalAlignment = Alignment.CenterVertically)
                            {
                                Text(text = "You don't have any offers." ,
                                     style = MaterialTheme.typography.h5 ,
                                     fontSize = 18.sp ,
                                     textAlign = TextAlign.Center)
                            }
                        }
                    }
                }
            }
        }
    )
}