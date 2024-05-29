package com.jtsp.springaopdemo.service;

import com.jtsp.springaopdemo.entity.User;
import com.jtsp.springaopdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User[]> getUsers() {
        return userRepository.getUsers();
    }

    public Optional<User> createNewUser(User user) {
        return userRepository.createNewUser(user);
    }

    public Optional<User> updateUserById(Long id, User user) {
        return userRepository.updateUserById(id, user);
    }

    public boolean deleteUserById(Long id) {
        return userRepository.deleteUserById(id);
    }
}
