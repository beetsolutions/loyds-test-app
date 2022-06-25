package com.beettechnologies.loyds.account.password.forgot.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.beettechnologies.loyds.MainActivity
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalAnimationApi
class ForgotPasswordViewTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val activity by lazy { composeTestRule.activity }
}