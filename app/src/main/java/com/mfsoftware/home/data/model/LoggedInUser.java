package com.mfsoftware.home.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String userName;
    private String firstName;

    public LoggedInUser(String userName, String firstName) {
        this.userName = userName;
        this.firstName = firstName;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }
}
