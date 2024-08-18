package com.gianghv.android.repository.api

import com.gianghv.android.domain.User
import com.gianghv.android.repository.FakeData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

interface FirebaseApi {
    suspend fun login(email: String, password: String): User
}

class FirebaseApiImpl() : FirebaseApi {
    private val firebase: DatabaseReference = FirebaseDatabase.getInstance().getReference()
    val database = firebase.database.reference

    override suspend fun login(email: String, password: String): User {
        FakeData.researchers.forEach {
            if (it.email == email) {
                if (it.password == password) {
                    return it
                }
            }
        }
        FakeData.supervisors.forEach {
            if (it.email == email) {
                if (it.password == password) {
                    return it
                }
            }
        }
        throw Exception("User not found")
    }
}
