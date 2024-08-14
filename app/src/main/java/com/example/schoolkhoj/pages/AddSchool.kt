package com.example.schoolkhoj.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.schoolkhoj.AuthState
import com.example.schoolkhoj.AuthViewModel
import com.example.schoolkhoj.data.Faculty

@SuppressLint("MutableCollectionMutableState")
@Composable
fun AddSchool(
    modifier: Modifier,
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val authState = authViewModel.authState.observeAsState()

    var name: String by remember { mutableStateOf("") }
    var address: String by remember { mutableStateOf("") }
    var city: String by remember { mutableStateOf("") }
    var state: String by remember { mutableStateOf("") }
    var type: String by remember { mutableStateOf("") }
    var startGrade: String by remember { mutableStateOf("") }
    var endGrade: String by remember { mutableStateOf("") }
    var faculties: List<Faculty> by remember { mutableStateOf(mutableListOf()) }
    var feeStructure: HashMap<String, Int> by remember { mutableStateOf(HashMap()) }

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
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            OutlinedTextField(
                shape = RoundedCornerShape(20.dp),
                value = name,
                onValueChange = {
                    name = it
                },
                label = {
                    Text(
                        text = "Name",
                        style = textStyle
                    )
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                shape = RoundedCornerShape(20.dp),
                value = address,
                onValueChange = {
                    address = it
                },
                label = {
                    Text(
                        text = "Address",
                        style = textStyle
                    )
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                shape = RoundedCornerShape(20.dp),
                value = city,
                onValueChange = {
                    city = it
                },
                label = {
                    Text(
                        text = "City",
                        style = textStyle
                    )
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                shape = RoundedCornerShape(20.dp),
                value = state,
                onValueChange = {
                    state = it
                },
                label = {
                    Text(
                        text = "State",
                        style = textStyle
                    )
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                shape = RoundedCornerShape(20.dp),
                value = type,
                onValueChange = {
                    address = it
                },
                label = {
                    Text(
                        text = "Type",
                        style = textStyle
                    )
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                shape = RoundedCornerShape(20.dp),
                value = startGrade,
                onValueChange = {
                    startGrade = it
                },
                label = {
                    Text(
                        text = "Start Grade",
                        style = textStyle
                    )
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                shape = RoundedCornerShape(20.dp),
                value = endGrade,
                onValueChange = {
                    endGrade = it
                },
                label = {
                    Text(
                        text = "End Grade",
                        style = textStyle
                    )
                }
            )
        }

    }
    SmallFloatingActionButton(
        onClick = { navController.popBackStack() },
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.secondary
    ) {
        Text(
            text = "Add",
            style = textStyle
        )
    }
}