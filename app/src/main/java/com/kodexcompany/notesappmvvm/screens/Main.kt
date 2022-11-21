package com.kodexcompany.notesappmvvm.screens

import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kodexcompany.notesappmvvm.MainViewModel
import com.kodexcompany.notesappmvvm.MainViewModel.MainViewModelFactory
import com.kodexcompany.notesappmvvm.model.Note
import com.kodexcompany.notesappmvvm.ui.theme.NotesAppMVVMTheme
import com.kodexcompany.notesappmvvm.utils.Constants
import com.kodexcompany.notesappmvvm.utils.DB_TYPE
import com.kodexcompany.notesappmvvm.utils.TYPE_DATABASE
import com.kodexcompany.notesappmvvm.utils.TYPE_FIREBASE

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel) {
    val context = LocalContext.current
    val mViewModal: MainViewModel =
        viewModel(factory = MainViewModelFactory(context
            .applicationContext as Application
        ))
    val notes = viewModel.reedAllNotes().observeAsState(listOf()).value
    NotesAppMVVMTheme {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(Constants.Screens.ADD_SCREEN)
                    }) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add_icons",
                        tint = Color.White
                    )
                }
            }
        ) {
            LazyColumn {

                items(notes) { note ->
                    NoteItem( note = note, navController = navController as NavHostController)
                }
            }
        }
    }
}
    @Composable
    fun NoteItem(note: Note,navController: NavHostController
    ) {
        val noteId = when(DB_TYPE.value){
            TYPE_FIREBASE -> note.firebaseId
            TYPE_DATABASE -> note.id
            else -> Constants.Keys.EMPTY
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 14.dp)
                .clickable {
                    //По нажатию переходим в саму заметку по id и пережаем его
                    navController.navigate(Constants.Screens.NOTE_SCREEN + "/${noteId}")
                 },
            elevation = 6.dp
        ) {
            Column(
                modifier = Modifier.padding(vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = note.title,
                    fontSize = 24.sp,
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = note.subtitle,
                )
            }
        }
    }

@Preview(showBackground = true)
@Composable
fun PrevMainScreen(){
    NotesAppMVVMTheme {

        val context = LocalContext.current
        val mViewModal: MainViewModel =
            viewModel(factory = MainViewModelFactory(context
                .applicationContext as Application
            ))
        MainScreen(navController = rememberNavController(), viewModel = mViewModal)

    }

}
    
    /*NotesAppMVVMTheme {


        Scaffold {

        }
        @Composable
        fun NoteItem(note: com.kodexcompany.notesappmvvm.navigation.Note, navController: NavHostController) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 24.dp)
                .clickable {
                    navController.navigate("note_Screen")
                },
            elevation = 6.dp
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = note.title,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = note.subtitle

                )
            }
        }
            LazyColumn{
                items(notes) {note ->
                    NoteItem(note = note, navController = navController )
            }
    }
        FloatingActionButton(
            onClick = {
                navController.navigate("add_Screen")
            },

            ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "add_Screen",
                tint = Color.White
            )
        }

    }

}
@Preview(showBackground = true)
@Composable
fun prevMainScreen(){
    NotesAppMVVMTheme {
        MainScreen(navController = rememberNavController(), viewModel = viewModel())

    }
}*/
/*

    NotesAppMVVMTheme {
         var title by remember { mutableStateOf("") }
        var subTitle by remember { mutableStateOf("") }
        val context = LocalContext.current
        val mViewModel: MainViewModel =
            viewModel(
                factory = MainViewModelFactory(
                    context
                        .applicationContext as Application
                )
            )
        val notes = mViewModel.readTest.observeAsState(listOf()).value

        Scaffold {
            FloatingActionButton(
                onClick = {
                    navController.navigate("add_Screen")
                },
                modifier = Modifier.padding(bottom = 400.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "add_Screen",
                    tint = Color.White
                )
            }
        }
        Column(modifier = Modifier.fillMaxSize()) {

        }
*/

