package org.hsn.marvelheroes.data.local

import kotlinx.coroutines.flow.Flow
import org.hsn.marvelheroes.dto.Character

/**
 * Created by Hussain on 21/08/24.
 */
interface LocalRepository {

    suspend fun insertCharacter(character: Character)

    suspend fun updateCharacter(character: Character)

    suspend fun deleteCharacter(character: Character)

    suspend fun getAllCharacters() : List<Character>

    suspend fun getCharacterById(characterId : String) : Character?

    suspend fun getAllBookmarkedCharacters() : List<Character>

    suspend fun getCharactersCount() : Int
}