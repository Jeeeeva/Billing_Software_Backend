//package com.example.Billingsoftware.config;
//
//import com.example.Billingsoftware.entity.UserEntity;
//import com.example.Billingsoftware.Respository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import jakarta.annotation.PostConstruct;
//
//@Component
//@RequiredArgsConstructor
//public class AdminInitializer {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @PostConstruct
//    public void initAdmin() {
//        String adminEmail = "admin@gmail.com";
//        if (userRepository.findByEmail(adminEmail).isEmpty()) {
//            UserEntity admin = UserEntity.builder()
//                    .userId(java.util.UUID.randomUUID().toString())
//                    .name("Admin")
//                    .email(adminEmail)
//                    .password(passwordEncoder.encode("admin123"))
//                    .role("ROLE_ADMIN")
//                    .build();
//
//            userRepository.save(admin);
//            System.out.println("✅ Admin user created successfully.");
//        } else {
//            System.out.println("ℹ️ Admin user already exists.");
//        }
//    }
//}
