package com.charleston.marvelapp

import androidx.lifecycle.ViewModel
import com.charleston.domain.interactor.ListCharactersUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MarvelViewModel(
    private val listCharactersUseCase: ListCharactersUseCase
) : ViewModel() {

    private var coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    fun listCharacters(){
        coroutineScope.launch {
            val list = listCharactersUseCase.execute()
            list.size
        }
    }
}