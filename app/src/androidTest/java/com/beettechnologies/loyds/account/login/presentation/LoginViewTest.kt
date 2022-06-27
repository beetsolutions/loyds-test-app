package com.beettechnologies.loyds.account.login.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.beettechnologies.loyds.MainActivity
import com.beettechnologies.loyds.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalAnimationApi
class LoginViewTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val activity by lazy { composeTestRule.activity }

    @Test
    fun loginUiElementsShown() {
        composeTestRule.onNodeWithText(activity.getString(R.string.account_view_description_label)).assertIsDisplayed()
        composeTestRule.onNodeWithText(activity.getString(R.string.account_view_username_label)).assertIsDisplayed()
        composeTestRule.onNodeWithText(activity.getString(R.string.account_view_username_placeholder_label)).assertIsDisplayed()
        composeTestRule.onNodeWithText(activity.getString(R.string.account_view_password_label)).assertIsDisplayed()
        composeTestRule.onNodeWithText(activity.getString(R.string.account_view_password_placeholder_label)).assertIsDisplayed()
        composeTestRule.onNodeWithText(activity.getString(R.string.account_view_forgot_password_label)).assertIsDisplayed()
        composeTestRule.onNodeWithText(activity.getString(R.string.account_view_register_label)).assertIsDisplayed()
        composeTestRule.onNodeWithText(activity.getString(R.string.account_view_login_label)).assertIsDisplayed()
        composeTestRule.onNodeWithText(activity.getString(R.string.account_view_login_label)).assertIsNotEnabled()
    }

    @Test
    fun loginButton_enableToggles() {
        composeTestRule.onNodeWithText(activity.getString(R.string.account_view_login_label)).assertIsDisplayed()
        composeTestRule.onNodeWithText(activity.getString(R.string.account_view_login_label)).assertIsNotEnabled()

        findUsernameTextInputField().performTextInput("test1")

        composeTestRule.onNodeWithText(activity.getString(R.string.account_view_login_label)).assertIsNotEnabled()

        findPasswordTextInputField().performTextInput("password1")

        composeTestRule.onNodeWithText(activity.getString(R.string.account_view_login_label)).assertIsEnabled()
    }

    private fun findUsernameTextInputField(): SemanticsNodeInteraction {
        return composeTestRule.onNodeWithText(activity.getString(R.string.account_view_username_label))
    }

    private fun findPasswordTextInputField(): SemanticsNodeInteraction {
        return composeTestRule.onNodeWithText(activity.getString(R.string.account_view_password_label))
    }
}
