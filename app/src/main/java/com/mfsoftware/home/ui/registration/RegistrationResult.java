package com.mfsoftware.home.ui.registration;

import androidx.annotation.Nullable;

import com.mfsoftware.home.ui.login.LoggedInUserView;

/**
 * Authentication result : success (user details) or error message.
 */
public class RegistrationResult {
    @Nullable
    private LoggedInUserView success;

    @Nullable
    private Integer error;

    RegistrationResult(@Nullable Integer error) {
        this.error = error;
    }

    RegistrationResult(@Nullable LoggedInUserView success) {
        this.success = success;
    }

    @Nullable
    LoggedInUserView getSuccess() {
        return success;
    }

    @Nullable
    Integer getError() {
        return error;
    }
}
