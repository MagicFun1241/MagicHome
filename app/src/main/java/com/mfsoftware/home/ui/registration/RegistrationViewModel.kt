package com.mfsoftware.home.ui.registration

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mfsoftware.home.R
import com.mfsoftware.home.api.SignUpResponse
import com.mfsoftware.home.data.RegistrationRepository
import com.mfsoftware.home.security.Validator.Companion.isEmail
import com.mfsoftware.home.security.Validator.Companion.isLogin
import com.mfsoftware.home.security.Validator.Companion.isPassword
import com.mfsoftware.home.ui.login.LoggedInUserView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationViewModel(var registrationRepository: RegistrationRepository?) : ViewModel() {
    private val registrationFormState = MutableLiveData<RegistrationFormState>()
    private val registrationResult = MutableLiveData<RegistrationResult>()

    fun register(context: Context, username: String, password: String, email: String, firstname: String, secondname: String) {
        registrationRepository!!.register(username, password, email, firstname, secondname, object : Callback<SignUpResponse> {
            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                if (response.isSuccessful) {
                    assert(response.body() != null)
                    val userName = response.body()!!.userName
                    val firstName = response.body()!!.firstName
                    val loggedInUserView = LoggedInUserView(userName, firstName, response.body()!!.token)
                    registrationRepository!!.setLoggedInUser(context, loggedInUserView)
                    registrationResult.setValue(RegistrationResult(loggedInUserView))
                } else registrationResult.setValue(RegistrationResult(R.string.login_failed))
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                registrationResult.value = RegistrationResult(R.string.login_failed)
            }
        })
    }

    fun getRegistrationFormState(): LiveData<RegistrationFormState?> {
        return registrationFormState
    }

    fun registrationDataChanged(email: String?, username: String?, password: String?) {
        if (!email?.let { isEmail(it) }!!) registrationFormState.setValue(RegistrationFormState(R.string.invalid_email, null, null));
        else if (!username?.let { isLogin(it) }!!) registrationFormState.setValue(RegistrationFormState(null, R.string.invalid_username, null))
        else if (!isPassword(password!!)) registrationFormState.setValue(RegistrationFormState(null, null, R.string.invalid_password))
        else registrationFormState.setValue(RegistrationFormState(true))
    }
}