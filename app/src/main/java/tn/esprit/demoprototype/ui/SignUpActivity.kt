import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import tn.esprit.demoprototype.R

@Composable
fun SignUpScreen(navController: NavHostController) {
    var firstName by remember { mutableStateOf(TextFieldValue("")) }
    var lastName by remember { mutableStateOf(TextFieldValue("")) }
    var selectedGender by remember { mutableStateOf("Male") }
    var selectedDate by remember { mutableStateOf("Select your date of birth") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE7F0FA))
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.subscribe),
            contentDescription = "Subscribe banner",
            modifier = Modifier
                .width(250.dp)
                .height(250.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("First Name") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "First Name Icon",
                    tint = Color(0xFFA7C7E7)
                )
            },
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Last Name") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Last Name Icon",
                    tint = Color(0xFFA7C7E7)
                )
            },
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = selectedDate,
            onValueChange = { selectedDate = it },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = "Birth Icon",
                    tint = Color(0xFFA7C7E7)
                )
            },
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.wrapContentWidth()
            ) {
                GenderSelectionOption(
                    label = "Male",
                    isSelected = selectedGender == "Male",
                    onSelect = { selectedGender = "Male" }
                )

                GenderSelectionOption(
                    label = "Female",
                    isSelected = selectedGender == "Female",
                    onSelect = { selectedGender = "Female" }
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                navController.navigate("home")
            },
            modifier = Modifier
                .width(250.dp)
                .height(50.dp)
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA7C7E7))
        ) {
            Text(text = "Sign Up", fontSize = 18.sp, color = Color.White)
        }
    }
}

@Composable
fun GenderSelectionOption(
    label: String,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .border(
                width = 2.dp,
                color = if (isSelected) Color(0xFF6EB5FF) else Color.LightGray,
                shape = RoundedCornerShape(8.dp)
            )
            .background(Color.Transparent)
            .clickable(onClick = onSelect)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(
                    id = if (label == "Male") R.drawable.male else R.drawable.female
                ),
                contentDescription = label,
                tint = if (isSelected) Color(0xFF6EB5FF) else Color.Black,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = label,
                color = if (isSelected) Color(0xFF6EB5FF) else Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    val navController = rememberNavController()
    SignUpScreen(navController)
}
