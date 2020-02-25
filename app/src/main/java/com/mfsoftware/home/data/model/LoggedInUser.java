package com.mfsoftware.home.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String userName;
    private String firstName;
    private String token;

    public LoggedInUser(String userName, String firstName, String token) {
        this.userName = userName;
        this.firstName = firstName;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }
}
