package com.mfsoftware.home.data

import com.mfsoftware.home.api.Api
import com.mfsoftware.home.api.SignInResponse
import com.mfsoftware.home.api.SignUpRequest
import com.mfsoftware.home.security.Hash
import retrofit2.Callback

class RegistrationRepository {

    fun register(username: String, password: String, email: String, firstname: String, secondname: String, callback: Callback<SignInResponse>) {
        val call = Api.json.registerUser(SignUpRequest(username, Hash.create(password), email, firstname, secondname))
        call.enqueue(callback)
    }

    companion object {
        private var instance: RegistrationRepository? = null

        @JvmStatic
        fun getInstance(): RegistrationRepository? {
            if (RegistrationRepository.instance == null) RegistrationRepository.instance = RegistrationRepository()
            return RegistrationRepository.instance
        }
    }
}