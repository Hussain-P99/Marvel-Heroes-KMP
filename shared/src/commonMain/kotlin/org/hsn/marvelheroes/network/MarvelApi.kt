package org.hsn.marvelheroes.network

import org.hsn.marvelheroes.dto.Character
import org.hsn.marvelheroes.model.ExecResult
import org.hsn.marvelheroes.model.characterresponse.CharacterResponse

/**
 * Created by Hussain on 20/08/24.
 */
interface MarvelApi {

    suspend fun getCharacters(search: String? = null) : ExecResult<List<Character>>

}