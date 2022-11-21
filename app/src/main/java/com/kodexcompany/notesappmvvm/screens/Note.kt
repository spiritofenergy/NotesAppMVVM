package com.kodexcompany.notesappmvvm.screens

import android.app.Application
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ModalBottomSheetValue.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kodexcompany.notesappmvvm.MainViewModel
import com.kodexcompany.notesappmvvm.model.Note
import com.kodexcompany.notesappmvvm.ui.theme.NotesAppMVVMTheme
import com.kodexcompany.notesappmvvm.utils.Constants
import com.kodexcompany.notesappmvvm.utils.Constants.Keys.EDIT_NOTE
import com.kodexcompany.notesappmvvm.utils.DB_TYPE
import com.kodexcompany.notesappmvvm.utils.TYPE_FIREBASE
import com.kodexcompany.notesappmvvm.utils.TYPE_ROOM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoteScreen(navController: NavController, viewModel: MainViewModel, noteId: String?) {
    val notes = viewModel.reedAllNotes().observeAsState(listOf()).value
    val note = when(DB_TYPE.value){
        TYPE_ROOM -> {
            notes.firstOrNull{ it.id == noteId?.toInt() } ?: Note()
        }
        TYPE_FIREBASE -> {
            notes.firstOrNull{ it.firebaseId == noteId} ?: Note()
        }
        else -> Note()
    }
    val bottomSheetState = rememberModalBottomSheetState(Hidden)
    val coroutineScope = rememberCoroutineScope()

    var title by remember {mutableStateOf(Constants.Keys.EMPTY)}
    var subtitle by remember {mutableStateOf(Constants.Keys.EMPTY)}

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetContent = {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 32.dp)
                ) {
                    Text(
                        text = EDIT_NOTE,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    OutlinedTextField(
                        value = title ,
                        onValueChange = { title = it },
                        label = { Text(text = Constants.Keys.TITLE)},
                        isError = title.isEmpty()
                    )
                    OutlinedTextField(
                        value = subtitle ,
                        onValueChange = { subtitle = it },
                        label = { Text(text = Constants.Keys.SUBTITLE)},
                        isError = subtitle.isEmpty()
                    )
                    Button(modifier = Modifier.padding(top = 26.dp),
                        onClick = {
                                viewModel.updateNote(note =
                            Note(id = note.id, title = title, subtitle = subtitle, firebaseId = note.firebaseId )
                            ){
                                navController.navigate(Constants.Screens.MAIN_SCREEN)
                            }
                        }) {
                        Text(text = Constants.Keys.UPDATE_NOTE)

                    }
                }
            }
        }) {
        Scaffold(modifier = Modifier.fillMaxSize(

        )) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center)
            {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(vertical = 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = note.title,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 8.dp))
                        Text(
                            text = note.subtitle,
                            fontWeight = FontWeight.Light,
                            modifier = Modifier.padding(top = 16.dp))
                    }

                }

                Row(modifier = Modifier
                    .padding(32.dp)
                    .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround)
                {
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                title = note.title
                                subtitle = note.subtitle
                                bottomSheetState.show()
                            }
                            Log.d("CheckData", "ADD_NOTE")
                        }) {

                        Text(text = Constants.Keys.ADD_NOTE)
                    }
                    Button(
                        onClick = {
                            Log.d("CheckData", "UPDATE_NOTE")
                        }) {

                        Text(text = Constants.Keys.UPDATE_NOTE)
                    }
                }
                Button(modifier = Modifier
                    .padding(16.dp)
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth(),
                    onClick = {
                        viewModel.deleteNote(note = note){
                        navController.navigate(Constants.Screens.MAIN_SCREEN)}
                        Log.d("CheckData", "DELETE")
                    }) {
                    Text(text = Constants.Keys.DELETE)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun PrevNoteScreen(){
     NotesAppMVVMTheme {
         val context = LocalContext.current
         val mViewModal: MainViewModel =
             viewModel(factory = MainViewModel.MainViewModelFactory(context
                 .applicationContext as Application
             ))
          NoteScreen(
              navController = rememberNavController(),
              viewModel = mViewModal,
              noteId = "1"
          )
     }
}    

/*
    val notes = viewModel.reedAllNotes().observeAsState(listOf()).value
    val note = notes.firstOrNull { it.id == noteId?.toInt() } ?: com.kodexcompany.notesappmvvm.navigation.Note(title = Constants.Keys.NOTE, subscribe = Constants.Keys.NOTE)
    val bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    var title by remember { mutableStateOf(Constants.Keys.EMPTY)}
    var subtitle by remember { mutableStateOf(Constants.Keys.EMPTY)}

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetContent = {
             Surface {
                 Column(
                     modifier = Modifier
                         .fillMaxWidth()
                         .padding(all = 32.dp)
                 ) {
                        Text(text = EDIT_NOTE,
                            Modifier.padding(vertical = 8.dp),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                     OutlinedTextField(
                         value = title,
                         onValueChange = {title = it},
                         label = {Text(text = TITLE)},
                         isError = title.isEmpty()
                     )
                     OutlinedTextField(
                         value = subtitle,
                         onValueChange = {subtitle = it},
                         label = {Text(text = SUBTITLE)},
                         isError = subtitle.isEmpty()
                     )
                     Button(
                         modifier = Modifier.padding(16.dp),
                         onClick = {
                             viewModel.updateNote(note = com.kodexcompany.notesappmvvm.navigation.Note(id = note.id, title = title, subscribe = subtitle)
                             ){
                                 navController.navigate(MAIN_SCREEN)
                             }
                         }
                     ){
                        Text(text = UPDATE_NOTE)
                     }
                     
                 }
             }
        }) {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            )
            {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp)

                ) {
                    Column(
                        modifier = Modifier.padding(vertical = 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = note.title,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 32.dp)
                        )
                        Text(
                            text = note.subscribe,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Light,
                            modifier = Modifier.padding(top = 16.dp)
                        )

                    }
                }
                Row(
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Button(onClick = {
                        coroutineScope.launch{
                            title = note.title
                            subtitle = subtitle
                            bottomSheetState.show()
                        }
                    }) {
                        Text(text = Constants.Keys.UPDATE_NOTE)
                    }

                    Button(onClick = {
                        viewModel.deleteNote(note = note){
                            navController.navigate(MAIN_SCREEN)
                        }
                    }) {
                        Text(text = Constants.Keys.DELETE)
                    }
                }
                Button(modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth(),
                    onClick = {
                        navController.navigate(MAIN_SCREEN)
                    }) {
                    Text(text = Constants.Keys.NAV_BECK)
                }
            }
        }
    }

}

    @OptIn(ExperimentalMaterialApi::class)
    @Preview(showBackground = true)
    @Composable
    fun PrevNoteScreen() {
        NotesAppMVVMTheme() {
            val context = LocalContext.current
            val mViewModal: MainViewModel =
                viewModel(factory = MainViewModelFactory(context
                    .applicationContext as Application
                )
                )
            NoteScreen(
                navController = rememberNavController(),
                viewModel = mViewModal,
                noteId = "1"
            )
        }
    }
*/
