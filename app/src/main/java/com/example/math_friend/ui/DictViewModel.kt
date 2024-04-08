package com.example.math_friend.ui


import androidx.databinding.Observable
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.math_friend.data.DictRepository
import com.example.math_friend.data.Theory
import kotlinx.coroutines.launch

class DictViewModel(private val repository: DictRepository) : ViewModel(), Observable {

    val inputWord = MutableLiveData<String>()


    val inputTranslate = MutableLiveData<String>()


    val saveOrUpdateButtonText = MutableLiveData<String>()


    val clearAllOrDeleteButtonText = MutableLiveData<String>()


    private var isUpdateOrDelete = false
    private lateinit var theoryToUpdateOrDelete: Theory

    init {
        addText()
    }

    fun addText() {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear all"
    }

    fun observeWords(owner: LifecycleOwner, observer: Observer<List<Theory>>) =
        repository.getWords().observe(owner, observer)


    fun saveOrUpdate() {
        if (!dataIsEmpty(inputWord) || !dataIsEmpty(inputTranslate)) {
            if (isUpdateOrDelete) {
                theoryToUpdateOrDelete.apply {
                    word = inputWord.value ?: ""
                    translate = inputTranslate.value ?: ""
                }
                viewModelScope.launch {
                    repository.update(theoryToUpdateOrDelete)
                    refreshUI()

                }
            } else {
                viewModelScope.launch {
                    repository.insert(
                        Theory(0, inputWord.value ?: "", inputTranslate.value ?: "")
                    )
                }
                clearText()
            }
        }
    }

    private fun dataIsEmpty(data: MutableLiveData<String>) = data.value == null || data.value == ""

    private fun refreshUI() {
        clearText()
        isUpdateOrDelete = false
        addText()
    }

    private fun clearText() {
        inputWord.value = ""
        inputTranslate.value = ""
    }

    fun initUpdateAndDelete(theory: Theory) {
        inputWord.value = theory.word
        inputTranslate.value = theory.translate

        isUpdateOrDelete = true
        theoryToUpdateOrDelete = theory

        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"
    }

    fun clearAllOrDelete() = if (isUpdateOrDelete)
        viewModelScope.launch {
            repository.delete(theoryToUpdateOrDelete)
            refreshUI()
        } else viewModelScope.launch {
        repository.deleteAll()

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }


}
