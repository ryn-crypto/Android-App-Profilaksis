package com.profilaksis.profilaksis.ui.screen.heart

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.elevatedCardColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.profilaksis.profilaksis.data.alcoholData
import com.profilaksis.profilaksis.data.bloodPressureData
import com.profilaksis.profilaksis.data.cholesterolData
import com.profilaksis.profilaksis.data.diabetesData
import com.profilaksis.profilaksis.data.exerciseData
import com.profilaksis.profilaksis.data.fruitData
import com.profilaksis.profilaksis.data.genderData
import com.profilaksis.profilaksis.data.model.ResultData
import com.profilaksis.profilaksis.data.model.UserLogin
import com.profilaksis.profilaksis.data.radioButtonStatus
import com.profilaksis.profilaksis.data.remote.requestdata.PredictRequestBody
import com.profilaksis.profilaksis.data.smokingData
import com.profilaksis.profilaksis.data.strokeData
import com.profilaksis.profilaksis.data.vegetableData
import com.profilaksis.profilaksis.data.walkData
import com.profilaksis.profilaksis.di.Injection
import com.profilaksis.profilaksis.ui.components.CustomRadioButton
import com.profilaksis.profilaksis.ui.screen.ViewModelFactory
import com.profilaksis.profilaksis.ui.screen.diabetes.AgeTextField
import com.profilaksis.profilaksis.ui.screen.diabetes.IntTextField

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeartScreen(
    viewModel: HeartViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    clickBack: () -> Unit,
    snackbarHostState: SnackbarHostState,
    clickSubmit: (ResultData) -> Unit,
    token: UserLogin,
) {
    val uiState by viewModel.uiState.collectAsState()
    var isLoading = false

    val ageStatus = remember { mutableStateOf("") }
    val height = remember { mutableIntStateOf(0) }
    val weight = remember { mutableIntStateOf(0) }
    val bmi = weight.intValue * ((height.intValue / 100) * (height.intValue / 100))
    val genderStatus = radioButtonStatus.genderStatus
    val diabetesStatus = radioButtonStatus.diabetesStatus
    val strokeStatus = radioButtonStatus.strokeStatus
    val bloodPressureStatus = radioButtonStatus.bloodPressureStatus
    val cholesterolStatus = radioButtonStatus.cholesterolStatus
    val smokingStatus = radioButtonStatus.smokingStatus
    val alcoholStatus = radioButtonStatus.alcoholStatus
    val exerciseStatus = radioButtonStatus.exerciseStatus
    val fruitStatus = radioButtonStatus.fruitStatus
    val vegetableStatus = radioButtonStatus.vegetableStatus
    val walkStatus = radioButtonStatus.walkStatus

    val gender = genderData.map { it.copy(selectedStatus = genderStatus) }
    val diabetes = diabetesData.map { it.copy(selectedStatus = diabetesStatus) }
    val stroke = strokeData.map { it.copy(selectedStatus = strokeStatus) }
    val blood = bloodPressureData.map { it.copy(selectedStatus = bloodPressureStatus) }
    val cholesterol = cholesterolData.map { it.copy(selectedStatus = cholesterolStatus) }
    val smoking = smokingData.map { it.copy(selectedStatus = smokingStatus) }
    val alcohol = alcoholData.map { it.copy(selectedStatus = alcoholStatus) }
    val exercise = exerciseData.map { it.copy(selectedStatus = exerciseStatus) }
    val fruit = fruitData.map { it.copy(selectedStatus = fruitStatus) }
    val vegetable = vegetableData.map { it.copy(selectedStatus = vegetableStatus) }
    val walk = walkData.map { it.copy(selectedStatus = walkStatus) }

    BackHandler(onBack = clickBack)
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = clickBack) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription = "Back",
                                modifier = Modifier.padding(end = 10.dp)
                            )
                        }
                        Text(text = "Cek Resiko Jantung")
                    }
                },
                modifier = Modifier.background(MaterialTheme.colorScheme.primary)
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.padding(top = 80.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.titleMedium,
                    text = "Silahkan isi data dibawah ini",
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.padding(10.dp))
                AgeTextField(
                    ageStatus,
                    "Umur",
                    rightLabel = "tahun",
                    onValueChange = { ageStatus.value = it })
                Spacer(modifier = Modifier.padding(5.dp))
                IntTextField(
                    height,
                    "Tinggi",
                    rightLabel = "Cm",
                    onValueChange = {
                        if (it == "") {
                            height.intValue = 0
                        } else {
                            height.intValue = it.toInt()
                        }
                    })
                Spacer(modifier = Modifier.padding(5.dp))
                IntTextField(
                    weight,
                    "Berat",
                    rightLabel = "Kg",
                    onValueChange = {
                        if (it == "") {
                            weight.intValue = 0
                        } else {
                            weight.intValue = it.toInt()
                        }
                    }
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Spacer(modifier = Modifier.padding(5.dp))
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    colors = elevatedCardColors(MaterialTheme.colorScheme.background),
                    elevation = CardDefaults.cardElevation(0.2.dp),
                ) {
                    Text(
                        text = "Jenis Kelamin",
                        modifier = Modifier.padding(top = 10.dp, start = 20.dp)
                    )
                    CustomRadioButton(gender, Modifier.padding(1.dp))
                }
                Spacer(modifier = Modifier.padding(5.dp))
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    colors = elevatedCardColors(MaterialTheme.colorScheme.background),
                    elevation = CardDefaults.cardElevation(0.2.dp),
                ) {
                    Text(
                        text = "Tekanan Darah",
                        modifier = Modifier.padding(top = 10.dp, start = 20.dp)
                    )
                    CustomRadioButton(blood, Modifier.padding(1.dp))
                }
                Spacer(modifier = Modifier.padding(5.dp))
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    colors = elevatedCardColors(MaterialTheme.colorScheme.background),
                    elevation = CardDefaults.cardElevation(0.2.dp),
                ) {
                    Text(
                        text = "Riwayat Kolesterol",
                        modifier = Modifier.padding(top = 10.dp, start = 20.dp)
                    )
                    CustomRadioButton(cholesterol, Modifier.padding(1.dp))
                }
                Spacer(modifier = Modifier.padding(5.dp))
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    colors = elevatedCardColors(MaterialTheme.colorScheme.background),
                    elevation = CardDefaults.cardElevation(0.2.dp),
                ) {
                    Text(
                        text = "Riwayat Stroke",
                        modifier = Modifier.padding(top = 10.dp, start = 20.dp)
                    )
                    CustomRadioButton(stroke, Modifier.padding(1.dp))
                }
                Spacer(modifier = Modifier.padding(5.dp))
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    colors = elevatedCardColors(MaterialTheme.colorScheme.background),
                    elevation = CardDefaults.cardElevation(0.2.dp),
                ) {
                    Text(
                        text = "Riwayat Diabetes",
                        modifier = Modifier.padding(top = 10.dp, start = 20.dp)
                    )
                    CustomRadioButton(diabetes, Modifier.padding(0.dp))
                }
                Spacer(modifier = Modifier.padding(5.dp))
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    colors = elevatedCardColors(MaterialTheme.colorScheme.background),
                    elevation = CardDefaults.cardElevation(0.2.dp),
                ) {
                    Text(
                        text = "Apakah Anda Merokok?",
                        modifier = Modifier.padding(top = 10.dp, start = 10.dp)
                    )
                    CustomRadioButton(smoking, Modifier.padding(1.dp))
                }
                Spacer(modifier = Modifier.padding(5.dp))
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    colors = elevatedCardColors(MaterialTheme.colorScheme.background),
                    elevation = CardDefaults.cardElevation(0.2.dp),
                ) {
                    Text(
                        text = "Apakah Anda Mengkonsumsi Alkohol?",
                        modifier = Modifier.padding(top = 10.dp, start = 20.dp)
                    )
                    CustomRadioButton(alcohol, Modifier.padding(1.dp))
                }
                Spacer(modifier = Modifier.padding(5.dp))
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    colors = elevatedCardColors(MaterialTheme.colorScheme.background),
                    elevation = CardDefaults.cardElevation(0.2.dp),
                ) {
                    Text(
                        text = "Apakah Anda Sering Berolahraga?",
                        modifier = Modifier.padding(top = 10.dp, start = 20.dp)
                    )
                    CustomRadioButton(exercise, Modifier.padding(1.dp))
                }
                Spacer(modifier = Modifier.padding(5.dp))
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    colors = elevatedCardColors(MaterialTheme.colorScheme.background),
                    elevation = CardDefaults.cardElevation(0.2.dp),
                ) {
                    Text(
                        text = "Apakah Anda Mengkonsumsi Buah Secara Rutin?",
                        modifier = Modifier.padding(top = 10.dp, start = 20.dp)
                    )
                    CustomRadioButton(fruit, Modifier.padding(1.dp))
                }
                Spacer(modifier = Modifier.padding(5.dp))
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    colors = elevatedCardColors(MaterialTheme.colorScheme.background),
                    elevation = CardDefaults.cardElevation(0.2.dp),
                ) {
                    Text(
                        text = "Apakah Anda Mengkonsumsi Sayur Secara Rutin?",
                        modifier = Modifier.padding(top = 10.dp, start = 20.dp)
                    )
                    CustomRadioButton(vegetable, Modifier.padding(1.dp))
                }
                Spacer(modifier = Modifier.padding(5.dp))
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    colors = elevatedCardColors(MaterialTheme.colorScheme.background),
                    elevation = CardDefaults.cardElevation(0.2.dp),
                ) {
                    Text(
                        text = "Apakah Anda Kesilitan untuk Berjalan?",
                        modifier = Modifier.padding(top = 10.dp, start = 20.dp)
                    )
                    CustomRadioButton(walk, Modifier.padding(1.dp))
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End)
                        .padding(end = 10.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(0.7f),
                        onClick = {
                            viewModel.sendData(
                                PredictRequestBody(
                                    kelamin = genderStatus.ordinal,
                                    umur = ageStatus.value.toInt(),
                                    bmi = bmi,
                                    tekananDarah = bloodPressureStatus.ordinal,
                                    kolesterol = cholesterolStatus.ordinal,
                                    stroke = strokeStatus.ordinal,
                                    diabetes = diabetesStatus.ordinal,
                                    sakitJantung = null,
                                    rokok = smokingStatus.ordinal,
                                    alkohol = alcoholStatus.ordinal,
                                    olahraga = exerciseStatus.ordinal,
                                    buah = fruitStatus.ordinal,
                                    sayur = vegetableStatus.ordinal,
                                    susahJalan = walkStatus.ordinal,
                                ), token = token.token
                            )
                        },
                        enabled = ageStatus.value.isNotEmpty() && bmi != 0
                    ) {
                        Text(text = "Cek Resiko Jantung")
                    }
                }
                LaunchedEffect(uiState) {
                    when (val currentState = uiState) {
                        is HeartUiState.Loading -> {
//                            isLoading = true
                        }

                        is HeartUiState.Success -> {
                            val result = currentState.result
                            clickSubmit(result)
                            viewModel.resetUiState()
                        }

                        is HeartUiState.Error -> {
                            snackbarHostState.showSnackbar(currentState.errorMessage)
                        }

                        else -> {}
                    }
                }
            }
            if (isLoading) {
                LoadingIndicator()
            }
        }
    )
}

@Composable
fun LoadingIndicator() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .height(400.dp)
                .width(400.dp)
                .padding(100.dp),
            strokeWidth = 5.dp,
            color = MaterialTheme.colorScheme.secondary,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HeartPreview() {

    HeartScreen(
        clickBack = {}, snackbarHostState = SnackbarHostState(), clickSubmit = {},
        token = UserLogin(1, "", "", "", "", "")
    )
}