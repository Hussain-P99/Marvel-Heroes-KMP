package org.hsn.marvelheroes.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import org.hsn.marvelheroes.dto.Character

/**
 * Created by Hussain on 20/08/24.
 */
@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(character: Character)

    @Update
    suspend fun update(character: Character)

    @Delete
    suspend fun deleteCharacter(character: Character)

    @Query("SELECT * FROM character")
    suspend fun getAllCharacters() : List<Character>

    @Query("SELECT * FROM character WHERE characterId = :id")
    suspend fun getCharacterById(id : String) : Character

    @Query("SELECT * FROM character WHERE isBookmarked == 1")
    suspend fun getBookmarkedCharacters() : List<Character>

    @Query("SELECT count(rowid) FROM character")
    suspend fun getCharacterCount() : Int
}