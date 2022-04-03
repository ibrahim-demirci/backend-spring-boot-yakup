package com.skyland.timesheetBackend.manager.responseModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ErrorInfo {
    private String  type ;
    private String  info;


}
