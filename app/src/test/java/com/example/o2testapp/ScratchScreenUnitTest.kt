package com.example.o2testapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.o2testapp.constants.Global
import com.example.o2testapp.constants.ScratchCardState
import com.example.o2testapp.models.ScratchCardModel
import com.example.o2testapp.viewModel.ScratchScreenViewModel
import kotlinx.coroutines.*
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class ScratchScreenUnitTest {
    private val scratchScreenViewModel = ScratchScreenViewModel()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun `scratch and finish early`() {
        Global.scratchCard.postValue(ScratchCardModel(code = null, state = ScratchCardState.UNSCRATCHED ))
        runBlocking {
            val job = scratchScreenViewModel.scratch()
            if(!job.isCompleted) {
                Assert.assertEquals(ScratchCardState.UNSCRATCHED, Global.scratchCard.value?.state)
            }
        }
    }

    @Test
    fun `scratch and wait for result`() {
        Global.scratchCard.postValue(ScratchCardModel(code = null, state = ScratchCardState.UNSCRATCHED ))
        runBlocking {
            val job = scratchScreenViewModel.scratch()

            delay(5000)
            if(!job.isCompleted)  {
                Assert.assertEquals(ScratchCardState.SCRATCHED, Global.scratchCard.value?.state)
                Assert.assertNotNull(Global.scratchCard.value?.code)
            }
        }
    }

    @Test
    fun `scratch and check code`() {
        Global.scratchCard.postValue(ScratchCardModel(code = null, state = ScratchCardState.UNSCRATCHED ))
        runBlocking {
            val job = scratchScreenViewModel.scratch()

            delay(5000)
            if(!job.isCompleted)  {
                Assert.assertNotNull(Global.scratchCard.value?.code)
            }
        }
    }
}