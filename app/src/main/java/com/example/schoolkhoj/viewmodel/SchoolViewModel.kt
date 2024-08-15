package com.example.schoolkhoj.viewmodel

import androidx.lifecycle.ViewModel
import com.example.schoolkhoj.data.School
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SchoolViewModel : ViewModel() {
    private val databaseReference = Firebase.database.reference.child("school")
    private val _items = MutableStateFlow<List<School>>(emptyList())
    val items: StateFlow<List<School>> = _items

    private var lastItem: DataSnapshot? = null
    var hasMoreData = true

    fun loadMoreItems(): List<School> {
        if (hasMoreData) {
            val query = if (lastItem != null) {
                databaseReference.orderByKey().startAfter(lastItem!!.key)
                    .limitToFirst(PAGE_SIZE + 1)
            } else {
                databaseReference.orderByKey().limitToFirst(PAGE_SIZE + 1)
            }

            query.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val newItems = mutableListOf<School>()
                    if (snapshot.exists()) {
                        lastItem = snapshot.children.lastOrNull()
                        snapshot.children.forEach { childSnapshot ->
                            val item = childSnapshot.getValue<School>()
                            if (item != null) {
                                newItems.add(item)
                            }
                        }
                        if (newItems.size > PAGE_SIZE) {
                            newItems.removeAt(PAGE_SIZE)
                            hasMoreData = true
                        } else {
                            hasMoreData = false
                        }
                        _items.value += newItems
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    error.toException().printStackTrace()
                }
            })
        }
        return _items.value
    }

    companion object {
        private const val PAGE_SIZE = 20
    }
}