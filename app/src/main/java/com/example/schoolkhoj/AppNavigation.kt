package com.example.schoolkhoj

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.schoolkhoj.data.School
import com.example.schoolkhoj.pages.AddSchool
import com.example.schoolkhoj.pages.AdminPanel
import com.example.schoolkhoj.pages.DetailPage
import com.example.schoolkhoj.pages.FacultyCard
import com.example.schoolkhoj.pages.FacultyContent
import com.example.schoolkhoj.pages.FeeStructureContent
import com.example.schoolkhoj.pages.HomePage
import com.example.schoolkhoj.pages.ImageContent
import com.example.schoolkhoj.pages.LoginPage
import com.example.schoolkhoj.pages.SignupPage
import com.example.schoolkhoj.util.type.Navigation
import com.google.gson.Gson
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
        composable("detail?school={school}",
            arguments = listOf(
                navArgument(
                    name = "school"
                ) {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        )
         {
             val schoolJson = it.arguments?.getString("school")
             val school = Gson().fromJson(schoolJson, School::class.java)
            DetailPage(navController = navController, authViewModel = authViewModel, school = school)
        }
    }
}
