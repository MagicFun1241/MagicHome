package com.mfsoftware.home.security

import java.security.MessageDigest
import kotlin.experimental.and

private fun bin2hex(data: ByteArray): String {
    val hex = StringBuilder(data.size * 2)
    for (b in data) hex.append(String.format("%02x", b.and(0xFF.toByte())))
    return hex.toString()
}

class Hash {
    companion object {
        @JvmStatic
        fun create(str: String): String = bin2hex(MessageDigest.getInstance("SHA-256").digest(str.toByteArray()))
    }
}