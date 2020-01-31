package com.nguyen.repository;

import com.nguyen.model.Role;
import com.nguyen.model.User;

import java.util.*;
import java.util.stream.Stream;


public class UserRepository {

    private static HashMap<String, User> users;
    static {
        users = new HashMap<String, User>();
        Stream.of(
                new User("admin", "123", Arrays.asList(Role.ROLE_ADMIN)),
                new User("customer", "123", Arrays.asList(Role.ROLE_CUSTOMER)),
                new User("guest", "123", new ArrayList<String>()),
                new User("root", "123", Arrays.asList(Role.ROLE_ADMIN, Role.ROLE_CUSTOMER))).
                forEach(user -> users.put(user.getUsername(), user));
    }

    public User findByUsernameAndPassword(String username, String password) {
        return users.entrySet().stream().
                filter(e -> e.getKey().equals(username) && e.getValue().getPassword().equals(password)).
                map(Map.Entry::getValue).
                findFirst().
                orElse(null);
    }
}
