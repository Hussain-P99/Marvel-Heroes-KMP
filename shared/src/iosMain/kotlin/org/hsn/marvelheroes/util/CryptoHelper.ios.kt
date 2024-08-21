package org.hsn.marvelheroes.util

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.refTo
import kotlinx.cinterop.usePinned
import platform.CoreCrypto.CC_MD5
import platform.CoreCrypto.CC_MD5_DIGEST_LENGTH
/**
 * Created by Hussain on 20/08/24.
 */


@OptIn(ExperimentalForeignApi::class, ExperimentalStdlibApi::class)
actual fun getMd5sum(
    ts: String,
    pubKey: String,
    privKey: String
): String {
    val input = ts + privKey + pubKey
    val data = input.encodeToByteArray()
    val digest = UByteArray(CC_MD5_DIGEST_LENGTH.toInt())

    data.usePinned {
        CC_MD5(it.addressOf(0), data.size.toUInt(), digest.refTo(0))
    }

    return digest.toHexString()
}