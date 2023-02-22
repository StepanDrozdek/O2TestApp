package com.example.o2testapp.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.o2testapp.constants.Global
import com.example.o2testapp.constants.ScratchCardState
import com.example.o2testapp.navigation.Screens
import com.example.o2testapp.view.components.ScratchCard

@Composable
fun HomeScreen(navController: NavController) {
    val scratchDisabledButtonColor =
        if (Global.scratchCard.value?.state == ScratchCardState.UNSCRATCHED)
            Color.Green else Color.Gray

    val scratchDisabledTextColor =
        if (Global.scratchCard.value?.state == ScratchCardState.UNSCRATCHED)
            Color.White else Color.LightGray

    val activateDisabledButtonColor =
        if (Global.scratchCard.value?.state == ScratchCardState.SCRATCHED)
            Color.Green else Color.Gray

    val activateDisabledTextColor =
        if (Global.scratchCard.value?.state == ScratchCardState.SCRATCHED)
            Color.White else Color.LightGray

    Column(Modifier.fillMaxSize()) {
        ScratchCard()
        Row(
            Modifier
                .fillMaxSize()
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = scratchDisabledButtonColor),
                onClick = {
                    if(Global.scratchCard.value?.state == ScratchCardState.UNSCRATCHED) {
                        navController.navigate(Screens.Scratch.route)
                    }
                }) {
                Text(text = "SCRATCH",
                    color = scratchDisabledTextColor
                )
            }
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = activateDisabledButtonColor),
                onClick = {
                    if(Global.scratchCard.value?.state == ScratchCardState.SCRATCHED) {
                        navController.navigate(Screens.Activate.route)
                    }
                }) {
                Text(text = "ACTIVATE",
                    color = activateDisabledTextColor
                )
            }
        }
    }
}