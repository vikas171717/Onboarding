package com.asmadiyatech.onboarding.service;

import java.util.List;
import java.util.Optional;

import com.asmadiyatech.onboarding.dto.AssignRoleRequest;
import com.asmadiyatech.onboarding.dto.OtpVerificationRequest;
import com.asmadiyatech.onboarding.dto.SignInRequest;
import com.asmadiyatech.onboarding.dto.UserRegistrationDTO;
import com.asmadiyatech.onboarding.entities.User;

public interface UserService {

    List<User> getAllUsers() ;

    Optional<User> getUserByUsername(String userName) ;

    Boolean deleteUser(String username) ;

    User registerUser(UserRegistrationDTO userDto);

    Object signIn(SignInRequest signInRequest) ;

    void sendOtp(User user) ;

    String verifyOtp(OtpVerificationRequest request) ;

    User assignRoleToUser(AssignRoleRequest request) ;

    Optional<User> findByUsername(String username) ;
}
