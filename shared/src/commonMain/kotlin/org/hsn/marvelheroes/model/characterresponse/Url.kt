package org.hsn.marvelheroes.model.characterresponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Url(
    @SerialName("type")
    val type: String?,
    @SerialName("url")
    val url: String?
)