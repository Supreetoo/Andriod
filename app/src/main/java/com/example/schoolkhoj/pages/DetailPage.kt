package com.example.schoolkhoj.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.schoolkhoj.AuthViewModel
import com.example.schoolkhoj.R
import com.example.schoolkhoj.data.School
import com.example.schoolkhoj.data.Faculty



sealed class DetailNavItem(val route: String, val title: String) {
    object Images : DetailNavItem("images", "Images")
    object Faculty : DetailNavItem("faculty", "Faculty")
    object FeeStructure : DetailNavItem("fee_structure", "Fee Structure")
}

@OptIn(ExperimentalMaterial3Api::class) // Handle potential experimental API warnings
@Composable
fun DetailPage(
    authViewModel: AuthViewModel,
    navController: NavHostController,
    school: School
) {
    val textStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.Monospace
    )
    val items = listOf(DetailNavItem.Images, DetailNavItem.Faculty, DetailNavItem.FeeStructure)

    val (selectedTab, setSelectedTab) = remember { mutableStateOf<DetailNavItem>(DetailNavItem.Images) }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        items.forEach { item ->
                            TextButton(onClick = { setSelectedTab(item) }) {
                                Text(
                                    text = item.title,
                                    color = if (selectedTab == item) Color.Black else Color.Gray
                                )
                            }
                        }
                    }
                }
            )
        },
        content = { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                when (selectedTab) {
                    DetailNavItem.Images -> ImageContent(school = school)
                    DetailNavItem.Faculty -> FacultyContent(school = school)
                    DetailNavItem.FeeStructure -> FeeStructureContent(school = school)
                }
            }
        }
    )
}

@Composable
fun ImageContent(school: School) {
    school.imageUri?.let { imageUris ->
        LazyColumn {
            items(imageUris) { uri ->
                uri?.let {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(it)
                            .crossfade(true)
                            .build(),
                        contentDescription = "School Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .padding(8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun FacultyContent(school: School) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        school.faculties?.let { facultyList ->
            items(facultyList) { faculty ->
                FacultyCard(faculty = faculty)
            }
        }
    }
}

@Composable
fun FacultyCard(faculty: Faculty) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp), // Space between bubbles
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Image
            Image(
                painter = painterResource(id = R.drawable.logo), // Placeholder image
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.background)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                faculty.name?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                    )
                }
                faculty.qualification?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            faculty.designation?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 8.dp)
                )
            }
        }
    }
}

@Composable
fun FeeStructureContent(school: School) {
    school.feeStructure?.let { fees ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(fees.keys.toList()) { grade ->
                Text(
                    text = "$grade: ${fees[grade]}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}
