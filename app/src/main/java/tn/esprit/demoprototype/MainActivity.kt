package tn.esprit.demoprototype

import DoctorDetailScreen
import RoleSelectionScreen
import SignUpScreen
import LoginScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tn.esprit.demoprototype.ui.HomeScreen
import tn.esprit.demoprototype.ui.ProfileScreen
import tn.esprit.demoprototype.ui.components.BottomNavBar
import com.example.medicare.ScheduleScreen
import tn.esprit.demoprototype.ui.screens.OnBoardingScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "onboarding") {
        composable("onboarding") {
            OnBoardingScreen(navController)
        }
        composable("roleSelection") {
            RoleSelectionScreen(navController)
        }
        composable("signup") {
            SignUpScreen(navController)
        }
        composable("loginScreen") {
            LoginScreen(navController)
        }
        composable("home") {
            HomeScreenWithBottomNav(navController)
        }
        composable("doctorDetails/{doctorName}") { backStackEntry ->
            val doctorName = backStackEntry.arguments?.getString("doctorName")
            DoctorDetailScreen(navController)
        }
    }
}

@Composable
fun HomeScreenWithBottomNav(navController: NavHostController) {
    val bottomNavController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavBar(bottomNavController) }
    ) { innerPadding ->
        NavHost(navController = bottomNavController, startDestination = "home") {
            composable("home") { HomeScreen(navController, Modifier.padding(innerPadding)) }
            composable("loginScreen") { LoginScreen(navController) }
            composable("schedule") { ScheduleScreen(navController, Modifier.padding(innerPadding)) }
            composable("profile") { ProfileScreen(navController, Modifier.padding(innerPadding)) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp()
}
