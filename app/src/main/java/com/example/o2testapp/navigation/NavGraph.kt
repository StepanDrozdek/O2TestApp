package com.example.o2testapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.o2testapp.ui.screens.ActivateScreen
import com.example.o2testapp.ui.screens.HomeScreen
import com.example.o2testapp.ui.screens.ScratchScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route)
    {
        composable(route = Screens.Home.route){
            HomeScreen(navController = navController)
        }
        composable(route = Screens.Scratch.route){
            ScratchScreen()
        }
        composable(route = Screens.Activate.route){
            ActivateScreen()
        }
    }
}