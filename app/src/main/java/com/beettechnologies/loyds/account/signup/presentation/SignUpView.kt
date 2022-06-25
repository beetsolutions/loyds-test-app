package com.beettechnologies.loyds.account.signup.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.beettechnologies.loyds.R
import com.beettechnologies.loyds.app.navigation.Navigation
import com.beettechnologies.loyds.app.navigation.NavigationPreviewParameterProvider
import com.beettechnologies.loyds.app.theme.Purple700
import com.beettechnologies.loyds.common.presentation.LoadingView
import com.beettechnologies.loyds.common.presentation.LogoView

@Composable
@Preview(showBackground = true)
fun SignUpView(modifier: Modifier = Modifier,
               @PreviewParameter(NavigationPreviewParameterProvider::class) navigation: Navigation) {

    val viewModel = hiltViewModel<SignUpViewModel>()

    val isLoading = viewModel.isLoading.collectAsState()
    val isSuccessful = viewModel.isSuccessful.collectAsState()
    val hasError = viewModel.hasError.collectAsState()
    val errorMessage = viewModel.errorMessage.collectAsState()

    var email by rememberSaveable { mutableStateOf("") }
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    val isValidate by derivedStateOf { email.isNotBlank() && username.isNotBlank() && password.isNotBlank() }

    if (isSuccessful.value) {
        navigation.navigateBack()
        viewModel.resetUIState()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Purple700)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 16.dp)
                ) {

                    IconButton(onClick = {
                        navigation.navigateBack()
                    }, modifier = Modifier.align(Alignment.CenterStart)) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = "Back Icon",
                            tint = Color.White,
                            modifier = Modifier.padding(end = 16.dp)
                        )
                    }

                    Text(
                        text = "Registration",
                        color = Color.White,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            item {
                LogoView()
            }

            item {
                Text(
                    text = stringResource(R.string.account_view_description_label),
                    textAlign = TextAlign.Center,
                    modifier = modifier.padding(16.dp),
                    color = Color.White
                )
            }

            item {
                if (hasError.value) {
                    Text(
                        text = errorMessage.value ?: "",
                        textAlign = TextAlign.Center,
                        modifier = modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        color = Color.Red
                    )
                }
            }

            item {
                TextField(
                    value = username,
                    onValueChange = {
                        username = it
                    },
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    label = { Text(text = stringResource(R.string.account_view_username_label)) },
                    placeholder = { Text(text = stringResource(R.string.account_view_username_placeholder_label)) },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
                        textColor = Color.Black,
                        placeholderColor = Color.Gray
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(
                            FocusDirection.Next
                        )
                    }),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Text
                    ),
                )
            }

            item {
                TextField(
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    label = {
                        Text(
                            text = stringResource(id = R.string.account_view_email_label),
                        )
                    },
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.account_view_email_placeholder_label),
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
                        textColor = Color.Black,
                        placeholderColor = Color.Gray
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(
                            FocusDirection.Next
                        )
                    }),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Email
                    ),
                )
            }

            item {
                TextField(
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    label = { Text(text = stringResource(R.string.account_view_password_label)) },
                    placeholder = { Text(text = stringResource(R.string.account_view_password_placeholder_label)) },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
                        textColor = Color.Black,
                        placeholderColor = Color.Gray
                    ),
                    keyboardActions = KeyboardActions(onSend = {
                        focusManager.clearFocus()
                        if (isValidate) {
                            viewModel.signup(email, username, password)
                        }
                    }),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Send,
                        keyboardType = KeyboardType.Password
                    ),
                    visualTransformation = PasswordVisualTransformation()
                )
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            viewModel.signup(email, username, password)
                        },
                        modifier = modifier
                            .width(200.dp)
                            .padding(top = 16.dp)
                            .align(Alignment.Center),
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
                                text = stringResource(id = R.string.account_view_signup_label),
                                fontSize = 18.sp,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}