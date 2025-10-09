package com.example.gamer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.gamer.ui.ForgotPasswordScreen
import com.example.gamer.ui.LoginScreen
import com.example.gamer.ui.NewsScreen
import com.example.gamer.ui.OtpValidationScreen
import com.example.gamer.ui.ProfileScreen
import com.example.gamer.ui.ResetPasswordScreen
import com.example.gamer.ui.SignupScreen
import com.example.gamer.ui.StoreScreen
import com.example.gamer.ui.theme.GamerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GamerTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()

                // Screen state
                var currentScreen by remember { mutableStateOf("login") }
                var userEmail by remember { mutableStateOf("") }

                Scaffold(
                    snackbarHost = { SnackbarHost(snackbarHostState) }
                ) { padding ->

                    when (currentScreen) {

                        "login" -> LoginScreen(
                            onNavigateToSignup = {
                                currentScreen = "signup"
                            },
                            onNavigateToForgotPassword = {
                                currentScreen = "forgot_password"
                            },
                            onLoginSuccess = {
                                currentScreen = "news"
                            },
                            showSnack = { msg ->
                                scope.launch { snackbarHostState.showSnackbar(msg) }
                            },
                            modifier = Modifier.padding(padding)
                        )

                        "signup" -> SignupScreen(
                            onNavigateBack = {
                                currentScreen = "login"
                            },
                            showSnack = { msg ->
                                scope.launch { snackbarHostState.showSnackbar(msg) }
                            },
                            modifier = Modifier.padding(padding)
                        )

                        "forgot_password" -> ForgotPasswordScreen(
                            onNavigateBack = { currentScreen = "login" },
                            onNavigateToOtp = { email ->
                                userEmail = email
                                currentScreen = "otp"
                            },
                            showSnack = { msg ->
                                scope.launch { snackbarHostState.showSnackbar(msg) }
                            },
                            modifier = Modifier.padding(padding)
                        )

                        "otp" -> OtpValidationScreen(
                            email = userEmail,
                            onNavigateBack = { currentScreen = "forgot_password" },
                            onNavigateToReset = { currentScreen = "reset_password" },
                            showSnack = { msg ->
                                scope.launch { snackbarHostState.showSnackbar(msg) }
                            },
                            modifier = Modifier.padding(padding)
                        )

                        "reset_password" -> ResetPasswordScreen(
                            onNavigateBack = { currentScreen = "otp" },
                            onPasswordReset = { currentScreen = "login" },
                            showSnack = { msg ->
                                scope.launch { snackbarHostState.showSnackbar(msg) }
                            },
                            modifier = Modifier.padding(padding)
                        )

                        "news" -> NewsScreen(
                            onNavigateBack = { currentScreen = "login" },
                            onNavigateToStore = { currentScreen = "store" },
                            onNavigateToProfile = { currentScreen = "profile" },
                            showSnack = { msg ->
                                scope.launch { snackbarHostState.showSnackbar(msg) }
                            },
                            modifier = Modifier.padding(padding)
                        )

                        "store" -> StoreScreen(
                            onNavigateToNews = { currentScreen = "news" },
                            onNavigateToProfile = { currentScreen = "profile" },
                            onNavigateBack = { currentScreen = "login" },
                            showSnack = { msg ->
                                scope.launch { snackbarHostState.showSnackbar(msg) }
                            },
                            modifier = Modifier.padding(padding)
                        )

                        "profile" -> ProfileScreen(
                            onNavigateToNews = { currentScreen = "news" },
                            onNavigateToStore = { currentScreen = "store" },
                            onNavigateBack = { currentScreen = "login" },
                            showSnack = { msg ->
                                scope.launch { snackbarHostState.showSnackbar(msg) }
                            },
                            modifier = Modifier.padding(padding)
                        )
                    }
                }
            }
        }
    }
}