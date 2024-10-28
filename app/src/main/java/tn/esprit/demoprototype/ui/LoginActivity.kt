import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import tn.esprit.demoprototype.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE7F0FA)) // Set the background color
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Image for login illustration
            Image(
                painter = painterResource(id = R.drawable.login),
                contentDescription = "Login Illustration",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Username Input Field
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Username Icon",
                        tint = Color(0xFFA7C7E7)
                    )
                },
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password Input Field
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = "Password Icon",
                        tint = Color(0xFFA7C7E7)
                    )
                },
                visualTransformation = PasswordVisualTransformation(),
                //keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Login Button
            Button(
                onClick = {
                    // Handle login logic here, and navigate to the next screen if successful
                    navController.navigate("home") // Navigate to home screen
                },
                modifier = Modifier
                    .width(250.dp)
                    .height(50.dp)
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA7C7E7))
            ) {
                Text(text = "Login", fontSize = 18.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Link to Register
            TextButton(onClick = {
                navController.navigate("roleSelection")
            }) {
                Text("Don't have an account? Register", color = Color(0xFFA7C7E7))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController() // Mock NavController for Preview
    LoginScreen(navController) // Pass the mocked NavController to the LoginScreen
}
