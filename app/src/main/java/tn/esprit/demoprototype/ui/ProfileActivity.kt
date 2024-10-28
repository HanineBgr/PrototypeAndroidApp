package tn.esprit.demoprototype.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import tn.esprit.demoprototype.R

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            ProfileScreen(navController)
        }
    }
}

@Composable
fun ProfileScreen(navController: NavController, modifier: Modifier = Modifier) {
    var notificationEnabled by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD))
            .padding(30.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Profile Header Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(55.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(text = "Hanine Bouguerra", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Text(text = "Patiente", fontSize = 14.sp, color = Color.Gray)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ProfileAttribute(value = "180cm", label = "Height")
                ProfileAttribute(value = "65kg", label = "Weight")
                ProfileAttribute(value = "22yo", label = "Age")
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Account Section with icons
            ProfileSection(title = "Account") {
                ProfileItemWithIcon("Personal Data", R.drawable.p_personal)
                ProfileItemWithIcon("Achievement", R.drawable.p_achi)
                ProfileItemWithIcon("Activity History", R.drawable.p_activity)
                ProfileItemWithIcon("Workout Progress", R.drawable.p_workout)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Notification Section with icon
            ProfileSection(title = "Notification") {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.p_notification),
                            contentDescription = "Notification Icon",
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(15.dp))
                        Text(text = "Pop-up Notification", fontSize = 12.sp)
                    }
                    Switch(
                        checked = notificationEnabled,
                        onCheckedChange = { notificationEnabled = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color(0xFFCE93D8),
                            uncheckedThumbColor = Color.LightGray
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Other Section
            ProfileSection(title = "Other") {
                ProfileItemWithIcon("Contact Us", R.drawable.p_contact)
                ProfileItemWithIcon("Privacy Policy", R.drawable.p_privacy)
                ProfileItemWithIcon("Settings", R.drawable.p_setting)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                    .padding(bottom = 55.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Logout,
                contentDescription = "Logout",
                tint = Color(0xFF90CAF9)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Logout", fontSize = 18.sp, color = Color(0xFF90CAF9))
        }

    }
}

@Composable
fun ProfileAttribute(value: String, label: String) {
    Surface(
        modifier = Modifier
            .width(80.dp)
            .height(55.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = value, color = Color(0xFF64B5F6), fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Text(text = label, color = Color.Gray, fontSize = 12.sp)
        }
    }
}

@Composable
fun ProfileSection(title: String, content: @Composable () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = title, fontWeight = FontWeight.Bold, fontSize = 14.sp, modifier = Modifier.padding(vertical = 8.dp))
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White),
            elevation = 4.dp
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                content()
            }
        }
    }
}

@Composable
fun ProfileItemWithIcon(item: String, iconId: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = iconId),
                contentDescription = "$item Icon",
                modifier = Modifier.size(12.dp)
            )
            Spacer(modifier = Modifier.width(15.dp))
            Text(text = item, fontSize = 12.sp)
        }
        Icon(
            painter = painterResource(id = R.drawable.arrow),
            contentDescription = "Next",
            tint = Color.Gray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    val navController = rememberNavController()
    ProfileScreen(navController)
}
