package org.hsn.marvelheroes.data.remote

import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.datetime.Clock
import org.hsn.marvelheroes.dto.Character
import org.hsn.marvelheroes.model.ExecResult
import org.hsn.marvelheroes.model.characterresponse.CharacterResponse
import org.hsn.marvelheroes.model.characterresponse.toDto
import org.hsn.marvelheroes.network.MarvelApi
import org.hsn.marvelheroes.network.httpClient
import org.hsn.marvelheroes.util.Constants
import org.hsn.marvelheroes.util.getMd5sum

/**
 * Created by Hussain on 20/08/24.
 */
class RemoteRepository : MarvelApi {

    override suspend fun getCharacters(search: String?) : ExecResult<List<Character>> {
        try {
            val ts = Clock.System.now().toEpochMilliseconds().toString()
            val hash = getMd5sum(ts,Constants.PUB_KEY,Constants.PRIV_KEY)
            val response : CharacterResponse = httpClient.get(Constants.CHARACTER_LIST_ENDPOINT) {
                url {
                    parameters.append("ts", ts)
                    parameters.append("apikey",Constants.PUB_KEY)
                    parameters.append("hash",hash)
                    parameters.append("limit","10")
                    parameters.append("offset","0")
                    if (search != null) {
                        parameters.append("nameStartsWith",search)
                    }
                }
            }.body()
            return ExecResult.Success(data = response.toDto())
        } catch (e : Exception) {
            return ExecResult.Error(message = e.message)
        }
    }
}