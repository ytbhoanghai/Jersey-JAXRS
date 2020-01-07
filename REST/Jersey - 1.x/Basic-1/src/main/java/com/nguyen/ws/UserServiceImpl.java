package com.nguyen.ws;

import javax.ws.rs.Path;
import java.util.HashMap;

@Path(value = "/users")
public class UserServiceImpl implements UserService {

    private static final HashMap<Integer, User> USERS;
    static {
        USERS = new HashMap<>();
        UserServiceImpl.createDummyData(USERS, 10);
    }

    private static void createDummyData(HashMap<Integer, User> USERS, int quantity) {
        for (int i = 0; i < quantity; i++) {
            Integer id = UserServiceImpl.generateUniqueKey(USERS);
            USERS.put(id, new User(id, "username" + i));
        }
    }

    private static Integer generateUniqueKey(HashMap<Integer, User> USERS) {
        return USERS.keySet().stream().max((num1, num2) -> num1 - num2).orElse(0) + 1;
    }

    public User[] getAll() {
        return USERS.values().toArray(new User[0]);
    }

    public User getById(Integer id) {
        return USERS.getOrDefault(id, new User());
    }

    public User update(Integer id, User user) {
        User currentUser = USERS.get(id);
        if (currentUser != null) {
            currentUser.setData(user);
            return currentUser;
        }
        return new User();
    }

    public User create(User user) {
        Integer id = UserServiceImpl.generateUniqueKey(USERS);
        user.setId(id);
        USERS.put(id, user);
        return USERS.get(id);
    }

    public User delete(Integer id) {
        return USERS.remove(id);
    }
}
