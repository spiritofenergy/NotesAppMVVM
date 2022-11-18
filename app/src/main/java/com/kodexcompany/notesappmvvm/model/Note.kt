package com.kodexcompany.notesappmvvm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kodexcompany.notesappmvvm.utils.Constants.Keys.NOTES_TABLE

@Entity(tableName = "notes_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    @ColumnInfo
    val title: String = "",
    @ColumnInfo
    val subtitle: String = "",

    val firebaseId: String =""
)
/*

@Entity(tableName = NOTES_TABLE)
data class Note (
    @PrimaryKey
    val id: Int = 0,
    @ColumnInfo
    val title: String,
    @ColumnInfo
    val subscribe: String
)*/
