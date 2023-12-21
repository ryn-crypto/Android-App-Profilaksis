package com.profilaksis.profilaksis.ui.screen.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.profilaksis.profilaksis.R
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
                .height(170.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
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
                        .padding(top = 10.dp)
                ) {
                    Greeting(
                        name = userData.username,
                        url = userData.avatar,
                        greeting = Greeting.getGreeting(),
                        icon = true,
                        modifier = Modifier.size(60.dp)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                ElevatedCard(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 45.dp)
                        .wrapContentHeight(Alignment.Bottom)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(
                                10.dp
                            )
                        ),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 3.dp, horizontal = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(4.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painterResource(
                                    R.drawable.icon1
                                ),
                                contentDescription = "",
                                modifier = Modifier.size(30.dp)
                            )
                            Text(
                                text = "Accurate",
                                fontSize = 10.sp,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                        Column(
                            modifier = Modifier
                                .padding(4.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painterResource(R.drawable.icon2),
                                contentDescription = "",
                                modifier = Modifier.size(30.dp)
                            )
                            Text(
                                text = "Everywhere",
                                fontSize = 10.sp,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                        Column(
                            modifier = Modifier
                                .padding(4.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painterResource(R.drawable.icon3),
                                contentDescription = "",
                                modifier = Modifier.size(30.dp)
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
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Articles may interest you",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(10.dp),
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
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {
                items(articleData) { article ->
                    ArticleCard(
                        url = article.imageUrl!!,
                        title = article.title!!,
                        author = article.author ?: "Unknown",
                        onClick = {
                            if (article.sourceUrl != null) {
                                val intent =
                                    Intent(Intent.ACTION_VIEW, Uri.parse(article.sourceUrl))
                                context.startActivity(intent)
                            } else {
                                Toast.makeText(
                                    context,
                                    "Sorry, this article is not available",
                                    Toast.LENGTH_SHORT
                                ).show()
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