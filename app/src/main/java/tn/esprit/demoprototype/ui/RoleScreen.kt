import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import tn.esprit.demoprototype.R

class RoleSelectionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AppNavGraph(navController)
        }
    }
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "roleSelection") {
        composable("roleSelection") {
            RoleSelectionScreen(navController)
        }
        composable("signup") {
            SignUpScreen(navController)
        }
    }
}

@Composable
fun RoleSelectionScreen(navController: NavHostController) {
    var selectedRole by remember { mutableStateOf("Care provider") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE7F0FA))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            // Patient Role Option
            RoleCard(
                title = "Patient",
                isSelected = selectedRole == "Patient",
                onClick = { selectedRole = "Patient" }
            )

            // Care Provider Role Option with reduced space
            RoleCard(
                title = "Care provider",
                isSelected = selectedRole == "Care provider",
                onClick = { selectedRole = "Care provider" }
            )


            Button(
                onClick = {
                    navController.navigate("signup")
                },
                modifier = Modifier
                    .padding(horizontal = 35.dp)
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFA7C7E7)
                )
            ) {
                Text(text = "Next", fontSize = 18.sp, color = Color.White)
            }


            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


// RoleCard Composable
@Composable
fun RoleCard(title: String, isSelected: Boolean, onClick: () -> Unit) {
    val borderColor = if (isSelected) Color(0xFF5AB964) else Color.Gray
    val borderWidth = if (isSelected) 2.dp else 1.dp

    Box(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(vertical = 2.dp)
            .clickable { onClick() }
            .border(borderWidth, borderColor, RoundedCornerShape(12.dp))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id =
                if (title == "Patient") R.drawable.patient else R.drawable.care_provider),
                contentDescription = "$title image",
                modifier = Modifier.size(150.dp),
                contentScale = ContentScale.Fit
            )

            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RoleSelectionScreenPreview() {
    val navController = rememberNavController()
    RoleSelectionScreen(navController)
}
