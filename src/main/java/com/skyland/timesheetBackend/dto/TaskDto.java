package com.skyland.timesheetBackend.dto;

import com.skyland.timesheetBackend.model.Task;
import com.skyland.timesheetBackend.model.User;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.OneToOne;
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
