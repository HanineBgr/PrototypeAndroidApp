package tn.esprit.demoprototype.ui


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Coronavirus
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.medicare.ScheduleScreen
import tn.esprit.demoprototype.R
import tn.esprit.demoprototype.model.Article
import tn.esprit.demoprototype.model.Doctor
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
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        AppNavGraph(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun AppNavGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(navController = navController, startDestination = "home", modifier = modifier) {
        composable("home") {
            HomeScreen(navController)
        }
        composable("schedule") {
            ScheduleScreen(navController)
        }
        composable("loginScreen") {
            ProfileScreen(navController)
        }
        composable("profile") {
            ProfileScreen(navController)
        }


    }
}

@Composable
fun HomeScreen(navController: NavHostController , modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE7F0FA))
            .padding(16.dp)
    ) {
        TopBar()
        Spacer(modifier = Modifier.height(16.dp))
        SearchBar()
        Spacer(modifier = Modifier.height(16.dp))
        ServicesRow()
        Spacer(modifier = Modifier.height(16.dp))
        HealthPromotionCard()
        Spacer(modifier = Modifier.height(16.dp))
        TopDoctorsSection(navController)
        Spacer(modifier = Modifier.height(16.dp))

        HealthArticlesSection()
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Hello Hanine Bouguerra 👋",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Image(
            painter = painterResource(id = R.drawable.notification_icon ),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(22.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchBar() {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent, RoundedCornerShape(100.dp)),
        placeholder = {
            Text(text = "Search a doctor or health issue")
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            //containerColor = Color.Transparent,
            focusedBorderColor = Color(0xFFA7C7E7),
            unfocusedBorderColor = Color(0xFFA7C7E7),
        ),
        shape = RoundedCornerShape(100.dp)
    )
}

@Composable
fun ServicesRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ServiceItem("Covid 19", Icons.Outlined.Coronavirus)
        ServiceItem("Hospital", Icons.Filled.LocalHospital)
        ServiceItem("Ambulance", Icons.Filled.CarRental)
        ServiceItem("Pill", Icons.Filled.LocalPharmacy)
    }
}

@Composable
fun ServiceItem(serviceName: String, icon: ImageVector) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "$serviceName Icon",
            modifier = Modifier.size(40.dp),
            tint = Color(0xFFA7C7E7)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = serviceName, fontSize = 14.sp)
    }
}

@Composable
fun HealthPromotionCard() {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .background(Color(0xFE2F0F2))
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.doctors),
                    contentDescription = "Doctor Promotion",
                    modifier = Modifier.size(100.dp),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "Early protection for your family health!",
                        color = Color.Gray,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    androidx.compose.material3.Button(
                        onClick = { /* Handle login logic here */ },
                        modifier = Modifier
                            .width(185.dp)
                            .height(35.dp)
                            .padding(horizontal = 16.dp),
                        shape = RoundedCornerShape(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB0C4DE))
                    ) {
                        androidx.compose.material3.Text(
                            text = "Get Started !",
                            fontSize = 18.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun TopDoctorsSection(navController: NavHostController) {
    val doctors = listOf(
        Doctor("Dr. Smith", "Cardiologist", 4.9, "2 km", R.drawable.doctor01),
        Doctor("Dr. Johnson", "Pediatrician", 4.7, "3 km", R.drawable.doctor02),
        Doctor("Dr. Williams", "Dermatologist", 4.6, "5 km", R.drawable.doctor03)
        // Add more doctors as needed
    )

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Top doctors", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            TextButton(onClick = { /* View All */ }) {
                Text(text = "See All", color = Color(0xFF4D80E4))
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 12.dp)
        ) {
            items(doctors) { doctor ->
                DoctorCard(
                    name = doctor.name,
                    specialty = doctor.specialty,
                    rating = doctor.rating,
                    distance = doctor.distance,
                    imageResId = doctor.imageResId,
                    navController = navController
                )
            }
        }
    }
}


@Composable
fun DoctorCard(name: String, specialty: String, rating: Double, distance: String, imageResId: Int, navController: NavHostController) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .width(150.dp)
            .padding(4.dp)
            .clickable {
                navController.navigate("doctorDetails/$name")

            }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = "Doctor Profile Image",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape),

                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                text = specialty,
                color = Color.Gray,
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = tn.esprit.demoprototype.R.drawable.login), // Change to your image resource
                    contentDescription = "Rating",
                    tint = Color(0xFFFFD700),
                    modifier = Modifier.size(16.dp)
                )
                Text(text = "$rating", fontSize = 12.sp)
                Spacer(modifier = Modifier.width(12.dp))
                Icon(
                    painter = painterResource(id = tn.esprit.demoprototype.R.drawable.login), // Change to your image resource
                    contentDescription = "Distance",
                    tint = Color.Gray,
                    modifier = Modifier.size(16.dp)
                )
                Text(text = distance, fontSize = 12.sp)
            }
        }
    }
}
@Composable
fun HealthArticlesSection() {
    // Define a static list of articles
    val articles = listOf(

        Article(
            title = "Healthy Eating Habits",
            author = "Dr. Adem Nasr",
            date = "Oct 14, 2023",
            imageResId = tn.esprit.demoprototype.R.drawable.hospital
        ),
        Article(
            title = "Understanding Mental Health",
            author = "Dr. Jane Smith",
            date = "Oct 16, 2023",
            imageResId = R.drawable.hospital
        )
    )

    Column {
        Text(
            text = "Health Articles",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(articles) { article ->
                ArticleCard(
                    title = article.title,
                    author = article.author,
                    date = article.date,
                    imageResId = article.imageResId
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun ArticleCard(title: String, author: String, date: String, imageResId: Int) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
        ) {

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.2f)
            ) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = "Article Image",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(8.dp)
                    .weight(0.7f)
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "By $author",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
                Text(
                    text = date,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}


data class BottomNavItem(val route: String,  val icon: ImageVector)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp()
}
