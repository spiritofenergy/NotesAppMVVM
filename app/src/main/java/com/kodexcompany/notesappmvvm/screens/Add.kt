package com.kodexcompany.notesappmvvm.screens

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
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
import com.kodexcompany.notesappmvvm.utils.TYPE_ROOM

@Composable
fun AddScreen(navController: NavController, viewModel: MainViewModel ) {
        var title by remember { mutableStateOf("")}
        var subTitle by remember { mutableStateOf("")}
        var isButtonEnabled by remember { mutableStateOf(false) }

        Scaffold {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,

                ) {
                Text(
                    text = Constants.Keys.ADD_NOTE ,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                OutlinedTextField(
                    value = title,
                    onValueChange = {
                        title = it
                        isButtonEnabled = title.isNotEmpty() && subTitle.isNotEmpty()
                        },
                    label = { Text(text = Constants.Keys.TITLE) },
                    isError = title.isEmpty()
                )
                OutlinedTextField(
                    value = subTitle,
                    onValueChange = {
                        subTitle = it
                        isButtonEnabled = title.isNotEmpty() && subTitle.isNotEmpty()
                        },
                    label = { Text(text = Constants.Keys.SUBTITLE) },
                    isError = subTitle.isEmpty()
                )
                Button(
                    modifier = Modifier.padding(top = 16.dp),
                    enabled = isButtonEnabled,
                    onClick = {
                        navController.navigate(Constants.Screens.MAIN_SCREEN)
                        viewModel.addNote(note = Note(title = title, subtitle = subTitle)){
                            Log.d("checkData", "Button pressed $title # $subTitle")
                         }
                    }
                ){
                    Text(text = Constants.Keys.SAFE_NOTE)
                }

             }

            }
        }



@Preview(showBackground = true)
@Composable

fun PrevAddScreen(){
    NotesAppMVVMTheme {
          val context = LocalContext.current
          val mViewModal: MainViewModel =
              viewModel(factory = MainViewModel.MainViewModelFactory(
                  context
                      .applicationContext as Application
              )
              )
        AddScreen(navController = rememberNavController(), viewModel = mViewModal )
    }
}
/*
    }
        var title by remember {mutableStateOf("")}
        var subtitle by remember {mutableStateOf("")}
            var isButtonEnabled by remember {mutableStateOf(false)}
        NotesAppMVVMTheme {
            Scaffold {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = ADD_NOTE,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    OutlinedTextField(
                        value = title,
                        onValueChange = {
                            title = it
                            isButtonEnabled = title.isNotEmpty() && subtitle.isNotEmpty()
                        },
                        label = { Text(text = NOTE_TITLE) },
                        isError = title.isEmpty()
                    )

                    OutlinedTextField(
                        value = subtitle,
                        onValueChange = {
                            subtitle = it
                            isButtonEnabled = title.isNotEmpty() && subtitle.isNotEmpty()
                        },
                        label = { Text(text = NOTE_SUBTITLE) },
                        isError = subtitle.isEmpty()
                    )

                    Button(modifier = Modifier.padding(top = 16.dp),
                        enabled = isButtonEnabled,
                        onClick = {
                           // viewModel.addNotes(note = com.kodexcompany.notesappmvvm.navigation.Note(title = title, subscribe = subtitle))

                            {
                                navController.navigate(NOTE_SCREEN)
                            }

                            Log.d("Check", "Сохранить в Роом")
                        }
                    ) {
                        Text(text = ROOM_DATABASE)
                    }

                }

            }
        }
    }*/
