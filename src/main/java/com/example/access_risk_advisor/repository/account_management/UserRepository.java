package com.example.access_risk_advisor.repository.account_management;

import com.example.access_risk_advisor.model.account_management.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {

    private final Map<Integer, User> usersById = new HashMap<>();
    private final Map<String, User> usersByEmail = new HashMap<>();
    private int idCounter = 1;

    public User save(User user) {

        int id = idCounter++;
        user.setIdUser(id);
        usersById.put(id, user);
        usersByEmail.put(user.getEmail(), user);
        return user;
    }

    public int saveAndReturnId(User user) {
        return save(user).getIdUser();
    }

    public Optional<User> findById(int id) {
        return Optional.ofNullable(usersById.get(id));
    }

    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(usersByEmail.get(email));
    }

    public Optional<User> findByUsername(String username) {
        return usersById.values().stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username))
                .findFirst();
    }

    public List<User> findAll() {
        return new ArrayList<>(usersById.values());
    }

    public void deleteById(int id) {
        User removed = usersById.remove(id);
        if (removed != null) {
            usersByEmail.remove(removed.getEmail());
        }
    }

    public boolean existsByEmail(String email) {
        return usersByEmail.containsKey(email);
    }

    public boolean existsByUsername(String username) {
        return usersById.values().stream()
                .anyMatch(user -> user.getUsername().equalsIgnoreCase(username));
    }

}
