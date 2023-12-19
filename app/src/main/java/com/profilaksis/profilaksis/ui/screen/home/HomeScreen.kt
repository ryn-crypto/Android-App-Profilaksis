package com.profilaksis.profilaksis.ui.screen.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.profilaksis.profilaksis.data.model.ResponseArticleItem
import com.profilaksis.profilaksis.data.model.UserLogin
import com.profilaksis.profilaksis.di.Injection
import com.profilaksis.profilaksis.ui.components.ArticleCard
import com.profilaksis.profilaksis.ui.components.Greeting
import com.profilaksis.profilaksis.ui.screen.ViewModelFactory
import com.profilaksis.profilaksis.utils.Greeting

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    userData: UserLogin,
    context: Context,
) {

    val uiState by viewModel.uiState.collectAsState()
    var isLoading by remember { mutableStateOf(false) }
    var articleData by remember { mutableStateOf(emptyList<ResponseArticleItem>()) }

    LaunchedEffect(Unit) {
        viewModel.getDataArticle()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
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
                        modifier = Modifier.height(60.dp)
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
        if (isLoading) {
            LazyColumn(
                content = {
                    items(3) {
                        ArticleCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
                            url = "",
                            title = "",
                            author = "",
                            onClick = {}
                        )
                    }
                }
            )
        } else {
            LazyColumn(
                modifier = Modifier.padding(horizontal = 20.dp),
            ) {
                items(articleData) { article ->
                    ArticleCard(
                        url = article.imageUrl!!,
                        title = article.title!!,
                        author = article.tags!!,
                        onClick = {
                            if (article.sourceUrl != null){
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.sourceUrl))
                                context.startActivity(intent)
                            } else {
                                Toast.makeText(context, "Sorry, this article is not available", Toast.LENGTH_SHORT).show()
                            }
                        }

                    )
                }
            }
        }
    }

    LaunchedEffect(uiState) {
        when (val currentState = uiState) {
            is HomeUiState.Loading -> {
                isLoading = true
            }

            is HomeUiState.Success -> {
                articleData = currentState.result
                isLoading = false

            }

            is HomeUiState.Error -> {
                isLoading = false
                Toast.makeText(
                    null,
                    currentState.errorMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        userData = UserLogin(
            username = "test",
            email = "",
            id = 1,
            role = "",
            token = "",
            avatar = "https://i.pravatar.cc/150?img=3",
        ),
        context = LocalContext.current
    )
}