package com.example.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private Long id;
    private String name;
    private String lastname;
    private String email;
    private String password;
    private UserRole role;

}
