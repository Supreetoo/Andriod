package com.example.schoolkhoj.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.schoolkhoj.AuthState
import com.example.schoolkhoj.AuthViewModel

@Composable
fun AddSchool(modifier: Modifier,
               navController: NavController,
               authViewModel: AuthViewModel
) {
    val authState = authViewModel.authState.observeAsState()

    val textStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.Monospace
    )
    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Unauthenticated -> {
                navController.popBackStack()
                navController.navigate("login")
            }

            else -> Unit
        }
    }
    Surface(modifier = modifier.fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Add School",
                style = textStyle
            )
        }
    }
}