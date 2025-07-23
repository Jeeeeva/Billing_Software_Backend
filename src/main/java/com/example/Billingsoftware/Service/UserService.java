package com.example.Billingsoftware.Service;

import com.example.Billingsoftware.io.UserRequest;
import com.example.Billingsoftware.io.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest request);

    String getUserRole(String email);

    List<UserResponse> readUser();

    void deleteUser(String id);
}
