package com.profilaksis.profilaksis.ui.screen.authorization.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.profilaksis.profilaksis.R
import com.profilaksis.profilaksis.data.model.UserLogin
import com.profilaksis.profilaksis.data.remote.requestdata.LoginRequestBody
import com.profilaksis.profilaksis.di.Injection
import com.profilaksis.profilaksis.ui.components.CustomInput
import com.profilaksis.profilaksis.ui.components.Loader
import com.profilaksis.profilaksis.ui.screen.ViewModelFactory

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    snackbarHostState: SnackbarHostState,
    loginSuccess: (UserLogin) -> Unit,
    onRegisterClick: () -> Unit,
) {
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var visible by remember { mutableStateOf(true) }
    var isLoading by remember { mutableStateOf(false) }

    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp, horizontal = 50.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
                    .padding(bottom = 16.dp),
                painter = painterResource(R.drawable.image_logo),
                contentDescription = "Logo",
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        CustomInput(
            modifier = Modifier.fillMaxWidth(),
            placeholder = stringResource(id = R.string.username),
            leftIcon = Icons.Default.AccountBox,
            isRightIconEnabled = false,
            visibleIcon = false,
            onValueChange = { userName = it }
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomInput(
            modifier = Modifier.fillMaxWidth(),
            placeholder = stringResource(id = R.string.password),
            leftIcon = Icons.Default.Lock,
            rightIcon = ImageVector.vectorResource(R.drawable.visible),
            rightIconFalse = ImageVector.vectorResource(R.drawable.invisible),
            isRightIconEnabled = true,
            onRightIconClick = { visible = !visible },
            visibleIcon = visible,
            onValueChange = { password = it },
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            contentPadding = PaddingValues(4.dp),
            onClick = {
                viewModel.login(
                    LoginRequestBody(
                        username = userName,
                        password = password
                    )
                )
                viewModel.loadingUiState()
            },
            enabled = userName.isNotEmpty() && password.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.login))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = stringResource(id = R.string.dont_have_account))
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = stringResource(id = R.string.register),
                color = Color.Blue,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .clickable { onRegisterClick() }
            )
        }
    }
    LaunchedEffect(uiState) {
        when (val currentState = uiState) {
            is LoginUiState.Loading -> {
                isLoading = true
            }

            is LoginUiState.Success -> {
                currentState.result.data?.let { loginSuccess(it) }
                viewModel.resetUiState()
                isLoading = false
            }

            is LoginUiState.Error -> {
                snackbarHostState.showSnackbar(currentState.errorMessage)
                isLoading = false
            }

            else -> {}
        }
    }
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            Loader()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPagePreview() {
    LoginScreen(
        loginSuccess = {},
        snackbarHostState = SnackbarHostState(),
        onRegisterClick = {}
    )
}