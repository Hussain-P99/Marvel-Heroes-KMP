package org.hsn.marvelheroes.model.characterresponse


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Comic(
    @SerialName("name")
    val name: String?,
    @SerialName("resourceURI")
    val resourceURI: String?
)