//        Column() {
//
//            listOf<com.kodexcompany.notesappmvvm.navigation.Note>(
//                com.kodexcompany.notesappmvvm.navigation.Note(title = "com.kodexcompany.notesappmvvm.navigation.Note 1", subtitle = "Subtitle for com.kodexcompany.notesappmvvm.navigation.Note 1"),
//                com.kodexcompany.notesappmvvm.navigation.Note(title = "com.kodexcompany.notesappmvvm.navigation.Note 2", subtitle = "Subtitle for com.kodexcompany.notesappmvvm.navigation.Note 2"),
//                com.kodexcompany.notesappmvvm.navigation.Note(title = "com.kodexcompany.notesappmvvm.navigation.Note 3", subtitle = "Subtitle for com.kodexcompany.notesappmvvm.navigation.Note 3"),
//                com.kodexcompany.notesappmvvm.navigation.Note(title = "com.kodexcompany.notesappmvvm.navigation.Note 4", subtitle = "Subtitle for com.kodexcompany.notesappmvvm.navigation.Note 4")
//            )
            /* NoteItem(title = "com.kodexcompany.notesappmvvm.navigation.Note 1", subtitle = "Subtitle for com.kodexcompany.notesappmvvm.navigation.Note 1" , navController = navController as NavHostController)
            NoteItem(title = "com.kodexcompany.notesappmvvm.navigation.Note 2", subtitle = "Subtitle for com.kodexcompany.notesappmvvm.navigation.Note 2" , navController = navController as NavHostController)
            NoteItem(title = "com.kodexcompany.notesappmvvm.navigation.Note 3", subtitle = "Subtitle for com.kodexcompany.notesappmvvm.navigation.Note 3" , navController = navController as NavHostController)
            NoteItem(title = "com.kodexcompany.notesappmvvm.navigation.Note 4", subtitle = "Subtitle for com.kodexcompany.notesappmvvm.navigation.Note 4" , navController = navController as NavHostController)
*/







//    @Composable
//    fun NoteItem(note: com.kodexcompany.notesappmvvm.navigation.Note, navController: NavHostController) {
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 8.dp, horizontal = 24.dp)
//                .clickable {
//                    navController.navigate("note_Screen")
//                },
//            elevation = 6.dp
//        ) {
//            Column(
//                modifier = Modifier.padding(horizontal = 8.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(
//                    text = note.title,
//                    fontSize = 25.sp,
//                    fontWeight = FontWeight.Bold
//                )
//                Text(
//                    text = note.subtitle
//
//                )
//            }
//        }
//    }





//    val notes = viewModel.reedAllNotes().observeAsState(listOf()).value
//     Scaffold(
//        floatingActionButton = {
//            FloatingActionButton(
//                onClick = {
//                    navController.navigate("add_screen")
//                }) {
//                Icon(imageVector = Icons.Filled.Add,
//                    contentDescription = "Add_icons",
//                    tint = Color.White
//                )
//            }
//        }
//    ) {
//        LazyColumn{
//            items(notes){note ->
//                NoteItem(note = note, navController = navController as NavHostController)
//            }
//        }
//    }
//}
//@Composable
//fun NoteItem(note : com.kodexcompany.notesappmvvm.navigation.Note,
//             navController: NavHostController){
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 8.dp, horizontal = 14.dp)
//            .clickable {
//                navController.navigate("note_screen" + "/${note.id}")
//            },
//        elevation = 6.dp
//    ) {
//        Column(modifier = Modifier.padding(vertical = 8.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(text = note.title,
//                fontSize = 24.sp,
//                color = Color.Blue,
//                fontWeight = FontWeight.Bold
//            )
//            Text(text = note.subscribe,
//            )
//        }
//    }
//}

     //   @Preview(showBackground = true)
     //   @Composable
//fun prevMainScreen(){
   /* NotesAppMVVMTheme() {
        val context = LocalContext.current
        val mViewModal: MainViewModel =
            viewModel(factory = MainViewModelFactory(context
                .applicationContext as Application
            ))
        MainScreen(navController = rememberNavController(), viewModel = mViewModal)

    }*/
