package com.example.schoolkhoj.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.schoolkhoj.AuthViewModel
import kotlinx.coroutines.launch

sealed class DetailNavItem(val route: String, val title: String) {
    object Images : DetailNavItem("images", "Images")
    object Faculty : DetailNavItem("faculty", "Faculty")
    object FeeStructure : DetailNavItem("fee_structure", "Fee Structure")
}

@OptIn(ExperimentalMaterial3Api::class) // Handle potential experimental API warnings
@Composable
fun DetailPage(modifier: Modifier = Modifier,
               navController: NavController,
               authViewModel: AuthViewModel,
               school: School
) {
    val items = listOf(DetailNavItem.Images, DetailNavItem.Faculty, DetailNavItem.FeeStructure)
    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    val currentScreen = currentBackStackEntry.value?.destination?.route ?: DetailNavItem.Images.route
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = school.schoolName) },
                actions = {
                    items.forEach { item ->
                        TextButton(onClick = {
                            scope.launch {
                                try {
                                    navController.navigate(item.route)
                                } catch (e:Exception) {
                                e.printStackTrace()
                            }

                            }
                        }) {
                            Text(
                                text = item.title,
                                color = if (currentScreen == item.route) Color.White else Color.Gray
                            )
                        }
                    }
                }
            )
        },
        content = { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                NavHost(navController = navController, startDestination = DetailNavItem.Images.route) {
                    composable(DetailNavItem.Images.route) {
                        ImageContent(school)
                    }
                    composable(DetailNavItem.Faculty.route) {
                        FacultyContent(school)
                    }
                    composable(DetailNavItem.FeeStructure.route) {
                        FeeStructureContent(school)
                    }
                }
            }
        }
    )
}

@Composable
fun ImageContent(school: School) {
    Image(
        painter = painterResource(id = school.imageRes),
        contentDescription = "${school.schoolName} Images",
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun FacultyContent(school: School) {
    val facultyMembers = listOf(
        "Dr. John Doe - Principal",
        "Mrs. Jane Smith - Vice Principal",
        "Mr. Alan Brown - Science Teacher",
        // Add more members here
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(facultyMembers) { member ->
            FacultyCard(member)
        }
    }
}

@Composable
fun FacultyCard(member: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Text(
            text = member,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun FeeStructureContent(school: School) {
    Text(
        text = "Fee Structure for ${school.schoolName}",
        modifier = Modifier.padding(16.dp),
        style = MaterialTheme.typography.bodyLarge
    )
}
