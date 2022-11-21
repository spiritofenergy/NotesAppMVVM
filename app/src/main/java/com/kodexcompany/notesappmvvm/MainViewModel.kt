package com.kodexcompany.notesappmvvm

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.kodexcompany.notesappmvvm.database.firebase.AppFirebaseRepository
import com.kodexcompany.notesappmvvm.database.room.dao.AppRoomDatabase
import com.kodexcompany.notesappmvvm.database.room.dao.repository.RoomRepository
import com.kodexcompany.notesappmvvm.model.Note
import com.kodexcompany.notesappmvvm.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    val context = application

    fun initDatabase(type: String, onSuccess: () -> Unit) {
        Log.d("checkData1", "MainViewModal InitDatabase with type $type")
        when (type) {
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(context = context).getRoomDao()
                REPOSITORY = RoomRepository(dao)
                Log.d("checkData1", "MainViewModal InitDatabase RoomRepository $type")
                    onSuccess()
            }
            TYPE_FIREBASE ->{
                Log.d("checkData", "init TYPE_FIREBASE")
                REPOSITORY = AppFirebaseRepository()
                REPOSITORY.connectToDatabase(
                    { onSuccess()},
                    { Log.d("checkData", "Error4: ${it}") }
                )
            }
        }
    }

    fun addNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.create(note = note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun reedAllNotes() = REPOSITORY.readAll

    fun updateNote(note: Note, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            REPOSITORY.update(note = note){
                viewModelScope.launch(Dispatchers.Main){
                    onSuccess()
                }
            }
        }
    }

    fun deleteNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.delete(note = note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun singOut(onSuccess: () -> Unit){
        when (DB_TYPE.value){
            TYPE_FIREBASE,
                TYPE_ROOM -> {
                    REPOSITORY.signOut()
                    DB_TYPE.value = Constants.Keys.EMPTY
                onSuccess()
                }
            else -> { Log.d("checkData", "singOut: ELSE: ${DB_TYPE.value}")}
        }
    }

    class MainViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {

            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                return MainViewModel(application = application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}


/*
    private val context = application

    fun initDatabase(type: String, onSuccess: () -> Unit) {
       Log.d("checkDat", "ViewModel initDatabase with type: $type")
        when (type) {
            TYPE_ROOM -> {
            val dao = AppRoomDatabase.getInstance(context = context).getRoomDao()
                REPOSITORY = RoomRepository(dao)
                Log.d("checkData", "InitDatabase Type Room")

                onSuccess }
            SAFE_IN_FIREBASE -> {
               Log.d("checkData", "InitDatabase TYPE FIREBASE")
               REPOSITORY = AppFirebaseRepository()
                REPOSITORY.connectToDatabase(
                        { onSuccess()},
                       { Log.d("checkData","Error4: ${it}")}
                    )
                }
            }
        }
        fun addNotes(note: com.kodexcompany.notesappmvvm.navigation.Note, onSuccess: () -> Unit) {
            viewModelScope.launch(Dispatchers.IO) {
                REPOSITORY.create(note = note) {
                    viewModelScope.launch(Dispatchers.com.kodexcompany.notesappmvvm.navigation.Main) {
                        onSuccess
                    }
                }
            }
        }
    fun updateNote(note: com.kodexcompany.notesappmvvm.navigation.Note,onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            REPOSITORY.update(note = note){
                viewModelScope.launch(Dispatchers.com.kodexcompany.notesappmvvm.navigation.Main){
                    onSuccess
                }
            }
        }
    }
    fun deleteNote(note: com.kodexcompany.notesappmvvm.navigation.Note,onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            REPOSITORY.delete(note = note){
                viewModelScope.launch(Dispatchers.com.kodexcompany.notesappmvvm.navigation.Main){
                    onSuccess
                }
            }
        }
    }
    fun reedAllNotes() = REPOSITORY.readAll
    }
*/


