package tn.esprit.demoprototype.ui.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import tn.esprit.demoprototype.ui.BottomNavItem


@Composable
fun BottomNavBar(navController: NavHostController) {
    val navItems = listOf(
        BottomNavItem("home",  Icons.Default.Home),
        BottomNavItem("schedule",  Icons.Default.CalendarMonth),
        BottomNavItem("loginScreen",  Icons.Default.Chat),
        BottomNavItem("profile", Icons.Default.Person)
    )

    BottomNavigation(
        backgroundColor = Color(0xFFA7C7E7),
        contentColor = Color.White
    ) {
        navItems.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(imageVector = item.icon, contentDescription = "") },
                selected = false,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                alwaysShowLabel = false
            )
        }
    }
}