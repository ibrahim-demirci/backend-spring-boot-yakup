package com.skyland.timesheetBackend.manager.responseModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ErrorInfo {
    private String  type ;
    private String  info;

    @AllArgsConstructor
    @Data
    public static class BaseResponse {
        private boolean success;
        private String status;
        private ErrorInfo error = null;
    }
}
