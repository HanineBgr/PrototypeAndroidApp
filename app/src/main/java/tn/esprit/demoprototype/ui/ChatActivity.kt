import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.ui.draw.clip
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import tn.esprit.demoprototype.R
import tn.esprit.demoprototype.model.Message

@Composable
fun ChatMessage(message: Message) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = if (message.isSentByMe) Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = if (message.isSentByMe) Color(0xFFCCE5FF) else Color(0xFFE0E0E0),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(12.dp)
        ) {
            Column {
                Text(text = message.text, style = TextStyle(fontSize = 16.sp))
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = message.time, style = TextStyle(fontSize = 12.sp, color = Color.Gray))
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(navController: NavController) {
    var messages by remember { mutableStateOf(listOf<Message>()) }
    var currentMessage by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 56.dp)
    ) {
        TopAppBar(
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color.Gray, shape = CircleShape)
                            .padding(2.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text("Hanine Bouguerra", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        Text("online", fontSize = 14.sp, color = Color.White)
                    }
                }
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFA7C7E7)),
            modifier = Modifier.fillMaxWidth()
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 8.dp)
                .fillMaxWidth()
        ) {
            messages.forEach { message ->
                ChatMessage(message)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Default.CameraAlt, contentDescription = "Camera", tint = Color(0xFF9EACFF))
            }

            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Default.AttachFile, contentDescription = "Attach File", tint = Color(0xFF9EACFF))
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(20.dp))
                    .padding(12.dp)
            ) {
                if (currentMessage.text.isEmpty()) {
                    Text(text = "Type a message", color = Color.Gray, fontSize = 16.sp)
                }
                BasicTextField(
                    value = currentMessage,
                    onValueChange = { currentMessage = it },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(fontSize = 16.sp)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = {
                    if (currentMessage.text.isNotEmpty()) {
                        messages = messages + Message(currentMessage.text, isSentByMe = true)
                        currentMessage = TextFieldValue("")
                    }
                },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(imageVector = Icons.Default.Send, contentDescription = "Send", tint = Color(0xFF9EACFF))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    val navController = rememberNavController()
    ChatScreen(navController)
}
