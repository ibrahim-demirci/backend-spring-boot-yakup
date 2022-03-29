package com.skyland.timesheetBackend.dto;

import lombok.Data;


import java.sql.Timestamp;

@Data
public class TaskDto {

        private Long id;
        private String title;
        private String description;
        private String username;
        private Timestamp creationDate;
        private Timestamp plannedFinishDate;

}
