package com.kodexcompany.notesappmvvm.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kodexcompany.notesappmvvm.MainViewModel
import com.kodexcompany.notesappmvvm.screens.*
import com.kodexcompany.notesappmvvm.utils.Constants

sealed class NavRoute(val route: String) {
    object Start: NavRoute(Constants.Screens.START_SCREEN)
    object Main: NavRoute(Constants.Screens.MAIN_SCREEN)
    object Add: NavRoute(Constants.Screens.ADD_SCREEN)
    object Note: NavRoute(Constants.Screens.NOTE_SCREEN)
}
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NotesNavHost(mViewModal: MainViewModel, navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavRoute.Start.route)
    {
        composable(NavRoute.Start.route)
        { StartScreen(navController = navController, viewModel = mViewModal) }
       composable(NavRoute.Main.route){
           MainScreen(navController = navController, viewModel = mViewModal) }
        composable(NavRoute.Add.route){
            AddScreen(navController = navController, viewModel = mViewModal)}
       composable(
           NavRoute.Note.route + "/{${Constants.Keys.ID}}"){ backStackEntry ->
    NoteScreen(navController = navController,
        viewModel = mViewModal,
        noteId =  backStackEntry
            .arguments?.getString(Constants.Keys
            .ID))}

    }
}


