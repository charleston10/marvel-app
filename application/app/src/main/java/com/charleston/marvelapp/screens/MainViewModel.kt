package com.charleston.marvelapp.screens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.charleston.domain.interactor.ListCharactersUseCase
import com.charleston.domain.interactor.ListThemesUseCase
import com.charleston.domain.model.CharacterModel
import com.charleston.domain.model.ThemeModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MainViewModel(
    private val listCharactersUseCase: ListCharactersUseCase,
    private val listThemesUseCase: ListThemesUseCase
) : ViewModel() {

    private var coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private lateinit var themeSelected: ThemeModel

    private val listFeaturedMutableLiveData = MutableLiveData<List<CharacterModel>>()
    private val listThemedMutableLiveData = MutableLiveData<List<ThemeModel>>()

    val listFeaturedLiveData = listFeaturedMutableLiveData
    val listThemeLiveData = listThemedMutableLiveData

    init {
        listThemes()
    }

    fun listThemes() {
        coroutineScope.launch {
            val list = listThemesUseCase.execute()
            listThemedMutableLiveData.postValue(list)
        }
    }

    fun list(){
        when(themeSelected.id){
            0 -> listCharacters()
        }
    }

    fun listCharacters() {
        coroutineScope.launch {
            val list = listCharactersUseCase.execute()
            listFeaturedLiveData.postValue(list)
        }
    }
}