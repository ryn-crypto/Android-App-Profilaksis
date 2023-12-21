package com.profilaksis.profilaksis.ui.screen.authorization.register

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHostState
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.profilaksis.profilaksis.R
import com.profilaksis.profilaksis.data.remote.requestdata.RegisterRequestBody
import com.profilaksis.profilaksis.di.Injection
import com.profilaksis.profilaksis.ui.components.CustomInput
import com.profilaksis.profilaksis.ui.screen.ViewModelFactory
import com.profilaksis.profilaksis.utils.isValidEmail

@Composable
fun RegisterPage(
    viewModel: RegisterViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    onRegisterSuccess: () -> Unit,
    onBackClick: () -> Unit = {},
    snackbarHostState: SnackbarHostState,
) {
    BackHandler(onBack = onBackClick)

    var isLoading by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var visible by remember { mutableStateOf(false) }

    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 35.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                modifier = Modifier
                    .height(180.dp)
                    .width(180.dp)
                    .padding(bottom = 16.dp),
                painter = painterResource(R.drawable.image_logo),
                contentDescription = "Logo",
            )
        }
        CustomInput(
            placeholder = stringResource(id = R.string.email),
            leftIcon = Icons.Default.Email,
            isRightIconEnabled = false,
            visibleIcon = false,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { email = it }
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomInput(
            placeholder = stringResource(id = R.string.username),
            leftIcon = Icons.Default.AccountBox,
            isRightIconEnabled = false,
            visibleIcon = false,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { username = it }
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomInput(
            modifier = Modifier.fillMaxWidth(),
            placeholder = stringResource(id = R.string.password),
            leftIcon = Icons.Default.Lock,
            rightIcon = ImageVector.vectorResource(R.drawable.invisible),
            rightIconFalse = ImageVector.vectorResource(R.drawable.visible),
            isRightIconEnabled = true,
            onRightIconClick = { visible = !visible },
            visibleIcon = visible,
            onValueChange = { password = it },
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomInput(
            modifier = Modifier.fillMaxWidth(),
            placeholder = stringResource(id = R.string.confirm_password),
            leftIcon = Icons.Default.Lock,
            isRightIconEnabled = false,
            visibleIcon = visible,
            onValueChange = { confirmPassword = it }
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                viewModel.register(
                    RegisterRequestBody(
                        email = email,
                        username = username,
                        password = password,
                        confirmPassword = confirmPassword
                    )
                )
                viewModel.loadingUiState()
            },
            enabled = isValidEmail(email),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Daftar")
        }

        LaunchedEffect(uiState) {
            when (val currentState = uiState) {
                is RegisterUiState.Loading -> {
                    isLoading = true
                }

                is RegisterUiState.Success -> {
                    isLoading = false
                    onRegisterSuccess()
                }

                is RegisterUiState.Error -> {
                    isLoading = false
                    snackbarHostState.showSnackbar(currentState.errorMessage)
                }

                else -> {}
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPagePreview() {
    RegisterPage(
        onRegisterSuccess = {},
        snackbarHostState = SnackbarHostState()
    )
}
