package com.kodexcompany.notesappmvvm

import NotesNavHost
import android.app.Activity.RESULT_OK
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.kodexcompany.notesappmvvm.ui.theme.NotesAppMVVMTheme

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
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "Notes App")
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
                    NotesNavHost(mViewModal)
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