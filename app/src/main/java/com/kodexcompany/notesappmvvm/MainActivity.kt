package com.kodexcompany.notesappmvvm

import com.kodexcompany.notesappmvvm.navigation.NotesNavHost
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kodexcompany.notesappmvvm.ui.theme.NotesAppMVVMTheme
import com.kodexcompany.notesappmvvm.utils.Constants
import com.kodexcompany.notesappmvvm.utils.DB_TYPE

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesAppMVVMTheme {
                val context = LocalContext.current
                val mViewModal: MainViewModel =
                    viewModel(factory = MainViewModel.MainViewModelFactory(context
                        .applicationContext as Application
                    ))
                val navController = rememberNavController()
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(text = "Note App")
                                    if (DB_TYPE.value.isNotEmpty()){
                                        Icon(imageVector = Icons.Default.ExitToApp,
                                            contentDescription = "",
                                            modifier = Modifier.clickable {
                                                mViewModal.singOut{
                                                    navController.navigate(Constants.Screens.START_SCREEN){
                                                        popUpTo(Constants.Screens.START_SCREEN){
                                                            inclusive = true
                                                        }

                                                    }
                                                }
                                            }
                                        )
                                    }
                                }
                             },
                    backgroundColor = Color.Blue,
                    contentColor = Color.White,
                    elevation = 12.dp
                )
            },
            content = {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NotesNavHost(mViewModal, navController)
                      }
                   }
                )

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NotesAppMVVMTheme {
     }
}
/*fun singIn(){
    val providers = arrayListOf(
        AuthUI.IdpConfig.EmailBuilder().build()
    )
    val singIntent = AuthUI.getInstance()
        .createSignInIntentBuilder()
        .setAvailableProviders(providers)
        .build()
}*/
//
//private fun singInResult(result: FirebaseAuthUIActivityResultContract){
//    val response = result.idpResponse
//    if (result.resultCode == RESULT_OK)
//
//}