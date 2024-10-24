package tn.esprit.demoprototype

import DoctorDetailScreen
import LoginScreen
import OnBoardingScreen
import RoleSelectionScreen
import SignUpScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.medicare.ScheduleScreen
import tn.esprit.demoprototype.ui.HomeScreen
import tn.esprit.demoprototype.ui.ProfileScreen
import tn.esprit.demoprototype.ui.components.BottomNavBar

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
    Scaffold(
        bottomBar = { BottomNavBar(navController) } // Only one line to include the BottomNavBar
    ) { innerPadding ->
        AppNavGraph(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun AppNavGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(navController = navController, startDestination = "loginScreen", modifier = modifier) {
        composable("home") {
            HomeScreen(navController)
        }

        composable("schedule") {
            ScheduleScreen(navController)
        }
        composable("loginScreen") {
            LoginScreen(navController)
        }
        composable("signup") {
            SignUpScreen(navController)
        }
        composable("profile") {
            ProfileScreen(navController)
        }
        composable("doctorDetails/{doctorName}") { backStackEntry ->
            val doctorName = backStackEntry.arguments?.getString("doctorName")
            DoctorDetailScreen(navController )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp()
}