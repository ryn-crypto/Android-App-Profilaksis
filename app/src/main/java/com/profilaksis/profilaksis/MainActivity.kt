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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.profilaksis.profilaksis.container.ProfilaksisApp
import com.profilaksis.profilaksis.container.ScreenContainer
import com.profilaksis.profilaksis.data.model.UserLogin
import com.profilaksis.profilaksis.ui.screen.authorization.AuthViewModel
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
    var dataUser: UserLogin? by remember { mutableStateOf(UserLogin("", 1, "", "", "", "")) }

    val authViewModel: AuthViewModel = viewModel()
    dataUser = authViewModel.getLoggedInfo()

    if (dataUser == null) {
        ScreenContainer(
            id = "Login",
            clickBack = {
                isBlank = true
            },
            userData = {
                authViewModel.saveLoginInfo(it)
                dataUser = authViewModel.getLoggedInfo()
                isBlank = false
            }
        )
    } else {
        if (!isBlank) {
            ProfilaksisApp(
                clickFab = {
                    isBlank = true
                    parameter = it
                },
                userData = dataUser!!,
                onClick = {
                    authViewModel.clearLoginInfo()
                    isBlank = true
                }
            )
        } else {
            ScreenContainer(
                parameter, clickBack = {
                    isBlank = false
                },
                userData = {
                    authViewModel.saveLoginInfo(it)
                }
            )
        }
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
            ProfilaksisApp(
                clickFab = {},
                userData = UserLogin(
                    username = "test",
                    email = "",
                    avatar = "",
                    id = 1,
                    role = "",
                    token = ""
                )
            )
        }
    }
}