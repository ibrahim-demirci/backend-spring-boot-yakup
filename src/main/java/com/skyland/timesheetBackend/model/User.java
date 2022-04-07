package com.skyland.timesheetBackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, updatable = false)
    private String userCode;
    private String name;
    private String surname;
    private String jobTitle;
    private String phone;
    private String description;
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();
    private boolean isVerified = false;

    public User(String name, String surname, String jobTitle, String phone, String description, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.jobTitle = jobTitle;
        this.phone = phone;
        this.description = description;
        this.email = email;
        this.password = password;
    }
}
