package com.kodexcompany.notesappmvvm.database.room.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kodexcompany.notesappmvvm.database.room.dao.repository.NoteRoomDao
import com.kodexcompany.notesappmvvm.model.Note
import com.kodexcompany.notesappmvvm.utils.Constants.Keys.NOTES_DATABASE

@Database(entities = [Note::class], version = 1)
abstract class  AppRoomDatabase : RoomDatabase(){


   abstract fun getRoomDao(): NoteRoomDao


    companion object{

        @Volatile
        private var INSTANCE : AppRoomDatabase? = null

        fun getInstance(context: Context): AppRoomDatabase {
            return if (INSTANCE == null ) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppRoomDatabase::class.java,
                    NOTES_DATABASE
                ).build()
                INSTANCE as AppRoomDatabase
            }else INSTANCE as AppRoomDatabase
        }

    }
}