package com.profilaksis.profilaksis.ui.screen.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
) {
    val percentage = 80f
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
            ProfileContent(percentage, modifier = Modifier.fillMaxSize(), onCLick = { /* TODO */ })
        }
    )
}

@Composable
fun ProfileContent(percentage: Float, modifier: Modifier, onCLick: () -> Unit) {
    val settings = SettingsData.settings
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 75.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Greeting(
            name = "Riyan",
            url = "https://images.unsplash.com/photo-1633332755192-727a05c4013d?q=80&w=2960&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            greeting = "Morning...",
            icon = false,
            modifier = Modifier.height(50.dp)
        )
        Spacer(modifier = Modifier.height(15.dp))
        BarResult(
            title = "Your Health",
            percent = percentage,
            onClick = { /* TODO */ }
        )
        Spacer(modifier = Modifier.height(15.dp))
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 25.dp)
        ) {
            items(settings) { setting ->
                SettingsCard(
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