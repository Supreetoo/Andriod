import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.schoolkhoj.AuthState
import com.example.schoolkhoj.AuthViewModel
import com.example.schoolkhoj.data.Faculty

@SuppressLint("MutableCollectionMutableState")
@Composable
fun AddSchool(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val authState = authViewModel.authState.observeAsState()

    var name: String by remember { mutableStateOf("") }
    var address: String by remember { mutableStateOf("") }
    var city: String by remember { mutableStateOf("") }
    var state: String by remember { mutableStateOf("") }
    var type: String by remember { mutableStateOf("") }
    var startGrade: String by remember { mutableStateOf("") }
    var endGrade: String by remember { mutableStateOf("") }
    var faculties: List<Faculty> by remember { mutableStateOf(mutableListOf()) }
    var feeStructure: HashMap<String, Int> by remember { mutableStateOf(HashMap()) }

    val textStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.Monospace
    )

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Unauthenticated -> {
                navController.popBackStack()
                navController.navigate("login")
            }
            else -> Unit
        }
    }

    Surface(modifier = modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Add School",
                style = textStyle
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name", style = textStyle) },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                shape = RoundedCornerShape(8.dp),
                textStyle = textStyle
            )

            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Address", style = textStyle) },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                shape = RoundedCornerShape(8.dp),
                textStyle = textStyle
            )

            OutlinedTextField(
                value = city,
                onValueChange = { city = it },
                label = { Text("City", style = textStyle) },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                shape = RoundedCornerShape(8.dp),
                textStyle = textStyle
            )

            OutlinedTextField(
                value = state,
                onValueChange = { state = it },
                label = { Text("State", style = textStyle) },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                shape = RoundedCornerShape(8.dp),
                textStyle = textStyle
            )

            OutlinedTextField(
                value = type,
                onValueChange = { type = it },
                label = { Text("Type", style = textStyle) },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                shape = RoundedCornerShape(8.dp),
                textStyle = textStyle
            )

            OutlinedTextField(
                value = startGrade,
                onValueChange = { startGrade = it },
                label = { Text("Start Grade", style = textStyle) },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                shape = RoundedCornerShape(8.dp),
                textStyle = textStyle
            )

            OutlinedTextField(
                value = endGrade,
                onValueChange = { endGrade = it },
                label = { Text("End Grade", style = textStyle) },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                shape = RoundedCornerShape(8.dp),
                textStyle = textStyle
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.weight(1f).padding(8.dp)
                ) {
                    Text("Upload Image", style = textStyle)
                }

                Button(
                    onClick = { /* Handle submit action */ },
                    modifier = Modifier.weight(1f).padding(8.dp)
                ) {
                    Text("Submit", style = textStyle)
                }
            }
        }
    }
}
