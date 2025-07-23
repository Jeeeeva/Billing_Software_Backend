package com.example.Billingsoftware.controller;

import com.example.Billingsoftware.JWTUtil.JWTUtil;
import com.example.Billingsoftware.Service.UserService;
import com.example.Billingsoftware.Service.impl.AppUserDetailsServiceImpl;
import com.example.Billingsoftware.io.AuthRequest;
import com.example.Billingsoftware.io.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final AppUserDetailsServiceImpl appUserDetailsService;
    private final UserService userService;
    private final JWTUtil jwtUtil;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        try {
            authenticate(request.getEmail(), request.getPassword());
            final UserDetails userDetails = appUserDetailsService.loadUserByUsername(request.getEmail());
            final String jwtToken = jwtUtil.generateToken(userDetails);
            String role = userService.getUserRole(request.getEmail());
            return new AuthResponse(request.getEmail(), jwtToken, role);
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email or password is incorrect");
        } catch (DisabledException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User is disabled");
        }
    }

    private void authenticate(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
    }

    @PostMapping("/encode")
    public String encodePassword(@RequestBody Map<String, String> request) {
        String plainPassword = request.get("password");
        if (plainPassword == null || plainPassword.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password must not be empty");
        }
        return passwordEncoder.encode(plainPassword);
    }
}
