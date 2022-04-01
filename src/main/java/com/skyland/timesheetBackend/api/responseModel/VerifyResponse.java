package com.skyland.timesheetBackend.api.responseModel;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor @Data
public class VerifyResponse {

    private boolean success;
    private String status;
    private ErrorInfo error = null;
}
