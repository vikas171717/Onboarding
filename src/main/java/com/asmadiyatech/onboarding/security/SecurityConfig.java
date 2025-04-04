
package com.asmadiyatech.onboarding.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.asmadiyatech.onboarding.config.JwtAuthenticationFilter;

@Configuration // Marks this class as a configuration class
@EnableWebSecurity // Enables Spring Security for the application
public class SecurityConfig {

    @Autowired // Injects JwtAuthenticationFilter
    private JwtAuthenticationFilter jwtAuthorizationFilter;

    // Bean for password encoding using BCrypt
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configures security settings for the application
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disables CSRF protection as JWT is used
                .csrf(csrf -> csrf.disable())

                // Configures authorization rules for different API endpoints
                .authorizeHttpRequests(authz -> authz
                        // Publicly accessible endpoints
                        .requestMatchers("/api/onboarding/user/register",
                                "/api/onboarding/user/signin",
                                "/api/onboarding/user/verify-otp",
                                "/api/onboarding/permission/create",
                                "/api/onboarding/tasks/create",
                                "/api/onboarding/role/create")
                        .permitAll()
                        // Admin access only
                        .requestMatchers("/api/onboarding/user/getAllUsers",
                                "/api/onboarding/user/{username}",
                                "/api/onboarding/user/assign-role",
                                "/api/onboarding/role/getAllRoles",
                                "/api/onboarding/role/{name}",
                                
                                "/api/onboarding/role/{roleId}/permissions/{permissionId}",
                                "/api/onboarding/permission/getAllPermissions",

                                "/api/onboarding/permission/{name}",
                                "/api/onboarding/permission/{id}",

                                "/api/onboarding/user/{userId}")
                        .hasRole("ADMIN")

                        
                        .requestMatchers("/api/onboarding/projects/create",
                                "/api/onboarding/tasks/{taskId}/assign/{userId}",
                                "/api/onboarding/projects/{projectId}/assign/{userId}")
                        .hasAnyRole("ADMIN", "PROJECT_MANAGER")
                        .requestMatchers("/api/onboarding/tasks/update-task-status/{taskId}")
                        .hasAnyRole("PROJECT_MANAGER", "PROJECT_ENGINEER")
                        .requestMatchers("/api/onboarding/projects/getAllProjects")
                        .hasAnyRole("ADMIN", "PROJECT_MANAGER", "PROJECT_ENGINEER", "EMPLOYEE")

                        // All other requests require authentication
                        .anyRequest().authenticated())

                // Sets session management policy to stateless (JWT-based authentication)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Adds JWT filter before Spring Security's authentication filter
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Configures CORS settings
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*") // Allows requests from all origins
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allows specified HTTP methods
                        .allowedHeaders("*"); // Allows all headers
            }
        };
    }
}