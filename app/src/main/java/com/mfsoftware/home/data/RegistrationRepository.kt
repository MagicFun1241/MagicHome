package com.mfsoftware.home.data

import android.content.Context
import com.mfsoftware.home.api.Api
import com.mfsoftware.home.api.SignUpRequest
import com.mfsoftware.home.api.SignUpResponse
import com.mfsoftware.home.security.Hash
import com.mfsoftware.home.ui.login.LoggedInUserView
import retrofit2.Callback

class RegistrationRepository {

    fun setLoggedInUser(context: Context, user: LoggedInUserView) {
        val preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString("username", user.userName)
        editor.putString("firstname", user.firstName)
        editor.putString("token", user.token)
        editor.apply()
    }

    fun register(username: String, password: String, email: String, firstname: String, secondname: String, callback: Callback<SignUpResponse>) {
        val call = Api.json.registerUser(SignUpRequest(username, Hash.create(password), email, firstname, secondname), Api.getFingerPrint())
        call.enqueue(callback)
    }

    companion object {
        private var instance: RegistrationRepository? = null

        @JvmStatic
        fun getInstance(): RegistrationRepository? {
            if (instance == null) instance = RegistrationRepository()
            return instance
        }
    }
}