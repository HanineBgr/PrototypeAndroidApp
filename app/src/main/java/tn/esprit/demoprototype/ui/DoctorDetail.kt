import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ButtonDefaults
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import tn.esprit.demoprototype.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            DoctorDetailScreen(navController)
        }
    }
}

@Composable
fun DoctorDetailScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Doctor Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                backgroundColor = Color(0xFFA7C7E7),
                contentColor = Color.White
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color(0xFFF2F6FF))
                    .padding(start = 16.dp, end = 16.dp, top = 25.dp)
            ) {
                DoctorInfo()

                Spacer(modifier = Modifier.height(25.dp))

                Text(
                    text = "About",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam...",
                    color = Color.Gray,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(25.dp))

                AppointmentSlots()

                Spacer(modifier = Modifier.height(30.dp))

                androidx.compose.material3.Button(
                    onClick = {

                    },
                    modifier = Modifier
                        .align(alignment =Alignment.CenterHorizontally)
                        .padding(horizontal = 50.dp)
                        .width(200.dp)
                        .height(40.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFA7C7E7)
                    )
                ) {
                    androidx.compose.material3.Text(
                        text = "Book appointment",
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }
            }
        }
    )
}

@Composable
fun DoctorInfo() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.doctor02),
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .background(Color.Gray, shape = RoundedCornerShape(50)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = "Dr. Jonhson",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = "Pediatrician",
                color = Color.Gray,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row {
                    repeat(5) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = null,
                            tint = Color(0xFFFFD700),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                Text(text = "4.7")

                Spacer(modifier = Modifier.width(30.dp))

                Icon(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(16.dp)
                )
                Text(text = "800m away", color = Color.Gray)
            }
        }
    }
}
@Composable
fun AppointmentSlots() {
    var selectedDay by remember { mutableStateOf<String?>(null) }
    var selectedTime by remember { mutableStateOf<String?>(null) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            DaySlot(day = "Mon", date = "21", isSelected = selectedDay == "Mon") {
                selectedDay = "Mon"
            }
            Spacer(modifier = Modifier.width(12.dp))

            DaySlot(day = "Tue", date = "22", isSelected = selectedDay == "Tue") {
                selectedDay = "Tue"
            }
            Spacer(modifier = Modifier.width(12.dp))

            DaySlot(day = "Wed", date = "23", isSelected = selectedDay == "Wed") {
                selectedDay = "Wed"
            }
            Spacer(modifier = Modifier.width(12.dp))

            DaySlot(day = "Thu", date = "24", isSelected = selectedDay == "Thu") {
                selectedDay = "Thu"
            }
            Spacer(modifier = Modifier.width(12.dp))

            DaySlot(day = "Fri", date = "25", isSelected = selectedDay == "Fri") {
                selectedDay = "Fri"
            }
            Spacer(modifier = Modifier.width(12.dp))

            DaySlot(day = "Sat", date = "26", isSelected = selectedDay == "Sat") {
                selectedDay = "Sat"
            }
        }


        Spacer(modifier = Modifier.height(25.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TimeSlot(time = "09:00 AM", isSelected = selectedTime == "09:00 AM") {
                selectedTime = "09:00 AM"
            }
            TimeSlot(time = "10:00 AM", isSelected = selectedTime == "10:00 AM") {
                selectedTime = "10:00 AM"
            }
            TimeSlot(time = "11:00 AM", isSelected = selectedTime == "11:00 AM") {
                selectedTime = "11:00 AM"
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TimeSlot(time = "12:00 PM", isSelected = selectedTime == "12:00 PM") {
                selectedTime = "12:00 PM"
            }

            TimeSlot(time = "01:00 PM", isSelected = selectedTime == "01:00 PM") {
                selectedTime = "01:00 PM"
            }
            TimeSlot(time = "02:00 PM", isSelected = selectedTime == "02:00 PM") {
                selectedTime = "02:00 PM"
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TimeSlot(time = "03:00 PM", isSelected = selectedTime == "03:00 PM") {
                selectedTime = "03:00 PM"
            }
            TimeSlot(time = "04:00 PM", isSelected = selectedTime == "04:00 PM") {
                selectedTime = "04:00 PM"
            }
            TimeSlot(time = "05:00 PM", isSelected = selectedTime == "05:00 PM") {
                selectedTime = "05:00 PM"
            }
        }
    }
}
@Composable
fun DaySlot(day: String, date: String, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .size(50.dp, 50.dp)
            .background(if (isSelected) Color(0xFFA7C7E7) else Color.White, shape = RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Text(
            text = day,
            fontWeight = FontWeight.Bold,
            color = if (isSelected) Color.White else Color.Black
        )
        Text(
            text = date,
            color = if (isSelected) Color.White else Color.Black
        )
    }
}

@Composable
fun TimeSlot(time: String, isSelected: Boolean, onClick: () -> Unit) {
    Text(
        text = time,
        modifier = Modifier
            .background(if (isSelected) Color(0xFFA7C7E7) else Color.White,
                shape = RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(12.dp),
        color = if (isSelected) Color.White else Color.Black,
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
fun DoctorDetailScreenPreview() {
    val navController = rememberNavController()
    DoctorDetailScreen(navController)}
