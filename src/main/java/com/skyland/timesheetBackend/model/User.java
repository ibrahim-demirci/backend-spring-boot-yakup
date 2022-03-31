package com.skyland.timesheetBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private String jobTitle;
    private String phone;
    private String description;
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();
    private boolean isVerified = false;

    public User( String name, String surname, String jobTitle, String phone, String description, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.jobTitle = jobTitle;
        this.phone = phone;
        this.description = description;
        this.username = username;
        this.password = password;
    }
}
