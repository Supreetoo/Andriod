package com.example.schoolkhoj.pages

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontFamily.Companion.Serif
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.schoolkhoj.R
import com.example.schoolkhoj.data.School
import com.google.gson.Gson

@Composable
fun SchoolCard(
    modifier: Modifier = Modifier,
    imageRes: String?,
    schoolName: String?,
    boardType: String?,
    coEdStatus: String?,
    grade: String?,
    navController: NavController
) {
    val school = School(
        name = schoolName,
        imageUri = mutableListOf(imageRes),
        type = boardType,
        startGrade = grade
    )
    val schoolJson = Gson().toJson(school)
    val textStyle = TextStyle(
        fontSize = 10.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.Monospace
    )
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                    navController.navigate("detail?school=${schoolJson}")
            }
            .clip(RoundedCornerShape(32.dp)),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column {
            // School Image
            AsyncImage(
                ImageRequest.Builder(LocalContext.current)
                    .data(imageRes)
                    .crossfade(true)
                    .build(),
                contentDescription = "School Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
            // Text below the image on a white background
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = schoolName.toString(),
                        style = textStyle,
                        modifier = Modifier.weight(1.5f),
                        fontSize = 10.sp,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = boardType.toString(),
                        color = Color.Gray,
                        fontSize = 10.sp,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "$grade",
                        color = Color.Gray,
                        fontSize = 10.sp,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.End
                    )
                }
            }
        }
    }
}
//@Preview(showSystemUi = true,uiMode = Configuration.UI_MODE_NIGHT_YES )
//@Composable
//fun SchoolCardPreview() {
//    val navController = rememberNavController()
//    SchoolCard(
//        imageRes = R.drawable.north_point,
//        schoolName = "North Point",
//        boardType = "ICSE",
//        coEdStatus = "Co-Ed",
//        grade = "10+2",
//        navController = navController
//    )
//}