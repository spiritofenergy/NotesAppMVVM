package com.kodexcompany.notesappmvvm.database

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.kodexcompany.notesappmvvm.model.Note
import com.kodexcompany.notesappmvvm.utils.LOGIN
import com.kodexcompany.notesappmvvm.utils.PASSWORD

interface DatabaseRepository {

    val readAll: LiveData<List<Note>>

    suspend fun create(note: Note, onSuccess: ()-> Unit)

    suspend fun update(note: Note, onSuccess: ()-> Unit)

    suspend fun delete(note: Note, onSuccess: ()-> Unit)

    fun signOut() {
       // mAuth.signOut()
    }
    fun connectToDatabase(onSuccess: () -> Unit, onFile: (String) -> Int){

    }
}

   /* private val mAuth: FirebaseAuth
        get() = FirebaseAuth.getInstance()

    val readAll: LiveData<List<Note>>

    suspend fun create(note: Note, onSuccess: ()-> Unit)

    suspend fun update(note: Note, onSuccess: ()-> Unit)

    suspend fun delete(note: Note, onSuccess: ()-> Unit)

    fun signOut() {
        mAuth.signOut()
    }

    fun connectToDatabase(onSuccess: () -> Unit, onFail: (String) -> Unit) {
        mAuth.signInWithEmailAndPassword(LOGIN, PASSWORD)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener{
                mAuth.signInWithEmailAndPassword(LOGIN, PASSWORD)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener{onFail(it.message.toString())}
            }
    }
}*/
