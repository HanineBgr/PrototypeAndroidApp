package com.example.medicare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import tn.esprit.demoprototype.R
import tn.esprit.demoprototype.ui.ProfileScreen

enum class FilterStatus { Upcoming, Completed, Canceled }

data class Schedule(
    val imgRes: Int,
    val doctorName: String,
    val doctorTitle: String,
    val reservedDate: String,
    val reservedTime: String,
    val status: FilterStatus
)

val schedules = listOf(
    Schedule(R.drawable.doctor01, "Dr. Anastasya Syahid", "Dental Specialist", "Monday, Aug 29", "11:00 - 12:00", FilterStatus.Upcoming),
    Schedule(R.drawable.doctor02, "Dr. Mauldya Imran", "Skin Specialist", "Monday, Sep 29", "11:00 - 12:00", FilterStatus.Upcoming),
    Schedule(R.drawable.doctor03, "Dr. Rihanna Garland", "General Specialist", "Monday, Jul 29", "11:00 - 12:00", FilterStatus.Upcoming),
    Schedule(R.drawable.doctor02,"Dr. John Doe", "Something Specialist", "Monday, Jul 29", "11:00 - 12:00", FilterStatus.Completed),
    Schedule(R.drawable.doctor03, "Dr. Sam Smith", "Other Specialist", "Monday, Jul 29", "11:00 - 12:00", FilterStatus.Canceled)
)

class ScheduleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController() // Create NavController
            ScheduleScreen(navController)        }
    }
}

@Composable
fun ScheduleScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf(FilterStatus.Upcoming) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F6FF)),
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()

                    .padding(top = 20.dp, start = 20.dp, end = 20.dp)
            ) {
                // Title added here
                Text(
                    text = "Schedule",
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        color = Color(0xFFA7C7E7)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    textAlign = TextAlign.Left
                )

                ScheduleTabs(selectedTab = selectedTab, onTabSelected = { selectedTab = it })
                Spacer(modifier = Modifier.height(20.dp))
                ScheduleList(schedules = schedules.filter { it.status == selectedTab })
            }
        }
    )
}
@Composable
fun ScheduleTabs(selectedTab: FilterStatus, onTabSelected: (FilterStatus) -> Unit) {
    val tabLabels = listOf("Upcoming", "Completed", "Canceled")
    val selectedTabIndex = FilterStatus.values().indexOf(selectedTab)

    TabRow(
        selectedTabIndex = selectedTabIndex,
        backgroundColor = Color(0xFFE6E6E6),
        contentColor = Color(0xFF2196F3),
        modifier = Modifier.clip(RoundedCornerShape(20.dp)),
    ) {
        FilterStatus.values().forEachIndexed { index, status ->
            Tab(
                selected = selectedTab == status,
                onClick = { onTabSelected(status) },
                text = {
                    Text(
                        text = tabLabels[index],
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight.Bold,
                            color = if (selectedTab == status) Color(0xFF2196F3)
                            else Color(0xFF404040),
                            fontSize = 14.sp
                        ),
                        modifier = Modifier
                            .padding(vertical = 8.dp) // Adjusted vertical padding for visibility
                            .fillMaxWidth(), // Fill full width of the tab
                        textAlign = TextAlign.Center,
                        maxLines = 1
                    )
                },
                modifier = Modifier
                    .height(40.dp) // Match the tab height
                    .padding(vertical = 0.dp) // No additional vertical padding
            )
        }
    }
}



@Composable
fun ScheduleList(schedules: List<Schedule>) {
    Column {
        schedules.forEachIndexed { index, schedule ->
            DoctorScheduleCard(schedule = schedule)
            if (index != schedules.size - 1) {
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun DoctorScheduleCard(schedule: Schedule) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp),
        shape = RoundedCornerShape(12.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = schedule.imgRes),
                    contentDescription = schedule.doctorName,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(text = schedule.doctorName, fontWeight = FontWeight.Bold)
                    Text(text = schedule.doctorTitle, color = Color.Gray, fontSize = 12.sp)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            DateTimeCard(date = schedule.reservedDate, time = schedule.reservedTime)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedButton(
                    onClick = { /* Cancel action */ },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(
                        backgroundColor = Color.Transparent,  // Keep transparent for outlined style
                        contentColor = Color(0xFFA7C7E7)  // Text color
                    ),
                    shape = RoundedCornerShape(20.dp)  // Rounded corners
                ) {
                    Text("Cancel")
                }
                Spacer(modifier = Modifier.width(8.dp))  // Reduced spacing between buttons
                Button(
                    onClick = { /* Reschedule action */ },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFA7C7E7),  // Blue background
                        contentColor = Color.White  // White text
                    ),
                    shape = RoundedCornerShape(20.dp)  // Rounded corners
                ) {
                    Text("Reschedule")
                }
            }
        }
    }
}


@Composable
fun DateTimeCard(date: String, time: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF1F1F1), shape = RoundedCornerShape(10.dp))
            .padding(12.dp)  // Reduced padding for DateTimeCard
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.CalendarToday, contentDescription = "Date", tint = Color(0xFF2196F3), modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = date, fontSize = 12.sp, fontWeight = FontWeight.Bold,
                    color = Color(0xFF2196F3))
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.AccessTime, contentDescription = "Time", tint = Color(0xFF2196F3), modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = time, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF2196F3))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SchedulePreview() {
    val navController = rememberNavController()
    ScheduleScreen(navController) }
