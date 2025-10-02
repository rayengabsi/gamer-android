package com.example.gamer.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamer.R

@Composable
fun LoginScreen(
    onNavigateToSignup: () -> Unit,
    onNavigateToForgotPassword: () -> Unit,  // ← NOUVELLE LIGNE
    showSnack: (String) -> Unit,
    modifier: Modifier = Modifier
){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var rememberMe by remember { mutableStateOf(false) }

    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }


    fun validateEmail(email: String): String? {
        return when {
            email.isEmpty() -> "Email is required"
            !email.contains("@") -> "Invalid email format"
            !email.contains(".") -> "Invalid email format"
            email.length < 5 -> "Email too short"
            else -> null
        }
    }

    fun validatePassword(password: String): String? {
        return when {
            password.isEmpty() -> "Password is required"
            password.length < 6 -> "Password must be at least 6 characters"
            password.length > 20 -> "Password too long (max 20 characters)"
            else -> null
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.gamer_logo),
            contentDescription = "Gamer Logo",
            modifier = Modifier.size(120.dp)
        )

        Spacer(Modifier.height(40.dp))


        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = if (it.isNotEmpty()) validateEmail(it) else null
            },
            label = { Text("Email", color = Color(0xFFE91E63)) },
            singleLine = true,
            leadingIcon = {
                Icon(
                    Icons.Filled.Email,
                    contentDescription = null,
                    tint = Color(0xFFE91E63)
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth(),
            isError = emailError != null,
            supportingText = {
                if (emailError != null) {
                    Text(emailError!!, color = MaterialTheme.colorScheme.error)
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFE91E63),
                unfocusedBorderColor = Color(0xFFE91E63),
                errorBorderColor = MaterialTheme.colorScheme.error
            )
        )

        Spacer(Modifier.height(16.dp))


        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = if (it.isNotEmpty()) validatePassword(it) else null
            },
            label = { Text("Password", color = Color(0xFFE91E63)) },
            singleLine = true,
            leadingIcon = {
                Icon(
                    Icons.Filled.Lock,
                    contentDescription = null,
                    tint = Color(0xFFE91E63)
                )
            },
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Text(
                        text = if (passwordVisible) "👁️" else "👁️",
                        fontSize = 20.sp,
                        color = Color(0xFFE91E63)
                    )
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            isError = passwordError != null,
            supportingText = {
                if (passwordError != null) {
                    Text(passwordError!!, color = MaterialTheme.colorScheme.error)
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFE91E63),
                unfocusedBorderColor = Color(0xFFE91E63),
                errorBorderColor = MaterialTheme.colorScheme.error
            )
        )

        Spacer(Modifier.height(8.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = rememberMe,
                    onCheckedChange = { rememberMe = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFFE91E63),
                        uncheckedColor = Color(0xFFE91E63)
                    )
                )
                Text("Remember Me", color = Color(0xFFE91E63))
            }
            Text(
                "Forgot password ?",
                modifier = Modifier.clickable { onNavigateToForgotPassword() },  // ← CHANGEMENT ICI
                color = Color(0xFFE91E63)
            )
        }

        Spacer(Modifier.height(24.dp))


        Button(
            onClick = {
                val emailValidation = validateEmail(email)
                val passwordValidation = validatePassword(password)

                emailError = emailValidation
                passwordError = passwordValidation

                if (emailValidation == null && passwordValidation == null) {
                    showSnack("Login successful!")
                } else {
                    showSnack("Please fix the errors")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFE91E63)
            ),
            shape = MaterialTheme.shapes.large
        ) {
            Text("Login", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(Modifier.height(16.dp))

        Text("OR", color = Color(0xFFE91E63))

        Spacer(Modifier.height(16.dp))


        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { showSnack("Facebook login coming soon") },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1877F2)
                )
            ) {
                Text("Facebook", fontSize = 14.sp)
            }

            OutlinedButton(
                onClick = { showSnack("Google login coming soon") },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.Gray
                )
            ) {
                Text("Google", fontSize = 14.sp)
            }
        }

        Spacer(Modifier.height(24.dp))


        Row {
            Text("Don't have an account? ", color = Color.Gray)
            Text(
                "Register Now",
                modifier = Modifier.clickable { onNavigateToSignup() },
                color = Color(0xFFE91E63),
                fontWeight = FontWeight.Bold
            )
        }
    }
}