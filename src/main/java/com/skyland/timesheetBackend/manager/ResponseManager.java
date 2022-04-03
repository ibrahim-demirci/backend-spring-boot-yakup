package com.skyland.timesheetBackend.manager;

import com.skyland.timesheetBackend.manager.responseModel.ErrorInfo;
import com.skyland.timesheetBackend.constants.K;
import com.skyland.timesheetBackend.manager.responseModel.LoginSuccessResponse;
import com.skyland.timesheetBackend.manager.responseModel.Token;

import static com.skyland.timesheetBackend.constants.K.ErrorMessageInfo.*;
import static com.skyland.timesheetBackend.constants.K.ErrorMessageType.*;
import static com.skyland.timesheetBackend.constants.K.ResponseStatusUtilities.*;

public class ResponseManager {

    private static final ResponseManager instance = new ResponseManager();

    //private constructor to avoid client applications to use constructor
    private ResponseManager(){}
    public static ResponseManager getInstance(){
        return instance;
    }

    enum AUTH_ERROR {
        expired_token,
        invalid_token,
        signature_verification,
        unknown_error,
    }

    public enum STATUS {
        created,
        updated,
        deleted,
        failed
    }

    public enum LOGIN_FAIL {
        user_not_found,
        user_not_verified,
        username_or_password_wrong
    }

    public LoginSuccessResponse get_login_success_response(Token token, String username) {
        LoginSuccessResponse loginSuccessResponse = null;
        loginSuccessResponse = new LoginSuccessResponse(true, STATUS_LOGIN,null, token, username);
        return loginSuccessResponse;
    }

    public ErrorInfo.BaseResponse get_login_fail_response(LOGIN_FAIL login_fail) {
        ErrorInfo.BaseResponse baseResponse = null;
        ErrorInfo errorInfo = null;

        switch (login_fail) {
            case user_not_found:
                errorInfo = new ErrorInfo(USER_NOT_FOUND, USER_NOT_FOUND_INFO);
                break;
            case user_not_verified:
                errorInfo = new ErrorInfo(USER_NOT_VERIFIED, USER_NOT_VERIFIED_INFO);
                break;
            case username_or_password_wrong:
                errorInfo = new ErrorInfo(USERNAME_OR_PASSWORD_WRONG, USERNAME_OR_PASSWORD_WRONG_INFO);
                break;
        }
        baseResponse = new ErrorInfo.BaseResponse(false, STATUS_FAILED, errorInfo);
        return baseResponse;
    }

    public ErrorInfo.BaseResponse get_auth_error_response(AUTH_ERROR error) {
        ErrorInfo errorInfo = null;
        ErrorInfo.BaseResponse loginFailResponse = null;

        switch (error) {
            case expired_token:
                errorInfo = new ErrorInfo(ACCESS_TOKEN_EXPIRED, ACCESS_TOKEN_EXPIRED_INFO);
                break;
            case invalid_token:
                errorInfo = new ErrorInfo(INVALID_TOKEN, INVALID_TOKEN_INFO);
                break;
            case signature_verification:
                errorInfo = new ErrorInfo(SIGNATURE_VERIFICATION, SIGNATURE_VERIFICATION_INFO);
                break;
            case unknown_error:
                errorInfo = new ErrorInfo(UNKNOWN_ERROR, UNKNOWN_ERROR_INFO);
                break;
            default:
                errorInfo = new ErrorInfo(K.ErrorMessageType.UNKNOWN_ERROR, UNKNOWN_ERROR_INFO);
        }

        loginFailResponse = new ErrorInfo.BaseResponse(false, K.ResponseStatusUtilities.STATUS_FAILED,errorInfo);
        return loginFailResponse;
    }

    public ErrorInfo.BaseResponse get_base_response(STATUS success) {
        ErrorInfo.BaseResponse baseResponse = null;
        switch (success) {
            case created:
                baseResponse = new ErrorInfo.BaseResponse(true, STATUS_CREATED,null);
                break;
            case deleted:
                baseResponse = new ErrorInfo.BaseResponse(true, STATUS_DELETED,null);
                break;
            case updated:
                baseResponse = new ErrorInfo.BaseResponse(true, STATUS_UPDATED,null);
                break;
            case failed:
                ErrorInfo errorInfo = new ErrorInfo(USER_NOT_FOUND, USER_NOT_FOUND_INFO);
                baseResponse = new ErrorInfo.BaseResponse(false,STATUS_FAILED,errorInfo);
                break;
        }
        return baseResponse;
    }


}
