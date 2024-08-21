package com.hsn.marvelheroes.models.characterresponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Event(
    @SerialName("name")
    val name: String?,
    @SerialName("resourceURI")
    val resourceURI: String?
)