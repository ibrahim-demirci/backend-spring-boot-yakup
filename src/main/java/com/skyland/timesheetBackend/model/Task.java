package com.skyland.timesheetBackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    @OneToOne(fetch = FetchType.EAGER)
    private User user;
    private Timestamp creationDate;
    private Timestamp plannedFinishDate;
}
