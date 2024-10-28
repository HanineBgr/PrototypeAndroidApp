package tn.esprit.demoprototype.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@Composable
fun OnBoardingScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE7F0FA))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            Image(
                painter = painterResource(id = R.drawable.doctor),
                contentDescription = "Doctor Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(250.dp)
                    .width(250.dp)
            )

            Text(
                text = "Stay Connected to Your Health",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Text(
                text = "We offer the best remote monitoring experience for both patients and care providers",
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 40.dp)
            )

            Button(
                onClick = {
                    Log.d("OnBoardingScreen", "Get Started button clicked")
                    navController.navigate("loginScreen") {
                    }
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
                Text(text = "Get Started", fontSize = 18.sp, color = Color.White)
            }

            TextButton(
                onClick = {
                    navController.navigate("roleSelection") {

                    }
                },
                modifier = Modifier.padding(top = 8.dp, bottom = 20.dp)
            ) {
                Text(
                    text = "Already a member? Register instead",
                    fontSize = 14.sp,
                    color = Color(0xFFA7C7E7)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingScreenPreview() {
    val navController = rememberNavController()
    OnBoardingScreen(navController)
}
