package org.hsn.marvelheroes.model.characterresponse


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Comics(
    @SerialName("available")
    val available: String?,
    @SerialName("collectionURI")
    val collectionURI: String?,
    @SerialName("items")
    val items: List<Comic>?,
    @SerialName("returned")
    val returned: String?
)