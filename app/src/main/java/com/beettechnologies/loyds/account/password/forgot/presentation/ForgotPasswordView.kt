package com.beettechnologies.loyds.account.password.forgot.presentation

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.beettechnologies.loyds.R
import com.beettechnologies.loyds.app.navigation.Navigation
import com.beettechnologies.loyds.app.theme.Purple700
import com.beettechnologies.loyds.common.presentation.LogoView

@Composable
fun ForgotPasswordView(modifier: Modifier = Modifier, navigation: Navigation) {

    val viewModel = hiltViewModel<ForgotPasswordViewModel>()
    val focusManager = LocalFocusManager.current
    var email by rememberSaveable { mutableStateOf("") }
    val isValidate by derivedStateOf { email.isNotBlank() }

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
                        text = "Forgot password",
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
                    keyboardActions = KeyboardActions(onSend = {
                        focusManager.clearFocus()
                    }),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Send,
                        keyboardType = KeyboardType.Email
                    ),
                )
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Button(
                        onClick = {
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

                        Text(
                            text = stringResource(id = R.string.account_view_reset_password_label),
                            fontSize = 18.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}