package com.kodexcompany.notesappmvvm.database.firebase

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.kodexcompany.notesappmvvm.database.DatabaseRepository
import com.kodexcompany.notesappmvvm.model.Note
import com.kodexcompany.notesappmvvm.utils.LOGIN
import com.kodexcompany.notesappmvvm.utils.PASSWORD

class AppFirebaseRepository:  DatabaseRepository {

    private val mAuth = FirebaseAuth.getInstance()
      override val readAll: LiveData<List<Note>>
        get() = TODO("Not yet implemented")

      override suspend fun create(note: Note, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

      override suspend fun update(note: Note, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

      override suspend fun delete(note: Note, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

      override fun signOut() {
        mAuth.signOut()
    }

    override fun connectToDatabase(onSuccess: () -> Unit, onFile: (String) -> Int) {
        mAuth.signInWithEmailAndPassword(LOGIN, PASSWORD)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener{
        mAuth.createUserWithEmailAndPassword(LOGIN, PASSWORD)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener{onFile(it.message.toString())}
            }
        }
    }
