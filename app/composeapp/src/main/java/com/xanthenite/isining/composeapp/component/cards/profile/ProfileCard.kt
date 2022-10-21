package com.xanthenite.isining.composeapp.component.cards.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.text.font.FontWeight
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
        onManageProfileClick : () -> Unit)
{
    Column(modifier = Modifier.verticalScroll(rememberScrollState()))
    {
        Card(
                shape = RoundedCornerShape(10.dp),
                backgroundColor = MaterialTheme.colors.surface,
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp , vertical = 8.dp)
                        .wrapContentHeight(),
                elevation = 2.dp)
        {
            Column {
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
                                Text(text = data.number?:"No number provided",
                                     style = MaterialTheme.typography.subtitle2,
                                     fontSize = 16.sp,)
                            }
                        }
                    }
                }
                Column {
                    Divider(modifier = Modifier.height(1.dp))
                    Row(verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.padding(8.dp))
                    {
                        Icon(
                                imageVector = Icons.Outlined.PersonOutline ,
                                contentDescription = "",
                                modifier = Modifier.padding(16.dp),
                                tint = MaterialTheme.colors.onPrimary)
                        TextButton(onClick = { onManageProfileClick() }) {
                            Text(text = "Manage Profile",
                                 style = MaterialTheme.typography.subtitle1,
                                 fontSize = 16.sp,
                                 color = MaterialTheme.colors.onPrimary)
                        }
                    }
                    Divider(modifier = Modifier.height(1.dp))
                    Row(verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.padding(8.dp))
                    {
                        Icon(
                                imageVector = Icons.Outlined.LockReset ,
                                contentDescription = "",
                                modifier = Modifier.padding(16.dp),
                                tint = MaterialTheme.colors.onPrimary)
                        TextButton(onClick = { onChangePasswordClick() }) {
                            Text(text = "Change Password",
                                 style = MaterialTheme.typography.subtitle1,
                                 fontSize = 16.sp,
                                 color = MaterialTheme.colors.onPrimary)
                        }
                    }
                    Divider(modifier = Modifier.height(1.dp))
                    Row(verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.padding(8.dp))
                    {
                        Icon(
                                imageVector = Icons.Outlined.ListAlt ,
                                contentDescription = "",
                                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                                tint = MaterialTheme.colors.onPrimary)
                        TextButton(onClick = { onTransactionClick() }) {
                            Text(text = "Transaction List",
                                 style = MaterialTheme.typography.subtitle1,
                                 fontSize = 16.sp,
                                 color = MaterialTheme.colors.onPrimary)
                        }
                    }
                    Divider(modifier = Modifier.height(1.dp))
                    Row(verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.padding(8.dp))
                    {
                        Icon(
                                imageVector = Icons.Outlined.Info,
                                contentDescription = "",
                                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                                tint = MaterialTheme.colors.onPrimary)
                        TextButton(onClick = { onAboutAppClick() }) {
                            Text(text = "About App",
                                 style = MaterialTheme.typography.subtitle1,
                                 fontSize = 16.sp,
                                 color = MaterialTheme.colors.onPrimary)
                        }
                    }
                    Divider(modifier = Modifier.height(1.dp))
                    Row(verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                                .padding(8.dp)
                                .clickable { onLogoutClick() })
                    {
                        Icon(
                                imageVector = Icons.Outlined.Logout ,
                                contentDescription = "",
                                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                                tint = Color.Red)
                        TextButton(onClick = { onLogoutClick() }) {
                            Text(text = "Logout",
                                 style = MaterialTheme.typography.subtitle1,
                                 color = Color.Red,
                                 fontSize = 16.sp)
                        }
                    }
                }
            }
        }
    }
}