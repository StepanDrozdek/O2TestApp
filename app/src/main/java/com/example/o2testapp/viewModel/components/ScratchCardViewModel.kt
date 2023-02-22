package com.example.o2testapp.viewModel.components

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.o2testapp.constants.Global
import com.example.o2testapp.models.ScratchCardModel

class ScratchCardViewModel: ViewModel() {
    var liveScratch: LiveData<ScratchCardModel> = Global.scratchCard
}