package com.example.gamer.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamer.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(
    onNavigateBack: () -> Unit,
    showSnack: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    // Validation states
    var fullNameError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var confirmPasswordError by remember { mutableStateOf<String?>(null) }

    fun validateFullName(name: String): String? {
        return when {
            name.isEmpty() -> "Full name is required"
            name.length < 3 -> "Name must be at least 3 characters"
            name.length > 50 -> "Name too long (max 50 characters)"
            !name.matches(Regex("^[a-zA-Z\\s]+$")) -> "Name should contain only letters"
            else -> null
        }
    }


    fun validateEmail(email: String): String? {
        return when {
            email.isEmpty() -> "Email is required"
            !email.contains("@") -> "Invalid email format"
            !email.contains(".") -> "Invalid email format"
            email.length < 5 -> "Email too short"
            !email.matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) -> "Invalid email format"
            else -> null
        }
    }

    fun validatePassword(password: String): String? {
        return when {
            password.isEmpty() -> "Password is required"
            password.length < 6 -> "Password must be at least 6 characters"
            password.length > 20 -> "Password too long (max 20 characters)"
            !password.matches(Regex(".*[A-Z].*")) -> "Password must contain uppercase letter"
            !password.matches(Regex(".*[a-z].*")) -> "Password must contain lowercase letter"
            !password.matches(Regex(".*[0-9].*")) -> "Password must contain a number"
            else -> null
        }
    }


    fun validateConfirmPassword(confirm: String, original: String): String? {
        return when {
            confirm.isEmpty() -> "Please confirm your password"
            confirm != original -> "Passwords do not match"
            else -> null
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        TopAppBar(
            title = { },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color(0xFFE91E63)
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White
            )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.gamer_logo),
                contentDescription = "Gamer Logo",
                modifier = Modifier.size(100.dp)
            )

            Spacer(Modifier.height(32.dp))


            OutlinedTextField(
                value = fullName,
                onValueChange = {
                    fullName = it
                    fullNameError = if (it.isNotEmpty()) validateFullName(it) else null
                },
                label = { Text("FullName", color = Color(0xFFE91E63)) },
                singleLine = true,
                leadingIcon = {
                    Icon(
                        Icons.Filled.Person,
                        contentDescription = null,
                        tint = Color(0xFFE91E63)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                isError = fullNameError != null,
                supportingText = {
                    if (fullNameError != null) {
                        Text(fullNameError!!, color = MaterialTheme.colorScheme.error)
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
                    if (confirmPassword.isNotEmpty()) {
                        confirmPasswordError = validateConfirmPassword(confirmPassword, it)
                    }
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
                        Text(passwordError!!, color = MaterialTheme.colorScheme.error, fontSize = 11.sp)
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
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                    confirmPasswordError = if (it.isNotEmpty()) validateConfirmPassword(it, password) else null
                },
                label = { Text("Confirm Password", color = Color(0xFFE91E63)) },
                singleLine = true,
                leadingIcon = {
                    Icon(
                        Icons.Filled.Lock,
                        contentDescription = null,
                        tint = Color(0xFFE91E63)
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                        Text(
                            text = if (confirmPasswordVisible) "👁️" else "👁️",
                            fontSize = 20.sp,
                            color = Color(0xFFE91E63)
                        )
                    }
                },
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                isError = confirmPasswordError != null,
                supportingText = {
                    if (confirmPasswordError != null) {
                        Text(confirmPasswordError!!, color = MaterialTheme.colorScheme.error)
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFE91E63),
                    unfocusedBorderColor = Color(0xFFE91E63),
                    errorBorderColor = MaterialTheme.colorScheme.error
                )
            )

            Spacer(Modifier.height(24.dp))


            Button(
                onClick = {
                    val nameValidation = validateFullName(fullName)
                    val emailValidation = validateEmail(email)
                    val passwordValidation = validatePassword(password)
                    val confirmValidation = validateConfirmPassword(confirmPassword, password)

                    fullNameError = nameValidation
                    emailError = emailValidation
                    passwordError = passwordValidation
                    confirmPasswordError = confirmValidation

                    if (nameValidation == null && emailValidation == null &&
                        passwordValidation == null && confirmValidation == null) {
                        showSnack("Registration successful!")
                        onNavigateBack()
                    } else {
                        showSnack("Please fix all errors")
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
                Text("Submit", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(Modifier.height(16.dp))


            Text(
                "By registering you agree to our Terms &\nConditions and Privacy Policy",
                color = Color(0xFFE91E63),
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}