package com.profilaksis.profilaksis.ui.screen.result

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.profilaksis.profilaksis.data.model.ResponseArticleItem
import com.profilaksis.profilaksis.data.model.ResultData
import com.profilaksis.profilaksis.di.Injection
import com.profilaksis.profilaksis.ui.components.ArticleCard
import com.profilaksis.profilaksis.ui.components.ResultCard
import com.profilaksis.profilaksis.ui.screen.ViewModelFactory
import com.profilaksis.profilaksis.utils.formatDate
import java.util.Date

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(
    viewModel: ResultViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    backScreen: String,
    parameter: ResultData,
    onClick: () -> Unit = {},
    back: (String) -> Unit = {},
) {
    val backButton = {back(backScreen)}
    BackHandler(onBack = backButton)

    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Result")
                    }
                },
                modifier = Modifier.background(MaterialTheme.colorScheme.primary)
            )
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(60.dp))
                parameter.prediction?.let { it1 ->
                    ResultCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp, horizontal = 20.dp),
                        percentage = it1,
                        date = "formatDate(parameter.date!!)",
                        userName = "parameter.userName",
                        description = parameter.keterangan,
                        onclick = onClick,
                        back = { back(backScreen) }
                    )
                }
                Spacer(modifier = Modifier.padding(5.dp))
                when (uiState) {
                    is ResultUiState.Loading -> {
                        Skeleton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                        )
                    }

                    is ResultUiState.Success -> {
                        Article(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 5.dp, horizontal = 20.dp),
                            article = (uiState as ResultUiState.Success).result,
                            onClick = onClick
                        )
                    }

                    is ResultUiState.Error -> {
                        Text(
                            text = (uiState as ResultUiState.Error).errorMessage,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(20.dp)
                        )
                    }

                    else -> {}
                }
            }
        }
    )
}

@Composable
fun Skeleton(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Article",
            style = MaterialTheme.typography.titleMedium
        )
        LazyColumn(content = {
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
        })
    }
}

@Composable
fun Article(
    modifier: Modifier = Modifier,
    article: List<ResponseArticleItem>,
    onClick: () -> Unit = {},
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Article",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.padding(10.dp))
        LazyColumn(content = {
            items(article.size) { index ->
                ArticleCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    url = article[index].imageUrl!!,
                    title = article[index].title!!,
                    author = article[index].tags!!,
                    onClick = onClick
                )
            }
        })
    }
}

@Preview(showBackground = true)
@Composable
fun ResultPreview() {
    ResultScreen(
        parameter = ResultData(
            "1",
            "username",
            90f,
            Date(),
        ),
        backScreen = "heart"
    )
}
