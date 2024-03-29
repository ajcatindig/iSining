package com.xanthenite.isining.composeapp.component.cards.transaction

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xanthenite.isining.composeapp.ui.theme.darkGreen
import com.xanthenite.isining.composeapp.ui.theme.darkOrange
import com.xanthenite.isining.composeapp.ui.theme.darkRed
import com.xanthenite.isining.composeapp.ui.theme.typography
import com.xanthenite.isining.composeapp.utils.ISiningPreview
import com.xanthenite.isining.composeapp.utils.IntentUtils
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun TransactionCard(
        title : String,
        price : String,
        address : String,
        description : String?,
        verified_at : String?,
        dateCreated : String,
        payment_channel : Int,
        payment_link : String)
{
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    val date = dateCreated.format(dateFormat)
    val isEnabled by derivedStateOf { payment_link.isNotBlank() && verified_at.isNullOrEmpty() }
    val context = LocalContext.current

    Card(shape = RoundedCornerShape(10.dp),
         backgroundColor = MaterialTheme.colors.surface,
         modifier = Modifier
             .padding(vertical = 4.dp, horizontal = 8.dp)
             .fillMaxWidth()
             .wrapContentHeight()
             .size(270.dp),
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
                .padding(start = 16.dp, end = 16.dp, top = 8.dp),
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
                        Text(text = "ARTWORK",
                             style = MaterialTheme.typography.h5,
                             textAlign = TextAlign.Start,
                             fontSize = 20.sp,
                             maxLines = 1,
                             overflow = TextOverflow.Ellipsis)
                    }
                    Row(horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically) {
                        StatusChip(status = if (verified_at != null) "yes" else null)
                    }
                }
            }
            //Title
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 4.dp),
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
                .padding(start = 16.dp, end = 16.dp, bottom = 4.dp),
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
                .padding(start = 16.dp, end = 16.dp, bottom = 4.dp),
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
                .padding(start = 16.dp, end = 16.dp, bottom = 4.dp),
                   verticalArrangement = Arrangement.Center,
                   horizontalAlignment = Alignment.CenterHorizontally)
            {
                Row(modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start)
                {
                    Text(text = "Description: ${description ?: "No Description Provided"}",
                         style = MaterialTheme.typography.caption,
                         textAlign = TextAlign.Start,
                         fontSize = 16.sp,
                         maxLines = 2,
                         overflow = TextOverflow.Ellipsis)
                }
            }
            //Payment Style
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 4.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                Row(horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth())
                {
                    Text(text = when(payment_channel) {
                            1 -> "Payment: Cash on Delivery"
                            else -> "Payment: GCash"
                        },
                        style = MaterialTheme.typography.caption ,
                        textAlign = TextAlign.Start ,
                        fontSize = 16.sp ,
                        maxLines = 1 ,
                        overflow = TextOverflow.Ellipsis)
                }
            }
            //Payment Status
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 4.dp),
                   verticalArrangement = Arrangement.Center,
                   horizontalAlignment = Alignment.CenterHorizontally)
            {
                Row(horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth())
                {
                    Text(text = if (verified_at != null) "Status: Paid" else "Status: Awaiting for payment" ,
                         style = MaterialTheme.typography.caption ,
                         textAlign = TextAlign.Start ,
                         fontSize = 16.sp ,
                         maxLines = 1 ,
                         overflow = TextOverflow.Ellipsis)
                }
            }
            //Date Created and status
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
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
            Divider(modifier = Modifier.height(1.dp))
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 8.dp),
                   verticalArrangement = Arrangement.Center,
                   horizontalAlignment = Alignment.CenterHorizontally)
            {
                Row(modifier = Modifier
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically)
                {
                    Button(onClick = { IntentUtils.launchBrowser(context, payment_link) },
                           enabled = isEnabled ,
                           modifier = Modifier
                               .height(30.dp)
                               .width(105.dp),
                           colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black) ,
                           shape = RoundedCornerShape(10.dp))
                    {
                        Text(text = "Pay Now" , color = Color.White , style = typography.h6, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun StatusChip(status : String?)
{
    val isApproved = darkGreen
    val isPending = darkOrange
    val isDenied = darkRed

    val pending = "PENDING"
    val approved = "PAID"
    val denied = "DENIED"
    
    Card(modifier = Modifier
        .width(105.dp)
        .height(30.dp),
         shape = RoundedCornerShape(10.dp),
         backgroundColor = when (status) {
             "yes" -> isApproved
             "no"  -> isDenied
             else  -> isPending },
         elevation = 0.dp)
    {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 4.dp, vertical = 4.dp),
               horizontalAlignment = Alignment.CenterHorizontally,
               verticalArrangement = Arrangement.Center) 
        {
            Row(modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) 
            {
                Text(text = when (status) {
                        "yes" -> approved
                        "no" -> denied
                        else -> pending
                     },
                     style = MaterialTheme.typography.h5,
                     fontSize = 16.sp,
                     textAlign = TextAlign.Center,
                     color = Color.White,
                     maxLines = 1)
            }
        }
    }    
}



@Preview(showBackground = true)
@Composable
fun PreviewStatusChip() = ISiningPreview {
    StatusChip(status = "approved")
    StatusChip(status = "denied")
    StatusChip(status = "pending")
}