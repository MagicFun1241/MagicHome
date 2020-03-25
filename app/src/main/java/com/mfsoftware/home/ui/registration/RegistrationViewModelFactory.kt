package com.mfsoftware.home.ui.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mfsoftware.home.data.RegistrationRepository
import com.mfsoftware.home.ui.login.LoginViewModel

class RegistrationViewModelFactory : ViewModelProvider.Factory {
    @SuppressWarnings("unchecked")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            RegistrationViewModel(RegistrationRepository.getInstance()) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}