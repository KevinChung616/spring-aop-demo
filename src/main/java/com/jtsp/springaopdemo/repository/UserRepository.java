package com.jtsp.springaopdemo.repository;

import com.jtsp.springaopdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Repository
public class UserRepository {
    private static final String URL_PREFIX = "https://jsonplaceholder.typicode.com/users";
    private final RestTemplate restTemplate;

    @Autowired
    public UserRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<User[]> getUsers() {
        User[] users = restTemplate.getForObject(this.URL_PREFIX, User[].class);
        return Optional.ofNullable(users);
    }

    public Optional<User> createNewUser(User user) {
        User newUser = restTemplate.postForObject(this.URL_PREFIX, user, User.class);
        return Optional.ofNullable(newUser);
    }

    public Optional<User> updateUserById(Long id, User user) {
        HttpEntity<User> updatedUser = new HttpEntity<>(user);
        HttpEntity<User> response = restTemplate.exchange(URL_PREFIX + "/" + id, HttpMethod.PUT, updatedUser, User.class);
        return Optional.ofNullable(response.getBody());
    }

    public boolean deleteUserById(Long id) {
        restTemplate.delete(URL_PREFIX + "/" + id);
        return true;
    }
}
