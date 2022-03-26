package com.skyland.timesheetBackend.api.model;
import com.skyland.timesheetBackend.api.utilities.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ErrorInfo {
    private String  type ;
    private String  info;
}
