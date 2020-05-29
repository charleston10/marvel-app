package com.charleston.marvelapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.charleston.domain.interactor.ListCharactersUseCase
import com.charleston.domain.model.CharacterModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MarvelViewModel(
    private val listCharactersUseCase: ListCharactersUseCase
) : ViewModel() {

    private var coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val listFeaturedMutableLiveData = MutableLiveData<List<CharacterModel>>()
    val listFeaturedLiveData = listFeaturedMutableLiveData

    init {
        listCharacters()
    }

    fun listCharacters() {
        coroutineScope.launch {
            val list = listCharactersUseCase.execute()
            listFeaturedLiveData.postValue(list)
        }
    }
}