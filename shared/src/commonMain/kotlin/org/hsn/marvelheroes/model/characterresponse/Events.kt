package org.hsn.marvelheroes.model.characterresponse

import com.hsn.marvelheroes.models.characterresponse.Event
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Events(
    @SerialName("available")
    val available: String?,
    @SerialName("collectionURI")
    val collectionURI: String?,
    @SerialName("items")
    val items: List<Event>?,
    @SerialName("returned")
    val returned: String?
)