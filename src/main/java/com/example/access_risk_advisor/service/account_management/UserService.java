package com.example.access_risk_advisor.service.account_management;

import com.example.access_risk_advisor.exception.ResourceNotFoundException;
import com.example.access_risk_advisor.model.account_management.User;
import com.example.access_risk_advisor.repository.account_management.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User with username '" + username + "' not found"));
    }

    public List<User> getUsersByDepartmentId(int departmentId) {
        return userRepository.findAll().stream()
                .filter(user -> user.getDepartmentId() == departmentId)
                .toList();
    }

    public List<User> getUsersByCompanyId(int companyId) {
        return userRepository.findAll().stream()
                .filter(user -> user.getCompanyId() == companyId)
                .toList();
    }

    public User updateUser(String username, User updatedUser) {
        User existingUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User with username '" + username + "' not found"));

        updatedUser.setIdUser(existingUser.getIdUser()); // Ensure ID is preserved
        return userRepository.save(updatedUser);
    }

    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User with username '" + username + "' not found"));
        userRepository.deleteById(user.getIdUser());
    }
}

