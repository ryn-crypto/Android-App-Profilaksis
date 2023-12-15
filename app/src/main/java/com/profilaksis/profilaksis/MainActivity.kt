package com.profilaksis.profilaksis

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.profilaksis.profilaksis.container.ProfilaksisApp
import com.profilaksis.profilaksis.container.ScreenContainer
import com.profilaksis.profilaksis.ui.theme.ProfilaksisTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfilaksisTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppContainer()
                }
            }
        }
    }
}

@Composable
fun AppContainer() {
    var isBlank by remember { mutableStateOf(false) }
    var parameter: String by remember { mutableStateOf("") }

    if (!isBlank) {
        ProfilaksisApp(
            clickFab = {
                isBlank = true
                parameter = it
            }
        )
    } else {
        ScreenContainer(parameter, clickBack = {
            isBlank = false
        })
    }
}


@Preview(showBackground = true)
@Composable
fun HomePreview() {
    ProfilaksisTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ProfilaksisApp()
        }
    }
}