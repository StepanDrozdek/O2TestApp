package com.example.o2testapp.constants

import androidx.lifecycle.MutableLiveData
import com.example.o2testapp.models.ScratchCardModel

class Global {
    companion object {
        val scratchCard: MutableLiveData<ScratchCardModel> = MutableLiveData<ScratchCardModel>()
            .apply { value = ScratchCardModel() }
    }
}