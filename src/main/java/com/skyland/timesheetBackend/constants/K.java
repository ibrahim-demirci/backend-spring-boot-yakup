package com.skyland.timesheetBackend.constants;

public class K {

    public static class ErrorMessageType {
        public static final String USERNAME_ALREADY_TAKEN = "username-already-taken";
        public static final String USER_NOT_FOUND = "user-not-found";
        public static final String USERNAME_OR_PASSWORD_WRONG = "username-or-password-wrong";
        public static final String USER_NOT_VERIFIED = "user-not-verified";
        public static final String UNKNOWN_ERROR = "unknown-error";
        public static final String ACCESS_TOKEN_EXPIRED = "expired-access-token";
        public static final String INVALID_TOKEN = "invalid-token";
        public static final String SIGNATURE_VERIFICATION = "signature-verification";

    }

    public static class ErrorMessageInfo {
        public static final String USERNAME_ALREADY_TAKEN_INFO = "This username is already taken.";
        public static final String USER_NOT_FOUND_INFO = "User not found with username.";
        public static final String UNKNOWN_ERROR_INFO = "An unknown error.";
        public static final String USERNAME_OR_PASSWORD_WRONG_INFO = "Username or password is wrong";
        public static final String USER_NOT_VERIFIED_INFO = "This user is not verified yet. Please wait until to allow your account :).";
        public static final String ACCESS_TOKEN_EXPIRED_INFO = "Access token is expired.";
        public static final String INVALID_TOKEN_INFO = "This token is invalid.";
        public static final String SIGNATURE_VERIFICATION_INFO = "Token signature is not correct.";
    }

    public class ResponseStatusUtilities {
        public static final String STATUS_FAILED = "failed";
        public static final String STATUS_CREATED = "created";
        public static final String STATUS_UPDATED = "updated";
        public static final String STATUS_DELETED = "deleted";
        public static final String STATUS_LOGIN = "login";

    }


}
