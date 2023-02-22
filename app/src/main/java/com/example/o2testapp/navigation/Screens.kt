package com.example.o2testapp.navigation

sealed class Screens(val route: String) {
    object Home: Screens("main_screen")
    object Scratch: Screens("scratch_screen")
    object Activate: Screens("activate_screen")
}
