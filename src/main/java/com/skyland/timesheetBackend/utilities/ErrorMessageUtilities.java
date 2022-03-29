package com.skyland.timesheetBackend.utilities;

public final class ErrorMessageUtilities {

    public static class ErrorMessageType {
        public static final String USERNAME_ALREADY_TAKEN = "username-already-taken";
        public static final String USER_NOT_FOUND = "user-not-found";
        public static final String UNKNOWN_ERROR = "unknown-error";
    }

    public static class ErrorMessageInfo {
        public static final String USERNAME_ALREADY_TAKEN_INFO = "This username is already taken.";
        public static final String USER_NOT_FOUND_INFO = "User not found with username.";
        public static final String UNKNOWN_ERROR_INFO = "An unknown error.";
    }

}