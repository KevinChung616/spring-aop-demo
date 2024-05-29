package com.jtsp.springaopdemo.controller;

import com.jtsp.springaopdemo.entity.User;
import com.jtsp.springaopdemo.exception.UserNotFoundException;
import com.jtsp.springaopdemo.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users") // localhost:8080/demo
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping  // GET localhost:8080/users
    public ResponseEntity<User[]> getUsers() {
        return ResponseEntity.ok(userService.getUsers().get());
    }

    @PostMapping // POST localhost:8080/users
    public ResponseEntity<User> createNewUser(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.createNewUser(user).get());
    }

    @PutMapping("/{id}") // PUT localhost:8080/users/1
    public ResponseEntity<User> updateUserById(@PathVariable @Min(1L) Long id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUserById(id, user).get());
    }

    @DeleteMapping("/{id}") // DELETE localhost:8080/users/1
    public ResponseEntity<Boolean> deleteUserById(@PathVariable @Min(1L) Long id) {
        return ResponseEntity.ok(userService.deleteUserById(id));
    }

    @GetMapping("/error")
    public ResponseEntity<String> internalServerError() {
        throw new RuntimeException("internal server error");
    }

    @GetMapping("/user-not-found")
    public ResponseEntity<String> userNotFound() {
        throw new UserNotFoundException("user not found");
    }

}
