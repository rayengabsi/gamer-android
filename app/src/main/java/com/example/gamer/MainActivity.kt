package com.example.gamer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.gamer.ui.ForgotPasswordScreen
import com.example.gamer.ui.LoginScreen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val snackbarHostState = remember { SnackbarHostState() }
            val scope = rememberCoroutineScope()

            // Variable pour gérer quel écran afficher
            var currentScreen by remember { mutableStateOf("login") }

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
                        modifier = Modifier.padding(padding)
                    )

                    "forgot_password" -> ForgotPasswordScreen(
                        onNavigateBack = {
                            currentScreen = "login"
                        },
                        onNavigateToOtp = { email ->
                            scope.launch {
                                snackbarHostState.showSnackbar("OTP sent to $email")
                            }
                            // Plus tard: currentScreen = "otp"
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