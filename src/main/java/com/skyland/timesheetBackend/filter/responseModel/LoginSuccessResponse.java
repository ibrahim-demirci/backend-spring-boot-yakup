package com.skyland.timesheetBackend.filter.responseModel;

import com.skyland.timesheetBackend.api.responseModel.ErrorInfo;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor @Data
public class LoginSuccessResponse {
        private boolean success;
        private String status;
        private ErrorInfo error = null;
        private Token token;
        private String username;

}
