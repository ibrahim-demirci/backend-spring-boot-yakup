package com.skyland.timesheetBackend.api.responseModel;

import lombok.AllArgsConstructor;
import lombok.Data;

// Data for convert to map
@AllArgsConstructor @Data
public class ApiResponseBody {
    private boolean success;
    private String status;
    private ErrorInfo error = null;



}



