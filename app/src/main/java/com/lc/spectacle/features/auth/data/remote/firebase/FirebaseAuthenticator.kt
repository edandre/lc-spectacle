package com.lc.spectacle.features.auth.data.remote.firebase

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.lc.spectacle.features.auth.data.remote.dto.UserDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class FirebaseAuthenticator {
    private val firebaseAuth = FirebaseAuth.getInstance()

    fun register(user: UserDto): AuthResult? {
        return try {
            runBlocking (Dispatchers.IO) {
                Tasks.await(
                    firebaseAuth.createUserWithEmailAndPassword(
                        user.email,
                        user.password
                    )
                )
            }
        } catch (e: Exception) {
            Log.e("RegisterError", "${e.message}\n${e.stackTraceToString()}\n")
            null
        }
    }

    fun authenticate(user: UserDto): AuthResult? {
        return try {
            runBlocking (Dispatchers.IO) {
                Tasks.await(
                    firebaseAuth.signInWithEmailAndPassword(
                        user.email,
                        user.password
                    )
                )
            }
        } catch (e: Exception) {
            Log.e("AuthError", "${e.message}\n${e.stackTraceToString()}\n")
            null
        }
    }
}