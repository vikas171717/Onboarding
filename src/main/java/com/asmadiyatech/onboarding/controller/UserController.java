<<<<<<< HEAD
package com.asmadiyatech.onboarding.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asmadiyatech.onboarding.dto.AssignRoleRequest;
import com.asmadiyatech.onboarding.dto.OtpVerificationRequest;
import com.asmadiyatech.onboarding.dto.SignInRequest;
import com.asmadiyatech.onboarding.dto.UserRegistrationDTO;
import com.asmadiyatech.onboarding.entities.User;
import com.asmadiyatech.onboarding.service.UserService;

@RestController
@RequestMapping("/api/onboarding/user")
public class UserController {

    @Autowired
    private UserService userService ;

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers(@RequestHeader("Authorization") String token) {
        List<User> usersList = userService.getAllUsers() ;
        return ResponseEntity.ok(usersList);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@RequestHeader("Authorization") String token, @PathVariable String username) {
        Optional<User> user = userService.getUserByUsername(username);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {

        if(userService.deleteUser(username)){
            return ResponseEntity.ok("User deleted successfully");
        }else {
            return ResponseEntity.notFound().build();
        }      
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRegistrationDTO userDto) {
        User createdUser = userService.registerUser(userDto);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest signInRequest) {
        Object response = userService.signIn(signInRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestBody OtpVerificationRequest request) {
        String response = userService.verifyOtp(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/assign-role")
    public ResponseEntity<User> assignRole(@RequestHeader("Authorization") String token, @RequestBody AssignRoleRequest request) {
        return ResponseEntity.ok(userService.assignRoleToUser(request));
    }
}
=======
package com.asmadiyatech.onboarding.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asmadiyatech.onboarding.dto.AssignRoleRequest;
import com.asmadiyatech.onboarding.dto.OtpVerificationRequest;
import com.asmadiyatech.onboarding.dto.SignInRequest;
import com.asmadiyatech.onboarding.dto.UserRegistrationDTO;
import com.asmadiyatech.onboarding.entities.User;
import com.asmadiyatech.onboarding.service.UserService;

@RestController
@RequestMapping("/api/onboarding/user")
public class UserController {

    @Autowired
    private UserService userService ;

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers(@RequestHeader("Authorization") String token) {
        List<User> usersList = userService.getAllUsers() ;
        return ResponseEntity.ok(usersList);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@RequestHeader("Authorization") String token, @PathVariable String username) {
        Optional<User> user = userService.getUserByUsername(username);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {

        if(userService.deleteUser(username)){
            return ResponseEntity.ok("User deleted successfully");
        }else {
            return ResponseEntity.notFound().build();
        }      
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRegistrationDTO userDto) {
        User createdUser = userService.registerUser(userDto);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest signInRequest) {
        Object response = userService.signIn(signInRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestBody OtpVerificationRequest request) {
        String response = userService.verifyOtp(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/assign-role")
    public ResponseEntity<User> assignRole(@RequestHeader("Authorization") String token, @RequestBody AssignRoleRequest request) {
        return ResponseEntity.ok(userService.assignRoleToUser(request));
    }
}
>>>>>>> 8a8786ed1032cfcfec9908411367fd4fcb8deeb4
