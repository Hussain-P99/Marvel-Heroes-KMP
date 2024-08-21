package org.hsn.marvelheroes.model.characterresponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Thumbnail(
    @SerialName("extension")
    val extension: String?,
    @SerialName("path")
    val path: String?
)