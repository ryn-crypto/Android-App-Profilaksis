package com.profilaksis.profilaksis.ui.screen.history

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.profilaksis.profilaksis.data.model.ResultsItem
import com.profilaksis.profilaksis.data.model.UserLogin
import com.profilaksis.profilaksis.di.Injection
import com.profilaksis.profilaksis.ui.components.HistoryCard
import com.profilaksis.profilaksis.ui.screen.ViewModelFactory
import com.profilaksis.profilaksis.utils.formatDate

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    viewModel: HistoryViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    token: UserLogin?,
    context: Context,
) {

    val uiState by viewModel.uiState.collectAsState()
    var isLoading by remember { mutableStateOf(false) }
    var dataHistory by remember { mutableStateOf(emptyList<ResultsItem>()) }

    LaunchedEffect(Unit) {
        viewModel.getDataHistory(token!!.token)
    }

    Scaffold(
        modifier = modifier.padding(bottom = 50.dp),
        topBar = {
            TopAppBar(
                title = { Text(text = "History") },
                modifier = Modifier.background(MaterialTheme.colorScheme.primary).shadow(1.dp),
            )
        },
        content = {
            if (isLoading) {
                Text(text = "Loading...")
            } else {
                HistoryContent(modifier = modifier, dataHistory = dataHistory)
            }
        }
    )

    LaunchedEffect(uiState) {
        when (val currentState = uiState) {
            is HistoryUiState.Loading -> {
                isLoading = true
            }

            is HistoryUiState.Success -> {
                isLoading = false
                dataHistory = currentState.result.reversed()
            }

            is HistoryUiState.Error -> {
                isLoading = false
                Toast.makeText(
                    context,
                    currentState.errorMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}

@Composable
fun HistoryContent(
    modifier: Modifier = Modifier,
    dataHistory: List<ResultsItem>,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 70.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = modifier
                .padding(bottom = 20.dp),
            content = {
            items(dataHistory.size) { index ->
                HistoryCard(
                    percentage = dataHistory[index].predictionResult!!,
                    date = formatDate(dataHistory[index].createdAt!!),
                    describe = dataHistory[index].keterangan!!
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        })
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryScreenPreview() {
    HistoryScreen(
        token = UserLogin(1, "token", "refresh_token", "email", "name", "username"),
        context = LocalContext.current
    )
}