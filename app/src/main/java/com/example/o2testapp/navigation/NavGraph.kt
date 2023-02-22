package com.example.o2testapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.o2testapp.view.ActivateScreen
import com.example.o2testapp.view.HomeScreen
import com.example.o2testapp.view.ScratchScreen

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
            ScratchScreen(navController = navController)
        }
        composable(route = Screens.Activate.route){
            ActivateScreen()
        }
    }
}