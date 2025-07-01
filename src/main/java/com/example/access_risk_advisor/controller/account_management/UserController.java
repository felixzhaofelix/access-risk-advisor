package com.example.access_risk_advisor.controller.account_management;

import com.example.access_risk_advisor.model.account_management.User;
import com.example.access_risk_advisor.service.account_management.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User Management", description = "Endpoints for managing users")
@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        logger.info("Received request to create user '{}'", user.getUsername());
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        logger.info("Fetching all users");
        return userService.getAllUsers();
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable String username) {
        logger.info("Fetching user '{}'", username);
        return userService.getUserByUsername(username);
    }

    @GetMapping("/department-id/{departmentId}")
    public List<User> getUsersByDepartmentId(@PathVariable int departmentId) {
        logger.info("Fetching users in department ID '{}'", departmentId);
        return userService.getUsersByDepartmentId(departmentId);
    }

    @GetMapping("/company-id/{companyId}")
    public List<User> getUsersByCompanyId(@PathVariable int companyId) {
        logger.info("Fetching users in company ID '{}'", companyId);
        return userService.getUsersByCompanyId(companyId);
    }

    @PutMapping("/{username}")
    public User updateUser(@PathVariable String username, @Valid @RequestBody User updatedUser) {
        logger.info("Updating user '{}'", username);
        return userService.updateUser(username, updatedUser);
    }

    @DeleteMapping("/{username}")
    public String deleteUser(@PathVariable String username) {
        logger.info("Deleting user '{}'", username);
        userService.deleteUser(username);
        return "Deleted user: " + username;
    }
}
