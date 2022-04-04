package com.skyland.timesheetBackend.dto;

import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String userCode;
    private String name;
    private String surname;
    private String jobTitle;
    private String phone;
    private String description;
    private String email;
    private boolean isVerified;
}
