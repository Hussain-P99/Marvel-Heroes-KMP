package org.hsn.marvelheroes.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.hsn.marvelheroes.data.MarvelRepository
import org.hsn.marvelheroes.model.ExecResult
import org.hsn.marvelheroes.dto.Character

/**
 * Created by Hussain on 21/08/24.
 */
class MainViewModel(private val marvelRepository: MarvelRepository) : ViewModel() {

    init {
//        getAllCharacters()
    }

    private var searchJob: Job? = null

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean> get() = _isRefreshing

    val characters = mutableStateOf(listOf<Character>())

    val character = mutableStateOf<Character?>(null)

    val isLoading = mutableStateOf(false)

    val error = mutableStateOf<String?>(null)

    fun getAllCharacters(shouldRefresh: Boolean = false) {
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            when(val result = marvelRepository.getAllCharacters(shouldRefresh)) {
                is ExecResult.Error -> {
                    withContext(Dispatchers.Main) {
                        error.value = result.message
                        isLoading.value = false
                    }
                }
                is ExecResult.Loading -> {
                    withContext(Dispatchers.Main) {
                        isLoading.value = true
                    }
                }
                is ExecResult.Success -> {
                    withContext(Dispatchers.Main) {
                        isLoading.value = false
                        characters.value = result.data ?: emptyList()
                    }
                }
            }
        }
    }

    fun onRefresh() {
        viewModelScope.launch(Dispatchers.IO) {
            _isRefreshing.postValue(true)
            when(val result = marvelRepository.getAllCharacters(true)) {
                is ExecResult.Error -> {
                    withContext(Dispatchers.Main) {
                        error.value = result.message
                        isLoading.value = false
                    }
                }
                is ExecResult.Loading -> {
                    withContext(Dispatchers.Main) {
                        isLoading.value = true
                    }                    }
                is ExecResult.Success -> {
                    withContext(Dispatchers.Main) {
                        isLoading.value = false
                        characters.value = result.data ?: emptyList()
                    }
                }
            }
            _isRefreshing.postValue(false)
        }
    }

    fun searchCharacter(searchText: String) {
        searchJob?.cancel()
        isLoading.value = true
        searchJob = viewModelScope.launch(Dispatchers.IO) {
            when(val result = marvelRepository.searchCharacter(searchText)) {
                is ExecResult.Error -> {
                    withContext(Dispatchers.Main) {
                        error.value = result.message
                        isLoading.value = false
                    }
                }
                is ExecResult.Loading -> {
                    withContext(Dispatchers.Main) {
                        isLoading.value = true
                    }                    }
                is ExecResult.Success -> {
                    withContext(Dispatchers.Main) {
                        isLoading.value = false
                        characters.value = result.data ?: emptyList()
                    }
                }
            }
        }
    }

    fun getCharacter(characterId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when(val result = marvelRepository.getCharacterById(characterId)) {
                is ExecResult.Error -> {
                    withContext(Dispatchers.Main) {
                        error.value = result.message
                        isLoading.value = false
                    }
                }
                is ExecResult.Loading -> {
                    withContext(Dispatchers.Main) {
                        isLoading.value = true
                    }                    }
                is ExecResult.Success -> {
                    withContext(Dispatchers.Main) {
                        isLoading.value = false
                        character.value = result.data?.firstOrNull()
                    }
                }
            }
        }
    }

    fun bookmarkCharacter(character: Character, bookmark: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            character.isBookmarked = bookmark
            marvelRepository.updateCharacter(character)
        }
    }

    fun getBookmarkedCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            when(val result = marvelRepository.getBookmarkedCharacters()) {
                is ExecResult.Error -> {
                    withContext(Dispatchers.Main) {
                        error.value = result.message
                        isLoading.value = false
                    }
                }
                is ExecResult.Loading -> {
                    withContext(Dispatchers.Main) {
                        isLoading.value = true
                    }                    }
                is ExecResult.Success -> {
                    withContext(Dispatchers.Main) {
                        isLoading.value = false
                        characters.value = result.data ?: emptyList()
                    }
                }
            }
        }
    }

    fun resetError() {
        error.value = null
    }

}