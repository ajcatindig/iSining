package com.xanthenite.isining.composeapp.component.cards.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.ArrowRightAlt
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.glide.GlideImage
import com.xanthenite.isining.composeapp.ui.theme.lightBlue
import com.xanthenite.isining.composeapp.ui.theme.offWhite
import com.xanthenite.isining.core.model.User

@Composable
fun ProfileCard(
        data : User,
        onLogoutClick : () -> Unit,
        onChangePasswordClick: () -> Unit,
        onAboutAppClick : () -> Unit,
        onTransactionClick: () -> Unit,
        onManageProfileClick : () -> Unit,
        onOffersClick : () -> Unit,
        onCommissionsClick : () -> Unit)
{
    Column(modifier = Modifier.verticalScroll(rememberScrollState()))
    {
        Card(
                backgroundColor = MaterialTheme.colors.surface,
                modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                shape = RoundedCornerShape(0.dp),
                elevation = 0.dp)
        {
            Column {
                //Image
                Column{
                    Row(verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(16.dp))
                    {
                        GlideImage(
                            imageModel = data.profile_photo_path,
                            modifier = Modifier
                                    .size(110.dp)
                                    .clip(CircleShape),
                            loading = {
                                Box(modifier = Modifier.matchParentSize())
                                {
                                    CircularProgressIndicator(
                                            modifier = Modifier.align(Alignment.Center),
                                            color = MaterialTheme.colors.onPrimary)
                                }
                            },
                            failure = {
                                val substring = data.name?.substring(0 , 1)?.toUpperCase()
                                Box(modifier = Modifier
                                        .matchParentSize()
                                        .background(offWhite))
                                {
                                    Text(
                                            text = substring.orEmpty() ,
                                            modifier = Modifier.align(Alignment.Center) ,
                                            style = MaterialTheme.typography.h4 ,
                                            fontSize = 40.sp,
                                            color = lightBlue)
                                }
                            }
                        )
                        Column(modifier = Modifier.padding(horizontal = 14.dp, vertical = 20.dp),
                               verticalArrangement = Arrangement.Center,
                               horizontalAlignment = Alignment.Start)
                        {
                            Text(text = "${data.first_name.orEmpty()} ${data.last_name.orEmpty()}",
                                 maxLines = 2,
                                 overflow = TextOverflow.Ellipsis,
                                 style = MaterialTheme.typography.h5,
                                 fontSize = 20.sp)
                            Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                horizontalArrangement = Arrangement.Start)
                            {
                                Text(text = "@${data.name.orEmpty()}",
                                     style = MaterialTheme.typography.subtitle2,
                                     fontSize = 16.sp,
                                     fontWeight = FontWeight.Bold)
                            }
                            Row(modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Start)
                            {
                                Text(text = data.email.orEmpty(),
                                     style = MaterialTheme.typography.subtitle2,
                                     fontSize = 16.sp,)
                            }
                            Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                horizontalArrangement = Arrangement.Start)
                            {
                                Text(text = data.mobile_number?:"No number provided",
                                     style = MaterialTheme.typography.subtitle2,
                                     fontSize = 16.sp,)
                            }
                        }
                    }
                    Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp , end = 16.dp , bottom = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start)
                    {
                        Text(text = "Bio",
                             style = MaterialTheme.typography.h5,
                             fontSize = 20.sp,
                             textAlign = TextAlign.Start)
                    }
                    Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp , end = 16.dp , bottom = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start)
                    {
                        Text(text = data.bio?:"Write something about yourself",
                             style = MaterialTheme.typography.caption,
                             fontSize = 16.sp,
                             textAlign = TextAlign.Start,
                             maxLines = 3,
                             overflow = TextOverflow.Ellipsis,
                             lineHeight = 24.sp)
                    }
                }
                //Clickable Rows
                Column {
                    //Manage Profile
                    Divider(modifier = Modifier.height(1.dp))
                    Column(modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp , end = 8.dp , top = 8.dp , bottom = 8.dp),
                           verticalArrangement = Arrangement.Center,
                           horizontalAlignment = Alignment.CenterHorizontally)
                    {
                        Row(verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier
                                    .selectable(
                                            selected = false , onClick = onManageProfileClick , role = Role.Button
                                               )
                                    .fillMaxWidth())
                        {
                            Icon(imageVector = Icons.Outlined.Edit ,
                                 contentDescription = "",
                                 modifier = Modifier.padding(16.dp),
                                 tint = MaterialTheme.colors.onPrimary)
                            Text(text = "Manage Profile",
                                 style = MaterialTheme.typography.subtitle1,
                                 fontSize = 16.sp,
                                 color = MaterialTheme.colors.onPrimary)
                        }
                    }
                    //Change password
                    Divider(modifier = Modifier.height(1.dp))
                    Column(modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp , end = 8.dp , top = 8.dp , bottom = 8.dp),
                           verticalArrangement = Arrangement.Center,
                           horizontalAlignment = Alignment.CenterHorizontally)
                    {
                        Row(verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier
                                    .selectable(
                                            selected = false , onClick = onChangePasswordClick , role = Role.Button
                                               )
                                    .fillMaxWidth())
                        {
                            Icon(imageVector = Icons.Outlined.LockReset ,
                                 contentDescription = "",
                                 modifier = Modifier.padding(16.dp),
                                 tint = MaterialTheme.colors.onPrimary)
                            Text(text = "Change Password",
                                 style = MaterialTheme.typography.subtitle1,
                                 fontSize = 16.sp,
                                 color = MaterialTheme.colors.onPrimary)
                        }
                    }
                    //Transaction List
                    Divider(modifier = Modifier.height(1.dp))
                    Column(modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp , end = 8.dp , top = 8.dp , bottom = 8.dp),
                           verticalArrangement = Arrangement.Center,
                           horizontalAlignment = Alignment.CenterHorizontally)
                    {
                        Row(verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier
                                    .selectable(
                                            selected = false , onClick = onTransactionClick , role = Role.Button
                                               )
                                    .fillMaxWidth())
                        {
                            Icon(imageVector = Icons.Outlined.ListAlt ,
                                 contentDescription = "",
                                 modifier = Modifier.padding(16.dp),
                                 tint = MaterialTheme.colors.onPrimary)
                            Text(text = "My Transactions",
                                 style = MaterialTheme.typography.subtitle1,
                                 fontSize = 16.sp,
                                 color = MaterialTheme.colors.onPrimary)
                        }
                    }
                    //Offers List
                    Divider(modifier = Modifier.height(1.dp))
                    Column(modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp , end = 8.dp , top = 8.dp , bottom = 8.dp),
                           verticalArrangement = Arrangement.Center,
                           horizontalAlignment = Alignment.CenterHorizontally)
                    {
                        Row(verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier
                                    .selectable(
                                            selected = false , onClick = onOffersClick , role = Role.Button
                                               )
                                    .fillMaxWidth())
                        {
                            Icon(imageVector = Icons.Outlined.LocalOffer,
                                 contentDescription = "",
                                 modifier = Modifier.padding(16.dp),
                                 tint = MaterialTheme.colors.onPrimary)
                            Text(text = "Offers History",
                                 style = MaterialTheme.typography.subtitle1,
                                 fontSize = 16.sp,
                                 color = MaterialTheme.colors.onPrimary)
                        }
                    }
                    //Commissions List
                    Divider(modifier = Modifier.height(1.dp))
                    Column(modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp , end = 8.dp , top = 8.dp , bottom = 8.dp),
                           verticalArrangement = Arrangement.Center,
                           horizontalAlignment = Alignment.CenterHorizontally)
                    {
                        Row(verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier
                                    .selectable(
                                            selected = false , onClick = onCommissionsClick , role = Role.Button
                                               )
                                    .fillMaxWidth())
                        {
                            Icon(imageVector = Icons.Outlined.Payments ,
                                 contentDescription = "",
                                 modifier = Modifier.padding(16.dp),
                                 tint = MaterialTheme.colors.onPrimary)
                            Text(text = "Commissions History",
                                 style = MaterialTheme.typography.subtitle1,
                                 fontSize = 16.sp,
                                 color = MaterialTheme.colors.onPrimary)
                        }
                    }
                    //About App
                    Divider(modifier = Modifier.height(1.dp))
                    Column(modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp , end = 8.dp , top = 8.dp , bottom = 8.dp),
                           verticalArrangement = Arrangement.Center,
                           horizontalAlignment = Alignment.CenterHorizontally)
                    {
                        Row(verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier
                                    .selectable(
                                            selected = false ,
                                            onClick = onAboutAppClick ,
                                            role = Role.Button)
                                    .fillMaxWidth())
                        {
                            Icon(imageVector = Icons.Outlined.Info ,
                                 contentDescription = "",
                                 modifier = Modifier.padding(16.dp),
                                 tint = MaterialTheme.colors.onPrimary)
                            Text(text = "About App",
                                 style = MaterialTheme.typography.subtitle1,
                                 fontSize = 16.sp,
                                 color = MaterialTheme.colors.onPrimary)
                        }
                    }
                    Divider(modifier = Modifier.height(1.dp))
                    Column(modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp , end = 8.dp , top = 8.dp , bottom = 8.dp) ,
                           verticalArrangement = Arrangement.Center ,
                           horizontalAlignment = Alignment.CenterHorizontally)
                    {
                        Row(verticalAlignment = Alignment.CenterVertically ,
                            horizontalArrangement = Arrangement.Start ,
                            modifier = Modifier
                                    .selectable(
                                            selected = false ,
                                            onClick = onLogoutClick ,
                                            role = Role.Button)
                                    .fillMaxWidth())
                        {
                            Icon(imageVector = Icons.Outlined.Logout ,
                                 contentDescription = "" ,
                                 modifier = Modifier.padding(16.dp) ,
                                 tint = Color.Red)
                            Text(text = "Logout" ,
                                 style = MaterialTheme.typography.subtitle1 ,
                                 fontSize = 16.sp ,
                                 color = Color.Red)
                        }
                    }
                }
            }
        }
    }
}