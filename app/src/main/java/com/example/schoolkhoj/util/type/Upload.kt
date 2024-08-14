package com.example.schoolkhoj.util.type

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.storage
import java.util.UUID

class Upload {
    companion object {
        fun uploadToGoogleStorage(uri: Uri, context: Context) {
            val storage = Firebase.storage
            val storageRef = storage.reference

            val uuid = UUID.randomUUID().toString()
            var spaceRef: StorageReference = storageRef.child("file/$uuid.jpg")

            val byteArray: ByteArray? = context.contentResolver
                .openInputStream(uri)
                .use { it?.readBytes() }

            byteArray?.let{

                val uploadTask = spaceRef.putBytes(byteArray)
                uploadTask.addOnFailureListener {
                    Toast.makeText(
                        context,
                        "Upload Failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }.addOnSuccessListener {
                    it.storage.downloadUrl.addOnSuccessListener { downloadUri ->
                        Log.i(
                            "Download URI",
                            downloadUri.toString()
                        ) }
                    Toast.makeText(
                        context,
                        "Upload Success",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }
    }
}