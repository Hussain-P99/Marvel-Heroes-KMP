package org.hsn.marvelheroes.data.local

import kotlinx.coroutines.flow.Flow
import org.hsn.marvelheroes.data.local.dao.CharacterDao
import org.hsn.marvelheroes.data.local.db.CharacterDatabase
import org.hsn.marvelheroes.dto.Character

/**
 * Created by Hussain on 21/08/24.
 */
class LocalRepositoryImpl(characterDatabase: CharacterDatabase) : LocalRepository {

    private val characterDao = characterDatabase.characterDao()

    override suspend fun insertCharacter(character: Character) {
        characterDao.insert(character)
    }

    override suspend fun updateCharacter(character: Character) {
        characterDao.update(character)
    }

    override suspend fun deleteCharacter(character: Character) {
        characterDao.deleteCharacter(character)
    }

    override suspend fun getAllCharacters(): List<Character> {
        return characterDao.getAllCharacters()
    }

    override suspend fun getCharacterById(characterId : String): Character {
        return characterDao.getCharacterById(characterId)
    }

    override suspend fun getAllBookmarkedCharacters(): List<Character> {
        return characterDao.getBookmarkedCharacters()
    }

    override suspend fun getCharactersCount(): Int {
        return characterDao.getCharacterCount()
    }
}