package com.skyland.timesheetBackend.manager.responseModel;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BaseResponse {
    private boolean success;
    private String status;
    private ErrorInfo error = null;
}