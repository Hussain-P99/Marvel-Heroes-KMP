package org.hsn.marvelheroes.model.characterresponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.hsn.marvelheroes.model.characterresponse.Story

@Serializable
data class Stories(
    @SerialName("available")
    val available: String?,
    @SerialName("collectionURI")
    val collectionURI: String?,
    @SerialName("items")
    val items: List<Story>?,
    @SerialName("returned")
    val returned: String?
)