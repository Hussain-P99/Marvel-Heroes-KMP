package org.hsn.marvelheroes.util

import java.security.MessageDigest

/**
 * Created by Hussain on 20/08/24.
 */

actual fun getMd5sum(ts : String,pubKey : String, privKey : String) : String {
    val messageDigest = MessageDigest.getInstance("MD5")
    messageDigest.update((ts+privKey+pubKey).toByteArray())
    val digest = messageDigest.digest().toHexString()
    return digest
}

fun ByteArray.toHexString() = asUByteArray().joinToString("") { it.toString(16).padStart(2, '0') }