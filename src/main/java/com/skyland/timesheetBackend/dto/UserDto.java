package com.skyland.timesheetBackend.dto;

import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String name;
    private String surname;
    private String phone;
    private String description;
    private String username;
    private boolean isVerified;
}
