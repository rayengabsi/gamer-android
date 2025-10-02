package com.example.gamer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.gamer.ui.*
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val themeViewModel: ThemeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDarkMode = themeViewModel.isDarkMode

            GamerTheme(darkTheme = isDarkMode) {
                val snackbarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()

                var currentScreen by remember { mutableStateOf("login") }
                var userEmail by remember { mutableStateOf("") }

                Scaffold(
                    snackbarHost = { SnackbarHost(snackbarHostState) }
                ) { padding ->
                    when (currentScreen) {
                        "login" -> LoginScreen(
                            onNavigateToSignup = {
                                scope.launch {
                                    snackbarHostState.showSnackbar("Signup coming soon")
                                }
                            },
                            onNavigateToForgotPassword = {
                                currentScreen = "forgot_password"
                            },
                            showSnack = { msg ->
                                scope.launch {
                                    snackbarHostState.showSnackbar(msg)
                                }
                            },
                            isDarkMode = isDarkMode,
                            onToggleTheme = { themeViewModel.toggleTheme() },
                            modifier = Modifier.padding(padding)
                        )

                        "forgot_password" -> ForgotPasswordScreen(
                            onNavigateBack = {
                                currentScreen = "login"
                            },
                            onNavigateToOtp = { email ->
                                userEmail = email
                                currentScreen = "otp"
                            },
                            showSnack = { msg ->
                                scope.launch {
                                    snackbarHostState.showSnackbar(msg)
                                }
                            },
                            modifier = Modifier.padding(padding)
                        )

                        "otp" -> OtpValidationScreen(
                            email = userEmail,
                            onNavigateBack = {
                                currentScreen = "forgot_password"
                            },
                            onNavigateToReset = {
                                currentScreen = "reset_password"
                            },
                            showSnack = { msg ->
                                scope.launch {
                                    snackbarHostState.showSnackbar(msg)
                                }
                            },
                            modifier = Modifier.padding(padding)
                        )

                        "reset_password" -> ResetPasswordScreen(
                            onNavigateBack = {
                                currentScreen = "otp"
                            },
                            onPasswordReset = {
                                currentScreen = "login"
                            },
                            showSnack = { msg ->
                                scope.launch {
                                    snackbarHostState.showSnackbar(msg)
                                }
                            },
                            modifier = Modifier.padding(padding)
                        )
                    }
                }
            }
        }
    }
}