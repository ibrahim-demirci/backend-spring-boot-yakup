package com.skyland.timesheetBackend.manager;

import com.skyland.timesheetBackend.constants.ResponseStatusUtilities;
import com.skyland.timesheetBackend.manager.commonEnum.AuthError;
import com.skyland.timesheetBackend.manager.responseModel.BaseResponse;
import com.skyland.timesheetBackend.manager.responseModel.ErrorMessage;
import com.skyland.timesheetBackend.manager.responseModel.LoginSuccessResponse;
import com.skyland.timesheetBackend.manager.responseModel.Token;

import static com.skyland.timesheetBackend.constants.ErrorMessageInfo.*;
import static com.skyland.timesheetBackend.constants.ResponseStatusUtilities.*;

public class ResponseManager {

    private static final ResponseManager instance = new ResponseManager();

    //private constructor to avoid client applications to use constructor
    private ResponseManager() {
    }

    public static ResponseManager getInstance() {
        return instance;
    }

    public enum STATUS {
        created,
        updated,
        deleted,
    }

    public LoginSuccessResponse getLoginSuccessResponse(Token token, String username) {
        LoginSuccessResponse loginSuccessResponse = null;
        loginSuccessResponse = new LoginSuccessResponse(true, STATUS_LOGIN, null, token, username);
        return loginSuccessResponse;
    }

//    public BaseResponse get_login_fail_response(LOGIN_FAIL login_fail) {
//        BaseResponse baseResponse = null;
//        ErrorInfo errorInfo = null;
//
//        switch (login_fail) {
//            case user_not_found:
//                errorInfo = new ErrorInfo(USER_NOT_FOUND_INFO);
//                break;
//            case user_not_verified:
//                errorInfo = new ErrorInfo(USER_NOT_VERIFIED_INFO);
//                break;
//            case username_or_password_wrong:
//                errorInfo = new ErrorInfo(USERNAME_OR_PASSWORD_WRONG_INFO);
//                break;
//        }
//        baseResponse = new BaseResponse(false, STATUS_FAILED, errorInfo);
//        return baseResponse;
//    }

    public BaseResponse getAuthErrorResponse(AuthError error) {
        ErrorMessage errorInfo = null;
        BaseResponse loginFailResponse = null;

        switch (error) {
            case expired_token:
                errorInfo = new ErrorMessage(ACCESS_TOKEN_EXPIRED_INFO);
                break;
            case invalid_token:
                errorInfo = new ErrorMessage(INVALID_TOKEN_INFO);
                break;
            case signature_verification:
                errorInfo = new ErrorMessage(SIGNATURE_VERIFICATION_INFO);
                break;
            default:
                errorInfo = new ErrorMessage(UNKNOWN_ERROR_INFO);
        }

        loginFailResponse = new BaseResponse(false, ResponseStatusUtilities.STATUS_FAILED, errorInfo);
        return loginFailResponse;
    }

    public BaseResponse getBaseResponse(STATUS success) {
        BaseResponse baseResponse = null;
        switch (success) {
            case created:
                baseResponse = new BaseResponse(true, STATUS_CREATED, null);
                break;
            case deleted:
                baseResponse = new BaseResponse(true, STATUS_DELETED, null);
                break;
            case updated:
                baseResponse = new BaseResponse(true, STATUS_UPDATED, null);
                break;
        }
        return baseResponse;
    }

    public BaseResponse getErrorResponseWithCustomMessage(String message) {
        ErrorMessage errorInfo = new ErrorMessage(message);
        return new BaseResponse(false, STATUS_FAILED, errorInfo);
    }
}
