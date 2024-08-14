package com.example.schoolkhoj

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.schoolkhoj.pages.AddSchool
import com.example.schoolkhoj.pages.AdminPanel
import com.example.schoolkhoj.pages.DetailPage
import com.example.schoolkhoj.pages.LoginPage
import com.example.schoolkhoj.pages.SignupPage
import com.example.schoolkhoj.pages.HomePage
import com.example.schoolkhoj.util.type.Navigation

import com.example.schoolkhoj.pages.School
import java.nio.charset.StandardCharsets

@Composable
fun AppNavigation(modifier: Modifier = Modifier, authViewModel: AuthViewModel) {
    val navController = rememberNavController()

    fun decodeValue(value: String): String {
        return java.net.URLDecoder.decode(value, StandardCharsets.UTF_8.toString())
    }

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
        composable("detail/{schoolName}/{boardType}/{coEdStatus}/{grade}/{imageRes}") { backStackEntry ->
            val schoolName = backStackEntry.arguments?.getString("schoolName") ?: ""
            val boardType = backStackEntry.arguments?.getString("boardType") ?: ""
            val coEdStatus = backStackEntry.arguments?.getString("coEdStatus") ?: ""
            val grade = backStackEntry.arguments?.getString("grade") ?: ""
            val imageResString = backStackEntry.arguments?.getString("imageRes") ?: ""
            val imageRes = imageResString.toIntOrNull() ?: R.drawable.north_point // Default image resource

            val school = School(
                imageRes = imageRes,
                schoolName = decodeValue(schoolName),
                boardType = decodeValue(boardType),
                coEdStatus = decodeValue(coEdStatus),
                grade = decodeValue(grade)
            )
            DetailPage(navController = navController, school = school)
        }
    }
}
