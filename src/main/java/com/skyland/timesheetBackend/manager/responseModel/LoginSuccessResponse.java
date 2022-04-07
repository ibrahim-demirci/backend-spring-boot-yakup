package com.skyland.timesheetBackend.manager.responseModel;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginSuccessResponse {
    private boolean success;
    private String status;
    private ErrorMessage error;
    private Token token;
    private String email;

}
