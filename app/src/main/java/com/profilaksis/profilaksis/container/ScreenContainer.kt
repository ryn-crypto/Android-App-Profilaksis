package com.profilaksis.profilaksis.container

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.profilaksis.profilaksis.data.model.ResultData
import com.profilaksis.profilaksis.data.model.UserLogin
import com.profilaksis.profilaksis.ui.screen.adds.AddsScreen
import com.profilaksis.profilaksis.ui.screen.authorization.login.LoginScreen
import com.profilaksis.profilaksis.ui.screen.authorization.register.RegisterPage
import com.profilaksis.profilaksis.ui.screen.consultation.Consultation
import com.profilaksis.profilaksis.ui.screen.diabetes.DiabetesScreen
import com.profilaksis.profilaksis.ui.screen.heart.HeartScreen
import com.profilaksis.profilaksis.ui.screen.result.ResultScreen

@Composable
fun ScreenContainer(id: String, clickBack: () -> Unit, userData: (UserLogin) -> Unit, token: UserLogin?) {

    var currentId by remember { mutableStateOf(id) }
    var isPremium by remember { mutableStateOf(true) }
    val snackbarHostState = remember { SnackbarHostState() }
    var parameter by remember { mutableStateOf(ResultData()) }
    var screenBack by remember { mutableStateOf("") }

    if (!isPremium) {
        AddsScreen(onClickExit = {
            isPremium = true
        })
    }

    if (isPremium) {
        when (currentId) {
            "Login" -> {
                LaunchedEffect(key1 = "Auth") {
                    snackbarHostState.showSnackbar("Please login")
                }
                LoginScreen(
                    loginSuccess = {
                        userData(it)
                    },
                    snackbarHostState = snackbarHostState,
                    onRegisterClick = {
                        currentId = "Register"
                    }
                )
            }

            "Register" -> {
                LaunchedEffect(key1 = "Auth") {
                    snackbarHostState.showSnackbar("Please Register")
                }
                RegisterPage(
                    onRegisterSuccess = {
                        currentId = "Login"
                    },
                    snackbarHostState = snackbarHostState,
                    onBackClick = {
                        currentId = "Login"
                    }
                )
            }

            "heart" -> {
                HeartScreen(
                    clickBack = clickBack,
                    snackbarHostState = snackbarHostState,
                    clickSubmit = {
                        parameter = it
                        currentId = "result"
                        screenBack = "heart"
                    },
                    token = token!!
                )
            }

            "diabetes" -> {
                DiabetesScreen(clickBack = clickBack,
                    snackbarHostState = snackbarHostState,
                    clickSubmit = {
                        parameter = it
                        currentId = "result"
                        screenBack = "diabetes"
                    },
                    token = token!!
                )
            }

            "result" -> {
                ResultScreen(
                    parameter = parameter,
                    onClick = {
                        currentId = "consultation"
                        parameter = ResultData(null, null, null)
                    },
                    backScreen = screenBack,
                    back = {
                        currentId = it
                        parameter = ResultData(null ,null, null)
                    }
                )
            }

            "consultation" -> {
                Log.e("test123 load cons", "param $parameter, id $currentId")
                Consultation(clickBack = clickBack, clickReset = {
                    parameter = ResultData(null, null, null)
                    currentId = ""
                    Log.e("test123", "param $parameter, id $currentId")
                })
            }
        }
    }

    SnackbarHost(
        modifier = Modifier.fillMaxSize(),
        hostState = snackbarHostState,
    )
}