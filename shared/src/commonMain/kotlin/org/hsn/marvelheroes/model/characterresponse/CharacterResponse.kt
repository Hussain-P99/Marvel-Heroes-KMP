package org.hsn.marvelheroes.model.characterresponse


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.hsn.marvelheroes.dto.Character
import kotlin.random.Random

@Serializable
data class CharacterResponse(
    @SerialName("code")
    val code: String?,
    @SerialName("data")
    val `data`: Data?,
    @SerialName("status")
    val status: String?
)

fun CharacterResponse.toDto(): List<Character> {
    return this.data?.results?.map { character ->
        Character(
            character.id ?: "",
            character.name ?: "",
            character.description ?: "",
            character.comics?.items ?: emptyList(),
            character.series?.items ?: emptyList(),
            character.events?.items ?: emptyList(),
            character.urls ?: emptyList(),
            character.thumbnail?.path + "." + character.thumbnail?.extension
        )
    } ?: emptyList()
}