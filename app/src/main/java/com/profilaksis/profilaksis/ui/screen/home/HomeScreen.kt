package com.profilaksis.profilaksis.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.profilaksis.profilaksis.data.model.UserLogin
import com.profilaksis.profilaksis.ui.components.ArticleCard
import com.profilaksis.profilaksis.ui.components.Greeting
import com.profilaksis.profilaksis.utils.Greeting

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    userData: UserLogin,
) {
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
                    .background(
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                        shape = RoundedCornerShape(
                            bottomStart = 30.dp,
                            bottomEnd = 30.dp
                        )
                    )
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 50.dp)
                ) {
                    Greeting(
                        name = userData.username,
                        url = userData.avatar,
                        greeting = Greeting.getGreeting(),
                        icon = true,
                        modifier = Modifier.height(50.dp)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp)
                        .wrapContentHeight(Alignment.Bottom)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(
                                10.dp
                            )
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    Row(
                        modifier = Modifier
                            .wrapContentSize(Alignment.Center),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .wrapContentSize(Alignment.Center)
                                .padding(7.dp)
                                .border(
                                    width = 0.5.dp,
                                    color = Color.Gray,
                                    shape = RoundedCornerShape(
                                        topStart = MaterialTheme.shapes.medium.topStart,
                                        topEnd = MaterialTheme.shapes.medium.topEnd,
                                        bottomStart = MaterialTheme.shapes.medium.bottomStart,
                                        bottomEnd = MaterialTheme.shapes.medium.bottomEnd
                                    )
                                )
                                .background(
                                    color = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.8f),
                                    shape = RoundedCornerShape(
                                        topStart = MaterialTheme.shapes.medium.topStart,
                                        topEnd = MaterialTheme.shapes.medium.topEnd,
                                        bottomStart = MaterialTheme.shapes.medium.bottomStart,
                                        bottomEnd = MaterialTheme.shapes.medium.bottomEnd
                                    )
                                ),
                            contentAlignment = Alignment.Center,
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(4.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {

                                Icon(
                                    imageVector = Icons.Default.ThumbUp,
                                    contentDescription = "value",
                                    tint = Color.Gray,
                                    modifier = Modifier.size(17.dp)
                                )
                                Text(
                                    text = "Accurate",
                                    fontSize = 10.sp,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.Gray,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .wrapContentSize(Alignment.Center)
                                .padding(7.dp)
                                .border(
                                    width = 0.5.dp,
                                    color = Color.Gray,
                                    shape = RoundedCornerShape(
                                        topStart = MaterialTheme.shapes.medium.topStart,
                                        topEnd = MaterialTheme.shapes.medium.topEnd,
                                        bottomStart = MaterialTheme.shapes.medium.bottomStart,
                                        bottomEnd = MaterialTheme.shapes.medium.bottomEnd
                                    )
                                )
                                .background(
                                    color = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.8f),
                                    shape = RoundedCornerShape(
                                        topStart = MaterialTheme.shapes.medium.topStart,
                                        topEnd = MaterialTheme.shapes.medium.topEnd,
                                        bottomStart = MaterialTheme.shapes.medium.bottomStart,
                                        bottomEnd = MaterialTheme.shapes.medium.bottomEnd
                                    )
                                ),
                            contentAlignment = Alignment.Center,
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(4.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {

                                Icon(
                                    imageVector = Icons.Default.LocationOn,
                                    contentDescription = "value",
                                    tint = Color.Gray,
                                    modifier = Modifier.size(17.dp)
                                )
                                Text(
                                    text = "Everywhere",
                                    fontSize = 10.sp,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.Gray,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .wrapContentSize(Alignment.Center)
                                .padding(7.dp)
                                .border(
                                    width = 0.5.dp,
                                    color = Color.Gray,
                                    shape = RoundedCornerShape(
                                        topStart = MaterialTheme.shapes.medium.topStart,
                                        topEnd = MaterialTheme.shapes.medium.topEnd,
                                        bottomStart = MaterialTheme.shapes.medium.bottomStart,
                                        bottomEnd = MaterialTheme.shapes.medium.bottomEnd
                                    )
                                )
                                .background(
                                    color = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.8f),
                                    shape = RoundedCornerShape(
                                        topStart = MaterialTheme.shapes.medium.topStart,
                                        topEnd = MaterialTheme.shapes.medium.topEnd,
                                        bottomStart = MaterialTheme.shapes.medium.bottomStart,
                                        bottomEnd = MaterialTheme.shapes.medium.bottomEnd
                                    )
                                ),
                            contentAlignment = Alignment.Center,
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(4.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {

                                Icon(
                                    imageVector = Icons.Default.Call,
                                    contentDescription = "value",
                                    tint = Color.Gray,
                                    modifier = Modifier.size(17.dp)
                                )
                                Text(
                                    text = "Consult",
                                    fontSize = 10.sp,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.Gray,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }
                        }

                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Articles may interest you",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(10.dp),
            textAlign = TextAlign.Center
        )
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 20.dp),
            content = {
                item {
                    ArticleCard(
                        url = "https://images.unsplash.com/photo-1633332755192-727a05c4013d?q=80&w=2960&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        title = "How to get a good night's sleep",
                        author = "Ryan",
                    )
                }
                item {
                    ArticleCard(
                        url = "https://images.unsplash.com/photo-1633332755192-727a05c4013d?q=80&w=2960&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        title = "How to get a good night's sleep",
                        author = "Ryan",
                    )
                }
                item {
                    ArticleCard(
                        url = "https://images.unsplash.com/photo-1633332755192-727a05c4013d?q=80&w=2960&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        title = "How to get a good night's sleep",
                        author = "Ryan",
                    )
                }
                item {
                    ArticleCard(
                        url = "https://images.unsplash.com/photo-1633332755192-727a05c4013d?q=80&w=2960&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        title = "How to get a good night's sleep",
                        author = "Ryan",
                    )
                }
            })
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        userData = UserLogin(
            id = 1,
            username = "Riyan",
            email = "",
            role = "",
            avatar = "https://images.unsplash.com/photo-1633332755192-727a05c4013d?q=80&w=2960&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            token = " "
        )
    )
}