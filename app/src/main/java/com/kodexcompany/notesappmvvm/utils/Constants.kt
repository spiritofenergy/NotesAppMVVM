package com.kodexcompany.notesappmvvm.utils

import androidx.compose.runtime.mutableStateOf
import com.kodexcompany.notesappmvvm.database.DatabaseRepository

const val TYPE_DATABASE = "type_database"
const val TYPE_ROOM = "type_room"
const val TYPE_FIREBASE = "type_firebase"
const val FIREBASE_ID = "firebaseId"


  lateinit var REPOSITORY: DatabaseRepository
  lateinit var LOGIN: String
  lateinit  var PASSWORD: String
  var DB_TYPE = mutableStateOf("")

object Constants{
    object Keys{
        const val NOTES_DATABASE ="notes_database"
        const val NOTES_TABLE ="notes_table"
        const val NOTE_TITLE ="Заметка"
        const val NOTE_SUBTITLE ="Подробнее"
        const val ADD_NOTE ="Новое дело"
        const val SAFE_NOTE ="Сохранить дело"
        const val TITLE ="title"
        const val SUBTITLE ="subtitle"
        const val WAT_WELL_WE_YSE ="Сохранить"
        const val ROOM_DATABASE ="Room_database"
        const val FIREBASE_DATABASE ="Firebase_database"
        const val ID ="Id"
        const val NOTE ="note"
        const val UPDATE_NOTE ="Изменить"
        const val DELETE ="Удалить"
        const val NAV_BECK ="Назад"
        const val EDIT_NOTE ="Изменить"
        const val LOG_IN ="Log in"
        const val EMPTY =""
        const val SAFE_ROOM ="Room"
        const val SAFE_FIREBASE ="Firebase"
        const val SING_IN ="Sign in"
        const val LOGIN_TEXT ="Логин"
        const val PASSWORD_TEX ="Пароль"
    }
    object Screens{
        const val START_SCREEN = "start_screen"
        const val MAIN_SCREEN = "main_screen"
        const val ADD_SCREEN = "add_screen"
        const val NOTE_SCREEN = "note_screen"


    }
}