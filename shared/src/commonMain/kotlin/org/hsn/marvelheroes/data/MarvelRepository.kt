package org.hsn.marvelheroes.data

import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.hsn.marvelheroes.data.local.LocalRepository
import org.hsn.marvelheroes.data.remote.RemoteRepository
import org.hsn.marvelheroes.dto.Character
import org.hsn.marvelheroes.model.ExecResult

/**
 * Created by Hussain on 21/08/24.
 */
open class MarvelRepository(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) {

    suspend fun getAllCharacters(shouldRefresh: Boolean): ExecResult<List<Character>> {
            val charactersCount = localRepository.getCharactersCount()
            if (shouldRefresh || charactersCount <= 0) {
                val result = remoteRepository.getCharacters(null)
                if (result is ExecResult.Success) {
                    addAllCharacters(result.data ?: emptyList())
                }
                return result
            } else {
                val characters = localRepository.getAllCharacters()
                return ExecResult.Success(characters)
            }
        }

    suspend fun updateCharacter(character: Character) {
        localRepository.updateCharacter(character)
    }


    suspend fun addAllCharacters(characters: List<Character>) {
        characters.forEach { character ->
            addCharacter(character)
        }
    }

    suspend fun addCharacter(character: Character) {
        localRepository.insertCharacter(character)
    }

    suspend fun searchCharacter(searchText: String): ExecResult<List<Character>> {
        val characters = localRepository.getAllCharacters()

        if (searchText.isEmpty()) {
            return ExecResult.Success(characters)
        }

        if (characters.isEmpty()) return (ExecResult.Success(emptyList()))

        val foundCharacter = characters.filter { character ->
            character.name.contains(searchText, ignoreCase = true)
        }

        if (foundCharacter.isEmpty()) {
            // lookup online
            val result = remoteRepository.getCharacters(searchText)
            addAllCharacters(result.data ?: emptyList())
            return result
        } else {
            return ExecResult.Success(foundCharacter)
        }
    }

    suspend fun getCharacterById(characterId: String): ExecResult<List<Character>> {
        val character = localRepository.getCharacterById(characterId)
        return if (character == null) {
            // search online
            ExecResult.Error("No character found")
        } else {
            ExecResult.Success(listOf(character))
        }
        }

    suspend fun getBookmarkedCharacters() : ExecResult<List<Character>> {
        val characters = localRepository.getAllBookmarkedCharacters()
        return ExecResult.Success(characters)
    }
}