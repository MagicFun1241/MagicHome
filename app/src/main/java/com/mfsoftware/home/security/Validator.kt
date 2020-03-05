package com.mfsoftware.home.security

import android.util.Patterns

class Validator {
    companion object {
        @JvmStatic
        fun isPassword(password: String): Boolean = password.trim { it <= ' ' }.length > 8

        @JvmStatic
        fun isEmail(str: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(str).matches()
    }
}