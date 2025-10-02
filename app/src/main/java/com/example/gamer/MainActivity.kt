package com.example.gamer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.gamer.ui.LoginScreen
import com.example.gamer.ui.SignupScreen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var currentScreen by remember { mutableStateOf("login") }
            val snackbarHostState = remember { SnackbarHostState() }
            val scope = rememberCoroutineScope()

            Scaffold(
                snackbarHost = { SnackbarHost(snackbarHostState) }
            ) { padding ->
                when (currentScreen) {
                    "login" -> LoginScreen(
                        onNavigateToSignup = { currentScreen = "signup" },
                        showSnack = { msg ->
                            scope.launch {
                                snackbarHostState.showSnackbar(msg)
                            }
                        },
                        modifier = Modifier.padding(padding)
                    )
                    "signup" -> SignupScreen(
                        onNavigateBack = { currentScreen = "login" },
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