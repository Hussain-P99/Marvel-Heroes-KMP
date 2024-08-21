package org.hsn.marvelheroes.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.hsn.marvelheroes.util.Constants

/**
 * Created by Hussain on 20/08/24.
 */
internal val httpClient by lazy {
    HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
        install(DefaultRequest) {
            url(Constants.BASE_URL)
        }
        install(HttpTimeout) {
            val _4_min = 60*4*1000L
            requestTimeoutMillis = _4_min
            socketTimeoutMillis = _4_min
            connectTimeoutMillis = _4_min
        }
    }
}