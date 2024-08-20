package org.hsn.marvelheroes

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform