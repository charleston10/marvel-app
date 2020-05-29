package com.charleston.marvelapp.screens.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.charleston.domain.interactor.ListCharactersUseCase
import com.charleston.domain.model.CharacterModel
import com.charleston.domain.model.ThemeModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class ListViewModel(
    private val listCharactersUseCase: ListCharactersUseCase
) : ViewModel() {

    private var coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val listMutableLiveData = MutableLiveData<List<CharacterModel>>()
    val listLiveData = listMutableLiveData

    fun loadList(themeSelected: ThemeModel) {
        when (themeSelected.id) {
            0 -> listCharacters()
        }
    }

    private fun listCharacters() {
        coroutineScope.launch {
            val list = listCharactersUseCase.execute()
            listMutableLiveData.postValue(list)
        }
    }
}