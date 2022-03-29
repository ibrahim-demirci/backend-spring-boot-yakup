package com.skyland.timesheetBackend.api.responseModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data @AllArgsConstructor
public class ErrorInfo {
    private String  type ;
    private String  info;
}
