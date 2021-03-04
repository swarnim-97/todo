package dev.swarnim.project.model;

import lombok.Data;

@Data
public class User {
    private Long phoneNumber;
    private String name;
    private String email;
    private String password;
    private Boolean active;
    private Boolean deleted;
}
