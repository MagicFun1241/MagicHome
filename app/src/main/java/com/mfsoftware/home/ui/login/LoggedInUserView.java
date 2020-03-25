package com.mfsoftware.home.ui.login;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Class exposing authenticated user details
 */
public class LoggedInUserView implements Parcelable {
    private String userName;
    private String firstName;
    private String token;

    public LoggedInUserView(String userName, String firstName, String token) {
        this.userName = userName;
        this.firstName = firstName;
        this.token = token;
    }

    public LoggedInUserView(String userName, String firstName) {
        this.userName = userName;
        this.firstName = firstName;
    }

    private LoggedInUserView(Parcel in) {
        userName = in.readString();
        firstName = in.readString();
        token = in.readString();
    }

    public static final Creator<LoggedInUserView> CREATOR = new Creator<LoggedInUserView>() {
        @Override
        public LoggedInUserView createFromParcel(Parcel in) {
            return new LoggedInUserView(in);
        }

        @Override
        public LoggedInUserView[] newArray(int size) {
            return new LoggedInUserView[size];
        }
    };

    public String getUserName() {
        return userName;
    }

    public String getToken() {
        return token;
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
        dest.writeString(token);
    }
}
