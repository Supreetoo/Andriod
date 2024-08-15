package com.example.schoolkhoj

import AddSchool
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
//import com.example.schoolkhoj.pages.AddSchool
import com.example.schoolkhoj.pages.AdminPanel
import com.example.schoolkhoj.pages.LoginPage
import com.example.schoolkhoj.pages.SignupPage
import com.example.schoolkhoj.pages.HomePage
import com.example.schoolkhoj.util.type.Navigation


@Composable
fun AppNavigation(modifier: Modifier = Modifier, authViewModel: AuthViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginPage(modifier, navController, authViewModel)
        }
        composable("signup") {
            SignupPage(modifier, navController, authViewModel)
        }
        composable("homepage") {
            HomePage(modifier, navController, authViewModel)
        }
        composable(Navigation.ADMIN.nav) {
            AdminPanel(modifier, navController, authViewModel)
        }
        composable(Navigation.SCHOOL_ADD.nav) {
            AddSchool(modifier = modifier, navController = navController, authViewModel = authViewModel)
        }
    }
}
