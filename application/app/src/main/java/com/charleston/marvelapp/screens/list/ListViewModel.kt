package com.charleston.marvelapp.screens.list

import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.charleston.domain.interactor.ListCharactersUseCase
import com.charleston.domain.model.ItemModel
import com.charleston.domain.model.ThemeEnum
import com.charleston.domain.model.ThemeModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class ListViewModel(
    private val listCharactersUseCase: ListCharactersUseCase
) : ViewModel() {

    private var coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private val listItem: ArrayList<ItemModel> = arrayListOf()

    private val listMutableLiveData = MutableLiveData<List<ItemModel>>()
    val listLiveData = listMutableLiveData

    val totalShowing = ObservableInt()

    fun loadList(themeSelected: ThemeModel) {
        when (themeSelected.type) {
            ThemeEnum.CHARACTERS -> {
                listCharacters()
            }
            ThemeEnum.SERIES -> {
                listCharacters()
            }
            ThemeEnum.COMICS -> {
                listCharacters()
            }
        }
    }

    private fun listCharacters() {
        coroutineScope.launch {
            val list = listCharactersUseCase.execute()
            listItem.addAll(list)
            listMutableLiveData.postValue(list)
            totalShowing.set(listItem.size)
        }
    }
}