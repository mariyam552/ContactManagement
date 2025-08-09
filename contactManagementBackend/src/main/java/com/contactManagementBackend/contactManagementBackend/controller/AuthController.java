package com.contactManagementBackend.contactManagementBackend.controller;

import com.contactManagementBackend.contactManagementBackend.dto.PasswordChangeRequest;
import com.contactManagementBackend.contactManagementBackend.model.user;
import com.contactManagementBackend.contactManagementBackend.Repository.userRepository;
import com.contactManagementBackend.contactManagementBackend.service.AuthService;
import com.contactManagementBackend.contactManagementBackend.JwtUtil.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// ✅ Step 1: SLF4J import
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    // ✅ Step 2: Logger object
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private userRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthService authService;

    // ✅ Register User
    @PostMapping("/register")
    public String register(@RequestBody user user) {
        logger.info("Register API called for email: {}", user.getEmail());
        logger.debug("Received isAdmin: {}", user.getIsAdmin());
        return authService.registerUser(user);
    }

    // ✅ Login with Role-based JWT
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody user loginRequest) {
        logger.info("Login attempt for email: {}", loginRequest.getEmail());
        Optional<user> optionalUser = userRepository.findByEmail(loginRequest.getEmail());

        if (optionalUser.isPresent()) {
            user user = optionalUser.get();

            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

                Map<String, Object> response = new HashMap<>();
                response.put("token", token);
                response.put("name", user.getName());
                response.put("email", user.getEmail());
                response.put("role", user.getRole());
                response.put("isAdmin", user.getIsAdmin());

                logger.info("Login successful for: {}", user.getEmail());
                return ResponseEntity.ok(response);
            }
        }

        logger.warn("Login failed for email: {}", loginRequest.getEmail());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password.");
    }

    // ✅ Forgot Password
    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        logger.info("Forgot password requested for email: {}", email);
        return authService.handleForgotPassword(email);
    }

    // ✅ Reset Password
    @PostMapping("/reset-password")
    public String resetPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String newPassword = request.get("password");
        logger.info("Reset password called for email: {}", email);
        return authService.resetPassword(email, newPassword);
    }

    // ✅ Change Password
    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody PasswordChangeRequest request) {
        logger.info("Change password attempt for email: {}", request.getEmail());

        boolean success = authService.changePassword(
                request.getEmail(),
                request.getCurrentPassword(),
                request.getNewPassword()
        );

        if (success) {
            logger.info("Password changed successfully for: {}", request.getEmail());
            return ResponseEntity.ok("Password changed successfully");
        } else {
            logger.warn("Failed password change for: {}", request.getEmail());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Current password is incorrect");
        }
    }
}
