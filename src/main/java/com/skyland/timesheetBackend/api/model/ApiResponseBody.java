package com.skyland.timesheetBackend.api.model;

import com.skyland.timesheetBackend.api.utilities.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

// Data for convert to map
@AllArgsConstructor @Data
public class ApiResponseBody {
    private boolean success;
    private String status;
    private ErrorInfo error = null;



}



