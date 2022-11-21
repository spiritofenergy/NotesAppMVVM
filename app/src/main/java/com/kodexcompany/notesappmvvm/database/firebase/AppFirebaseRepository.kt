package com.kodexcompany.notesappmvvm.database.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.kodexcompany.notesappmvvm.database.DatabaseRepository
import com.kodexcompany.notesappmvvm.model.Note
import com.kodexcompany.notesappmvvm.utils.Constants
import com.kodexcompany.notesappmvvm.utils.FIREBASE_ID
import com.kodexcompany.notesappmvvm.utils.LOGIN
import com.kodexcompany.notesappmvvm.utils.PASSWORD

class AppFirebaseRepository:  DatabaseRepository {

    private val mAuth = FirebaseAuth.getInstance()

    private val database = Firebase.database.reference
        .child(mAuth.currentUser?.uid.toString())

      override val readAll: LiveData<List<Note>> = AllNotesLiveData()

      override suspend fun create(note: Note, onSuccess: () -> Unit) {
           val noteId = database.push().key.toString()
           val mapNotes = hashMapOf<String, Any>()

            mapNotes[FIREBASE_ID] = noteId
            mapNotes[Constants.Keys.TITLE] = note.title
            mapNotes[Constants.Keys.SUBTITLE] = note.subtitle

          database.child(noteId)
              .updateChildren(mapNotes)
              .addOnSuccessListener{ onSuccess()
              Log.d("checkData", "addOnSuccessListener")}
              .addOnFailureListener{ Log.d("checkData", " Failed to add new note ")}
      }

      override suspend fun update(note: Note, onSuccess: () -> Unit) {
          val noteId = note.firebaseId
          val mapNotes = hashMapOf<String, Any>()

          mapNotes[FIREBASE_ID] = noteId
          mapNotes[Constants.Keys.TITLE] = note.title
          mapNotes[Constants.Keys.SUBTITLE] = note.subtitle

          database.child(noteId)
              .updateChildren(mapNotes)
              .addOnSuccessListener { onSuccess() }
              .addOnFailureListener { Log.d("checkData", "Failed to update note") }
      }

      override suspend fun delete(note: Note, onSuccess: () -> Unit) {
        database.child(note.firebaseId)
            .removeValue()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { Log.d("checkData", "Failed to delete note") }
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
