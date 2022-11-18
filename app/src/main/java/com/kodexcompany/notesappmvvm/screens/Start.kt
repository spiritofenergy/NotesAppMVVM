package com.kodexcompany.notesappmvvm.screens

import android.app.Application
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kodexcompany.notesappmvvm.MainViewModel
import com.kodexcompany.notesappmvvm.ui.theme.NotesAppMVVMTheme
import com.kodexcompany.notesappmvvm.utils.*
import com.kodexcompany.notesappmvvm.utils.Constants.Keys.WAT_WELL_WE_YSE
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@ExperimentalMaterialApi
@Composable
fun StartScreen(navController: NavHostController, viewModel: MainViewModel) {

    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    var login by remember { mutableStateOf(Constants.Keys.EMPTY) }
    var password by remember { mutableStateOf(Constants.Keys.EMPTY) }

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
                        text = Constants.Keys.LOG_IN,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    OutlinedTextField(
                        value = login,
                        onValueChange = { login = it },
                        label = { Text(text = Constants.Keys.LOGIN_TEXT) },
                        isError = login.isEmpty()
                    )
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text(text = Constants.Keys.PASSWORD_TEX) },
                        isError = password.isEmpty()
                    )
                    Button(modifier = Modifier.padding(top = 26.dp),
                        onClick = {
                            LOGIN = login
                            PASSWORD = password
                             Log.d("checkData", "Hi $login + $PASSWORD")
                            viewModel.initDatabase(TYPE_FIREBASE){
                                Log.d("checkData", "Hi. AUth Success + $LOGIN")
                            }
                        },
                            enabled = login.isNotEmpty() && password.isNotEmpty()
                        ) {
                        Text(text = Constants.Keys.SING_IN)

                    }
                }
            }
        }) {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(text = WAT_WELL_WE_YSE)
                Button(
                    onClick = {
                        viewModel.initDatabase(TYPE_ROOM) {
                            navController.navigate(Constants.Screens.MAIN_SCREEN)
                        }
                    },
                    modifier = Modifier
                        .width(200.dp)
                        .padding(vertical = 8.dp)
                ) {
                    Text(text = Constants.Keys.SAFE_ROOM)
                }
                Button(
                    onClick = {
                        coroutineScope.launch {
                            bottomSheetState.show() }
                     },
                    modifier = Modifier
                        .width(200.dp)
                        .padding(vertical = 8.dp)
                ) {
                    Text(text = Constants.Keys.SAFE_FIREBASE)
                }
            }
        }
    }
}
@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun PrevStartScreen(){
    NotesAppMVVMTheme {
        val context = LocalContext.current
        val mViewModal: MainViewModel =
            viewModel(factory = MainViewModel.MainViewModelFactory(context
                .applicationContext as Application
            ))
        StartScreen(navController = rememberNavController(), viewModel = mViewModal)
     }
}
//    val bottomSheetState =
//        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
//    val coroutineScope = rememberCoroutineScope()
//    var login by remember { mutableStateOf(Constants.Keys.EMPTY) }
//    var password by remember { mutableStateOf(Constants.Keys.EMPTY) }
//    ModalBottomSheetLayout(
//        sheetState = bottomSheetState,
//        sheetElevation = 5.dp,
//        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
//        sheetContent = {
//            Surface {
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(all = 32.dp)
//                ) {
//                    Text(
//                        text = Constants.Keys.LOG_IN,
//                        Modifier.padding(vertical = 8.dp),
//                        fontWeight = FontWeight.Bold,
//                        fontSize = 18.sp
//                    )
////                    Text(
////                        text = "Login: $login"
// //                   )
//                    OutlinedTextField(
//                        value = login,
//                        onValueChange = { login = it },
//                        label = { Text(text = "Login: $login") },
//                        isError = login.isEmpty()
//                    )
////                    )
//                    OutlinedTextField(
//                        value = password,
//                        onValueChange = { password = it },
//                        label = { Text(text = "Password") },
//                        isError = password.isEmpty()
//                    )
//                    //AUCH
//                    Button(
//                        modifier = Modifier.padding(16.dp),
//                        onClick = {
//                            login = login
//                            password = password
//                          viewModel.initDatabase(SAFE_IN_FIREBASE){
//                              navController.navigate("main_Screen")
//
//
//                              Log.d("checkData", "Login: $login  Password $password in Start")
//                          }
//                        },
//                        enabled = login.isNotEmpty() && password.isNotEmpty()
//                    ) {
//                        Text(text = Constants.Keys.SING_IN)
//                    }
//                }
//            }
//        }) {
//        Scaffold(
//            modifier = Modifier.fillMaxSize()
//        ) {
//            Column(
//                modifier = Modifier.fillMaxSize(),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center
//            ) {
//                Text(text = WAT_WELL_WE_YSE)
//                Button(
//                    onClick = {
//                        viewModel.initDatabase(TYPE_ROOM) {
//                        }
//                        navController.navigate("main_Screen")
//                    },
//                    modifier = Modifier
//                        .width(200.dp)
//                        .padding(vertical = 8.dp)
//                )
//                {
//                    Text(text = ROOM_DATABASE)
//                }
//                Button(
//                    onClick = {
//                        coroutineScope.launch {
//                            bottomSheetState.show()
////                            viewModel.initDatabase(SAFE_IN_FIREBASE) {
////                                navController.navigate("main_Screen")
////                            }
//                        }
//                                  },
//                    modifier = Modifier
//                        .width(200.dp)
//                        .padding(vertical = 8.dp)
//                ) {
//                    Text(text = SAFE_IN_FIREBASE)
//                }
//            }
//        }
//    }
//    }
//@OptIn(ExperimentalMaterialApi::class)
//@Preview(showBackground = true)
//      @Composable
//    fun PrevStartScreen() {
//        NotesAppMVVMTheme() {
//            val context = LocalContext.current
//            val mViewModal: MainViewModel =
//                viewModel(
//                    factory = MainViewModelFactory(
//                        context
//                            .applicationContext as Application
//                    )
//                )
//
//            StartScreen(navController = rememberNavController(), viewModel = mViewModal)
//
//        }
//    }
//
