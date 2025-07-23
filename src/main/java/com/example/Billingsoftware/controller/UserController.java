package com.example.Billingsoftware.controller;

import com.example.Billingsoftware.Service.UserService;
import com.example.Billingsoftware.io.UserRequest;
import com.example.Billingsoftware.io.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@RequestBody UserRequest request){
        try{
            return userService.createUser(request);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Unable to create User"+e.getMessage());
        }

    }

    @GetMapping("/users")
    public List<UserResponse> readUser(){
        return userService.readUser();
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deteleUser(@PathVariable String id){

        try{
            userService.deleteUser(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User Not Found");
        }

    }
}
