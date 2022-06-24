package com.beettechnologies.loyds.account.login.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.beettechnologies.loyds.R
import com.beettechnologies.loyds.app.navigation.Navigation
import com.beettechnologies.loyds.app.theme.Purple700
import com.beettechnologies.loyds.common.presentation.LoadingView

@Composable
//@Preview(showBackground = true)
fun LoginView(modifier: Modifier = Modifier, navigation: Navigation) {

    val viewModel = hiltViewModel<LoginViewModel>()
    val isLoading = viewModel.isLoading.collectAsState()
    val hasError = viewModel.hasError.collectAsState()
    val errorMessage = viewModel.errorMessage.collectAsState()
    val loginSuccess = viewModel.loginSuccess.collectAsState()

    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    val isValidate by derivedStateOf { username.isNotBlank() && password.isNotBlank() }

    if (loginSuccess.value) {
        navigation.navigateToHome()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Purple700),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "Logo Icon",
            modifier = Modifier.wrapContentSize(Alignment.Center)
        )
        Text(
            text = stringResource(R.string.account_view_phone_number_label),
            textAlign = TextAlign.Center,
            modifier = modifier.padding(16.dp),
            color = Color.White
        )

        if (hasError.value) {
            Text(
                text = errorMessage.value?: "",
                textAlign = TextAlign.Center,
                modifier = modifier.padding(16.dp),
                color = Color.Red
            )
        }

        TextField(
            value = username,
            onValueChange = {
                username = it
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            label = {
                Text(
                    text = stringResource(id = R.string.account_view_username_label),
                )
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.account_view_username_placeholder_label),
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                textColor = Color.Black,
                placeholderColor = Color.Gray
            )
        )

        TextField(
            value = password,
            onValueChange = {
                password = it
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            label = {
                Text(
                    text = stringResource(id = R.string.account_view_password_label),
                )
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.account_view_password_placeholder_label),
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                textColor = Color.Black,
                placeholderColor = Color.Gray
            )
        )

        Button(
            onClick = {
                viewModel.login(username, password)
            },
            modifier = modifier
                .width(200.dp)
                .padding(top = 16.dp),
            enabled = isValidate,
            elevation = ButtonDefaults.elevation(defaultElevation = 5.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFFF5722),
                disabledBackgroundColor = Color(0xFFFF5722).copy(alpha = 0.4f)
            )
        ) {
            if (isLoading.value) {
                LoadingView()
            } else {
                Text(
                    text = stringResource(id = R.string.account_view_login_label),
                    fontSize = 18.sp,
                    color = Color.White
                )
            }
        }

        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.account_view_forgot_password_label),
                fontSize = 18.sp,
                color = Color.White,
                modifier = modifier.align(Alignment.CenterStart)
            )
            Text(
                text = stringResource(id = R.string.account_view_register_label),
                fontSize = 18.sp,
                color = Color.White,
                modifier = modifier.align(Alignment.CenterEnd)
            )
        }
    }
}