package com.example.schoolkhoj.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import com.example.schoolkhoj.AuthState
import com.example.schoolkhoj.AuthViewModel
import com.example.schoolkhoj.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

data class School(
    val imageRes: Int,
    val schoolName: String,
    val boardType: String,
    val coEdStatus: String,
    val grade: String
)

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val authState = authViewModel.authState.observeAsState()
    val searchQuery = remember { mutableStateOf(TextFieldValue("")) }

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Unauthenticated -> navController.navigate("login")
            else -> Unit
        }
    }

    val schools = listOf(
        School(R.drawable.north_point, "North Point", "ICSE/CBSE", "Co-Ed", "10+2"),
        School(R.drawable.st_xavier, "St.Xavier's", "CBSE", "Co-Ed", "10+2"),
        School(R.drawable.sunshine, "Sunshine", "CBSE", "Co-Ed", "10+2"),
        School(R.drawable.dav, "Dav", "CBSE", "Co-Ed", "10+2"),
        School(R.drawable.gd, "Gd", "CBSE", "Co-Ed", "10+2")
        // Add more schools here
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Search Bar
        TextField(
            value = searchQuery.value,
            onValueChange = { searchQuery.value = it },
            placeholder = { Text(text = "Search Schools...") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(schools.filter {
                it.schoolName.contains(searchQuery.value.text, ignoreCase = true)
            }) { school ->
                SchoolCard(
                    imageRes = school.imageRes,
                    schoolName = school.schoolName,
                    boardType = school.boardType,
                    coEdStatus = school.coEdStatus,
                    grade = school.grade,
                    navController = navController
                )
            }
        }
    }
}
