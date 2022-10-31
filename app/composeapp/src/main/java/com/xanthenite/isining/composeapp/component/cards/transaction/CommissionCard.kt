package com.xanthenite.isining.composeapp.component.cards.transaction

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xanthenite.isining.composeapp.ui.theme.darkGreen
import com.xanthenite.isining.composeapp.ui.theme.darkOrange
import com.xanthenite.isining.composeapp.ui.theme.darkRed

@Composable
fun CommissionCard(
        title : String,
        price : String,
        address : String,
        note : String?,
        accepted_at : String?,
        deleted_at : String?,
        dateCreated : String)
{
    Card(shape = RoundedCornerShape(10.dp),
         backgroundColor = MaterialTheme.colors.surface,
         modifier = Modifier
                 .padding(vertical = 4.dp , horizontal = 8.dp)
                 .fillMaxWidth()
                 .wrapContentHeight()
                 .size(200.dp),
         elevation = 2.dp)
    {
        Column(modifier = Modifier
                .fillMaxSize(),
               horizontalAlignment = Alignment.CenterHorizontally,
               verticalArrangement = Arrangement.Center)
        {
            //Type
            Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp , end = 16.dp , top = 8.dp),
                   verticalArrangement = Arrangement.Center,
                   horizontalAlignment = Alignment.CenterHorizontally)
            {
                Row(modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically)
                {
                    Row(verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start)
                    {
                        Text(text = "Commission",
                             style = MaterialTheme.typography.h5,
                             textAlign = TextAlign.Start,
                             fontSize = 20.sp,
                             maxLines = 1,
                             overflow = TextOverflow.Ellipsis)
                    }
                    Row(horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically) {
                        StatusChipComm(accepted_at, deleted_at)
                    }
                }
            }
            //Title
            Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp , end = 16.dp , top = 8.dp , bottom = 4.dp),
                   verticalArrangement = Arrangement.Center,
                   horizontalAlignment = Alignment.CenterHorizontally)
            {
                Row(modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start)
                {
                    Text(text = title ,
                         style = MaterialTheme.typography.h5 ,
                         textAlign = TextAlign.Start ,
                         fontSize = 18.sp ,
                         maxLines = 2 ,
                         overflow = TextOverflow.Ellipsis)
                }
            }
            //Price
            Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp , end = 16.dp , bottom = 4.dp),
                   verticalArrangement = Arrangement.Center,
                   horizontalAlignment = Alignment.CenterHorizontally)
            {
                Row(modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start)
                {
                    Text(text = "Price: ₱$price",
                         style = MaterialTheme.typography.caption,
                         textAlign = TextAlign.Start,
                         fontSize = 16.sp)
                }
            }
            //Address
            Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp , end = 16.dp , bottom = 4.dp),
                   verticalArrangement = Arrangement.Center,
                   horizontalAlignment = Alignment.CenterHorizontally)
            {
                Row(modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start)
                {
                    Text(text = "Address: $address",
                         style = MaterialTheme.typography.caption,
                         textAlign = TextAlign.Start,
                         fontSize = 16.sp,
                         maxLines = 1,
                         overflow = TextOverflow.Ellipsis)
                }
            }
            //Description
            Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp , end = 16.dp , bottom = 4.dp),
                   verticalArrangement = Arrangement.Center,
                   horizontalAlignment = Alignment.CenterHorizontally)
            {
                Row(modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start)
                {
                    Text(text = "Note: ${note ?: "No additional note provided"}",
                         style = MaterialTheme.typography.caption,
                         textAlign = TextAlign.Start,
                         fontSize = 16.sp,
                         maxLines = 2,
                         overflow = TextOverflow.Ellipsis)
                }
            }
            //Date Created and status
            Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp , end = 16.dp , bottom = 8.dp),
                   verticalArrangement = Arrangement.Center,
                   horizontalAlignment = Alignment.CenterHorizontally)
            {
                Row(horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth())
                {
                    Text(text = "Submitted: $dateCreated" ,
                         style = MaterialTheme.typography.caption ,
                         textAlign = TextAlign.Start ,
                         fontSize = 16.sp ,
                         maxLines = 1 ,
                         overflow = TextOverflow.Ellipsis)
                }
            }
        }
    }
}

@Composable
fun StatusChipComm(accepted_at : String?, deleted_at : String?)
{
    val isApproved = darkGreen
    val isPending = darkOrange
    val isDenied = darkRed

    val pending = "PENDING"
    val approved = "APPROVED"
    val denied = "DENIED"

    Card(modifier = Modifier
            .width(105.dp)
            .height(30.dp) ,
         shape = RoundedCornerShape(10.dp) ,
         backgroundColor = if (accepted_at != null) isApproved
         else if (deleted_at != null) isDenied else isPending,
         elevation = 0.dp)
    {
        Column(modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 4.dp , vertical = 4.dp) ,
               horizontalAlignment = Alignment.CenterHorizontally ,
               verticalArrangement = Arrangement.Center)
        {
            Row(modifier = Modifier
                    .fillMaxWidth() ,
                horizontalArrangement = Arrangement.Center ,
                verticalAlignment = Alignment.CenterVertically)
            {
                Text(text = if (accepted_at != null) approved
                else if (deleted_at != null) denied else pending ,
                     style = MaterialTheme.typography.h5 ,
                     fontSize = 16.sp ,
                     textAlign = TextAlign.Center ,
                     color = Color.White ,
                     maxLines = 1)
            }
        }
    }
}