package com.example.schoolkhoj.pages

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import com.example.schoolkhoj.AuthState
import com.example.schoolkhoj.AuthViewModel
import com.example.schoolkhoj.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import com.example.schoolkhoj.data.Faculty
import com.example.schoolkhoj.data.Grade
import com.example.schoolkhoj.data.School
import com.example.schoolkhoj.util.type.SchoolType
import com.example.schoolkhoj.viewmodel.SchoolViewModel
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import kotlinx.coroutines.tasks.await
import okhttp3.internal.wait
import java.util.UUID

data class School(
    val imageRes: Int? = null,
    val schoolName: String? = null,
    val boardType: String? = null,
    val coEdStatus: String? = null,
    val grade: String? = null
)

@SuppressLint("MutableCollectionMutableState")
@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel,
    viewModel: SchoolViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val authState = authViewModel.authState.observeAsState()
    val searchQuery = remember { mutableStateOf(TextFieldValue("")) }

    val items by viewModel.items.collectAsState()
    val listState = rememberLazyListState()
    val schools by remember { mutableStateOf(mutableListOf<School>()) }
    val database: DatabaseReference = Firebase.database.reference.child("school")
    database.get().addOnSuccessListener {
        it.children.forEach { school ->
            school.getValue<School>()?.let { it1 -> schools.add(it1) }
        }
    }
    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Unauthenticated -> navController.navigate("login")
            else -> Unit
        }
    }

//    LaunchedEffect(remember { derivedStateOf { listState.firstVisibleItemIndex } }) {
//        val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
//        if (lastVisibleItem != null && lastVisibleItem.index == items.size - 1) {
//            viewModel.loadMoreItems()
//        }
//    }
//    LazyColumn(state = listState) {
//        items(items) { school ->
//            school.name?.let {
//                SchoolCard(
//                    imageRes = R.drawable.north_point,
//                    schoolName = it,
//                    boardType = school.type,
//                    coEdStatus = school.startGrade,
//                    grade = school.endGrade,
//                    navController = navController
//                )
//            }
//        }
//        if (items.isNotEmpty() && viewModel.hasMoreData) {
//            item {
//                CircularProgressIndicator(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(16.dp)
//                )
//            }
//        }
//    }

//    val schools = listOf(
//        School(R.drawable.north_point, "North Point", "ICSE/CBSE", "Co-Ed", "10+2"),
//        School(R.drawable.st_xavier, "St.Xavier's", "CBSE", "Co-Ed", "10+2"),
//        School(R.drawable.sunshine, "Sunshine", "CBSE", "Co-Ed", "10+2"),
//        School(R.drawable.dav, "Dav", "CBSE", "Co-Ed", "10+2"),
//        School(R.drawable.gd, "Gd", "CBSE", "Co-Ed", "10+2")
//        // Add more schools here
//    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Search Bar
        TextField(
            value = searchQuery.value,
            onValueChange = { searchQuery.value = it },
            placeholder = { Text(text = "Search Schools...") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )


        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items.filter {
                it.name?.contains(searchQuery.value.text, ignoreCase = true) ?:false
            }) { school ->
                SchoolCard(
                    imageRes = school.imageUri?.get(0),
                    schoolName = school.name,
                    boardType = school.type,
                    coEdStatus = school.endGrade,
                    grade = school.startGrade,
                    navController = navController
                )
            }
        }
    }

//    SmallFloatingActionButton(
//        onClick = {
//            val faculties: List<Faculty> = listOf(
//                Faculty(
//                    name = "Faculty 1",
//                    qualification = "Qualification 1",
//                    designation = "Teacher"
//                ),
//                Faculty(
//                    name = "Faculty 2",
//                    qualification = "Qualification 2",
//                    designation = "Principal"
//                )
//            )
//            val feeStructure: HashMap<String, Int> = HashMap()
//            feeStructure[Grade.GRADE_ONE.grade] = 1000
//            feeStructure[Grade.GRADE_TWO.grade] = 2000
//
//            val npcs: com.example.schoolkhoj.data.School = com.example.schoolkhoj.data.School(
//                name = "North Point",
//                address = "Ramna",
//                city = "Muzaffarpur",
//                state = "Bihar",
//                type = SchoolType.PRIVATE.type,
//                startGrade = Grade.PRE_NURSERY.grade,
//                endGrade = Grade.GRADE_ELEVEN.grade,
//                faculties = faculties,
//                feeStructure = feeStructure,
//                isHostelAvailable = true,
//                imageUri = listOf("https://firebasestorage.googleapis.com/v0/b/schoolkhoj-2f548.appspot.com/o/file%2Fnpcs.jpg?alt=media&token=5cc0ff60-61ff-4638-86d2-4e20460ced16")
//            )
//            val sunshine: com.example.schoolkhoj.data.School = com.example.schoolkhoj.data.School(
//                name = "Sunshine",
//                address = "Sherpur",
//                city = "Muzaffarpur",
//                state = "Bihar",
//                type = SchoolType.PRIVATE.type,
//                startGrade = Grade.PRE_NURSERY.grade,
//                endGrade = Grade.GRADE_ELEVEN.grade,
//                faculties = faculties,
//                feeStructure = feeStructure,
//                isHostelAvailable = true,
//                imageUri = listOf("https://firebasestorage.googleapis.com/v0/b/schoolkhoj-2f548.appspot.com/o/file%2Fsunshine.jpg?alt=media&token=de9d9d02-cb83-4570-83e7-3c78b27d69a1")
//            )
//            val xaviers: com.example.schoolkhoj.data.School = com.example.schoolkhoj.data.School(
//                name = "St. Xavier's",
//                address = "Ramna",
//                city = "Muzaffarpur",
//                state = "Bihar",
//                type = SchoolType.PRIVATE.type,
//                startGrade = Grade.PRE_NURSERY.grade,
//                endGrade = Grade.GRADE_ELEVEN.grade,
//                faculties = faculties,
//                feeStructure = feeStructure,
//                isHostelAvailable = true,
//                imageUri = listOf("https://firebasestorage.googleapis.com/v0/b/schoolkhoj-2f548.appspot.com/o/file%2Fxaviers.jpeg?alt=media&token=94e2630f-f0bd-49c0-83fd-d24d985e1c8b")
//            )
//            database.child("school").child(UUID.randomUUID().toString()).setValue(npcs)
//            database.child("school").child(UUID.randomUUID().toString()).setValue(xaviers)
//            database.child("school").child(UUID.randomUUID().toString()).setValue(sunshine)
//        },
//        containerColor = MaterialTheme.colorScheme.secondaryContainer,
//        contentColor = MaterialTheme.colorScheme.secondary
//    ) {
//        Text(
//            text = "Add",
//        )
//    }
    }

//    Surface(
//        modifier = Modifier.fillMaxSize(),
//        color = MaterialTheme.colorScheme.background
//    ) {
//        LazyColumn(
//            verticalArrangement = Arrangement.spacedBy(8.dp),
//            modifier = Modifier.padding(16.dp)
//        ) {
//            items(items) { school ->
//                RecordItem(school = school)
//            }
//        }
//    }
//}
//
//@Composable
//fun RecordItem(school: School) {
//    Column(modifier = Modifier.padding(8.dp)) {
//        Text(text = "Name: ${school.name}", style = MaterialTheme.typography.bodyLarge)
//        Text(text = "Type: ${school.type}", style = MaterialTheme.typography.bodyLarge)
//        Text(text = "Address: ${school.address}", style = MaterialTheme.typography.bodyLarge)
//    }
//}

