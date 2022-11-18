import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kodexcompany.notesappmvvm.MainViewModel
import com.kodexcompany.notesappmvvm.screens.AddScreen
import com.kodexcompany.notesappmvvm.screens.MainScreen
import com.kodexcompany.notesappmvvm.screens.NoteScreen
import com.kodexcompany.notesappmvvm.screens.StartScreen
import com.kodexcompany.notesappmvvm.utils.Constants

sealed class NavRoute(val route: String)
    object Start: NavRoute(Constants.Screens
        .START_SCREEN)
    object Main: NavRoute(Constants.Screens
        .MAIN_SCREEN)
    object Add: NavRoute(Constants.Screens
        .ADD_SCREEN)
    object Note: NavRoute(Constants.Screens
        .NOTE_SCREEN)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NotesNavHost(mViewModal: MainViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "start_Screen"){
        composable("start_Screen"){ StartScreen(navController = navController, viewModel = mViewModal) }
       composable("main_Screen"){ MainScreen(navController = navController, viewModel = mViewModal) }
        composable("add_Screen"){ AddScreen(navController = navController, viewModel = mViewModal)}
       composable("note_Screen" + "/{${Constants.Keys.ID}}"){ backStackEntry ->
    NoteScreen(navController = navController,
        viewModel = mViewModal,
        noteId =  backStackEntry
            .arguments?.getString(Constants.Keys
            .ID))}

    }
}


