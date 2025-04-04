package com.asmadiyatech.onboarding.dto;

import lombok.Data;

@Data
public class UserRegistrationDTO {
    private String username;
    private String email;
    private String name;
    private Integer age;
    private Long phoneNo;
    private String address;
    private String password;
    private String role;
}
