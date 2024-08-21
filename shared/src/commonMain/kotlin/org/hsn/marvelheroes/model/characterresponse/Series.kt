package org.hsn.marvelheroes.model.characterresponse


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Series(
    @SerialName("available")
    val available: String?,
    @SerialName("collectionURI")
    val collectionURI: String?,
    @SerialName("items")
    val items: List<SeriesDetails>?,
    @SerialName("returned")
    val returned: String?
)