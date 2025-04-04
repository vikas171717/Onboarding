package com.asmadiyatech.onboarding.serviceImplementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.asmadiyatech.onboarding.config.JWTProvider;
import com.asmadiyatech.onboarding.dto.AssignRoleRequest;
import com.asmadiyatech.onboarding.dto.JwtResponse;
import com.asmadiyatech.onboarding.dto.OtpVerificationRequest;
import com.asmadiyatech.onboarding.dto.SignInRequest;
import com.asmadiyatech.onboarding.dto.UserRegistrationDTO;
import com.asmadiyatech.onboarding.entities.Role;
import com.asmadiyatech.onboarding.entities.User;
import com.asmadiyatech.onboarding.repository.RoleRepository;
import com.asmadiyatech.onboarding.repository.UserRepository;
import com.asmadiyatech.onboarding.service.MailSenderService;
import com.asmadiyatech.onboarding.service.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private MailSenderService mailSenderService;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Transactional
    @Override
    public Boolean deleteUser(String username) {
        if (userRepository.existsByUsername(username)) {
            userRepository.deleteByUsername(username);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User registerUser(UserRegistrationDTO userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new RuntimeException("Email already in use");
        }
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setAge(userDto.getAge());
        user.setPhoneNo(userDto.getPhoneNo());
        user.setAddress(userDto.getAddress());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        // Check if role is provided
        String roleInput = userDto.getRole();

        Role role = roleRepository.findByName(
                roleInput != null ? roleInput.toUpperCase() : "EMPLOYEE")
        .orElseGet(() -> roleRepository.findByName("EMPLOYEE")
                .orElseThrow(() -> new RuntimeException("Default role 'EMPLOYEE' not found!")));


        // Set the role for the user and save the user
        user.setRole(role);

        return userRepository.save(user);
    }

    @Override
    public Object signIn(SignInRequest signInRequest) {
        User user = userRepository.findByUsername(signInRequest.getUsername()).orElseThrow(
                () -> new RuntimeException("User not found with username: " + signInRequest.getUsername()));

        if (!user.getIsVerified()) {
            sendOtp(user);
            return new JwtResponse(null, "User not verified yet OTP sent please verify");
        }
        // Fetch the password by Student prn
        String password = user.getPassword();
        // Validate password
        if (!passwordEncoder.matches(signInRequest.getPassword(), password)) {
            throw new RuntimeException("Invalid credentials");
        }

        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println(user.getRole().getName().toString());
        System.out.println("---------------------------------------------------------------------------------------------");


        // Generate JWT token with email as the subject
        String token = jwtProvider.generateToken(user.getEmail(), user.getRole().getName().toString().toUpperCase());
        // Return JwtResponse with token and a success message

        return new JwtResponse(token, "Login successful for: " + user.getName());
    }

    @Override
    public void sendOtp(User user) {
        // Generate OTP and expiration
        String otp = String.valueOf((int) (Math.random() * 900000) + 100000); // 6-digit OTP
        user.setOtp(otp);
        user.setOtpExpiration(LocalDateTime.now().plusMinutes(10)); // OTP valid for 10 minutes

        userRepository.save(user);

        // Send OTP email (logic to be implemented in mail service)
        mailSenderService.sendOtp(user.getEmail(), otp);
    }

    @Transactional
    @Override
    public String verifyOtp(OtpVerificationRequest request) {
        // Fetch the Student by ID
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found with username: " + request.getUsername()));

        // Check if the OTP exists and matches
        if (user.getOtp() == null || !user.getOtp().equals(request.getOtp())) {
            throw new RuntimeException("Invalid OTP");
        }

        // Check if the OTP expiration is valid (i.e., not expired)
        if (user.getOtpExpiration() == null || user.getOtpExpiration().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP has expired");
        }

        // Mark coordinator as verified
        user.setIsVerified(true);
        user.setOtp(null); // Clear OTP after successful verification
        user.setOtpExpiration(null); // Clear expiration time
        userRepository.save(user);

        return "User verified successfully";
    }

    @Override
    public User assignRoleToUser(AssignRoleRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        user.setRole(role);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username) ;
    }

}
