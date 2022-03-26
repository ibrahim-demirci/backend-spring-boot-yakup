package com.skyland.timesheetBackend.api.utilities;

public final class ErrorMessage {

    public static class ErrorMessageType {
        public static final String USERNAME_ALREADY_TAKEN = "username-already-taken";
        public static final String USER_NOT_FOUND = "user-not-found";
    }

    public static class ErrorMessageInfo {
        public static final String USERNAME_ALREADY_TAKEN_INFO = "This username is already taken.";
        public static final String USER_NOT_FOUND_INFO = "User not found with username. Please sign up before.";
    }



}