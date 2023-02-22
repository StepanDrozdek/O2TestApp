package com.example.o2testapp.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.o2testapp.constants.Global
import com.example.o2testapp.constants.ScratchCardState
import com.example.o2testapp.models.ScratchCardModel
import com.example.o2testapp.view.components.ScratchCard

@Composable
fun ScratchScreen() {
    Column(Modifier.fillMaxSize()) {
        ScratchCard()
        Button(onClick = {
            val data = ScratchCardModel(code = "TEST", state = ScratchCardState.SCRATCHED  )
            Global.scratchCard.postValue(data)
        }) {
            Text(text = "Scratch")
        }
    }
}