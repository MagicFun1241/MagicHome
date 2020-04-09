package com.mfsoftware.home.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser implements Parcelable {

    private String userName;
    private String firstName;

    public LoggedInUser(String userName, String firstName) {
        this.userName = userName;
        this.firstName = firstName;
    }

    private LoggedInUser(Parcel in) {
        userName = in.readString();
        firstName = in.readString();
    }

    public static final Creator<LoggedInUser> CREATOR = new Creator<LoggedInUser>() {
        @Override
        public LoggedInUser createFromParcel(Parcel in) {
            return new LoggedInUser(in);
        }

        @Override
        public LoggedInUser[] newArray(int size) {
            return new LoggedInUser[size];
        }
    };

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeString(firstName);
    }
}
