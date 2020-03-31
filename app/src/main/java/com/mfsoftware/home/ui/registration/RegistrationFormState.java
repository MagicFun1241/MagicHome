package com.mfsoftware.home.ui.registration;

import androidx.annotation.Nullable;

/**
 * Data validation state of the login form.
 */
public class RegistrationFormState {
    @Nullable
    private Integer emailError;

    @Nullable
    private Integer usernameError;

    @Nullable
    private Integer passwordError;

    private boolean isDataValid;

    RegistrationFormState(@Nullable Integer emailError, @Nullable Integer usernameError, @Nullable Integer passwordError) {
        this.emailError = emailError;
        this.usernameError = usernameError;
        this.passwordError = passwordError;
        this.isDataValid = false;
    }

    RegistrationFormState(boolean isDataValid) {
        this.emailError = null;
        this.usernameError = null;
        this.passwordError = null;
        this.isDataValid = isDataValid;
    }

    @Nullable
    public Integer getEmailError() {
        return emailError;
    }

    @Nullable
    Integer getUsernameError() {
        return usernameError;
    }

    @Nullable
    Integer getPasswordError() {
        return passwordError;
    }

    boolean isDataValid() {
        return isDataValid;
    }
}
