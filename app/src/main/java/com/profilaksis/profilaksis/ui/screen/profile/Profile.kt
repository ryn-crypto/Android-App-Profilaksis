package com.profilaksis.profilaksis.ui.screen.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.profilaksis.profilaksis.data.local.SettingsData
import com.profilaksis.profilaksis.di.Injection
import com.profilaksis.profilaksis.ui.components.BarResult
import com.profilaksis.profilaksis.ui.components.Greeting
import com.profilaksis.profilaksis.ui.components.SettingsCard
import com.profilaksis.profilaksis.ui.screen.ViewModelFactory
import com.profilaksis.profilaksis.utils.formatDate

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
) {
    val uiState by viewModel.uiState.collectAsState()
    val greeting = com.profilaksis.profilaksis.utils.Greeting.getGreeting()

    LaunchedEffect(Unit) {
        viewModel.loadData()
    }

    Scaffold(
        topBar = {
            Surface(
                shadowElevation = 4.dp,
                color = MaterialTheme.colorScheme.secondary
            ) {
                TopAppBar(
                    title = { Text(text = "Profile") }
                )
            }
        },
        content = {
            ProfileContent(
                modifier = Modifier.fillMaxSize(),
                onCLick = { /* TODO */ },
                greeting = greeting,
                uiState = uiState
            )
        }
    )
}

@Composable
fun ProfileContent(
    modifier: Modifier,
    onCLick: () -> Unit,
    greeting: String,
    uiState: ProfileUiState
) {
    val settings = SettingsData.settings
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 90.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (uiState) {
            is ProfileUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            is ProfileUiState.Success -> {
                val lasHistory = uiState.lastStory
                val profile = uiState.profile
                if (profile != null) {
                    Greeting(
                        name = profile.username,
                        url = profile.avatar,
                        greeting = greeting,
                        icon = false,
                        modifier = Modifier.height(50.dp)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                if (lasHistory != null) {
                    BarResult(
                        title = formatDate(lasHistory.createdAt),
                        type = lasHistory.type,
                        percent = lasHistory.predictionResult,
                        onClick = { Unit }
                    )
                }
            }

            is ProfileUiState.Error -> {
                val errorMessage = uiState.errorMessage
                // Show error message
                Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, bottom = 70.dp)
        ) {
            items(settings) { setting ->
                SettingsCard(
                    modifier = Modifier.padding(vertical = 5.dp),
                    onClick = { onCLick },
                    icon = setting.icon,
                    title = setting.title
                )
            }
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_5", backgroundColor = 0xFFFFFFFF)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}