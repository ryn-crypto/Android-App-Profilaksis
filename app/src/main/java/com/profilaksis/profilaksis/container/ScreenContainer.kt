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
import com.profilaksis.profilaksis.data.model.ResponseResult
import com.profilaksis.profilaksis.ui.screen.adds.AddsScreen
import com.profilaksis.profilaksis.ui.screen.consultation.Consultation
import com.profilaksis.profilaksis.ui.screen.diabetes.DiabetesScreen
import com.profilaksis.profilaksis.ui.screen.heart.HeartScreen
import com.profilaksis.profilaksis.ui.screen.result.ResultScreen

@Composable
fun ScreenContainer(id: String, clickBack: () -> Unit) {

    var currentId by remember { mutableStateOf(id) }
    var isPremium by remember { mutableStateOf(true) }
    val snackbarHostState = remember { SnackbarHostState() }
    var parameter by remember { mutableStateOf(ResponseResult()) }

    if (!isPremium) {
        AddsScreen(onClickExit = {
            isPremium = true
        })
    }

    if (isPremium) {

        when (currentId) {
            "heart" -> {
                HeartScreen(clickBack = clickBack,
                    snackbarHostState = snackbarHostState,
                    clickSubmit = {
                        parameter = it
                        currentId = "result"
                    })
            }

            "diabetes" -> {
                DiabetesScreen(clickBack = clickBack)
            }

            "result" -> {
                Log.e("test123 load res", "param $parameter, id $currentId")
                ResultScreen(parameter = parameter, onClick = {
                    Log.e("test123 res", "param $parameter, id $currentId")
                    currentId = "consultation"
                    parameter = ResponseResult(null)
                })
            }

            "consultation" -> {
                Log.e("test123 load cons", "param $parameter, id $currentId")
                Consultation(clickBack = clickBack, clickReset = {
                    parameter = ResponseResult(null)
                    currentId = ""
                    Log.e("test123", "param $parameter, id $currentId")
                })
            }
        }
    }

    SnackbarHost(
        hostState = snackbarHostState, modifier = Modifier.fillMaxSize()
    )
}