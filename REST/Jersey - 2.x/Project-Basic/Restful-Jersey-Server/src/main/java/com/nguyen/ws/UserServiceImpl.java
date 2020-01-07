package com.nguyen.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Path("/users")
public class UserServiceImpl implements UserService {

    private static final HashMap<Integer, User> USERS;
    private static AtomicInteger atomicInteger;
    static {
        USERS = new HashMap<Integer, User>();
        atomicInteger = new AtomicInteger();
        UserServiceImpl.createDummyData(USERS, 15);
    }

    private static void createDummyData(HashMap<Integer, User> USERS, int quantity) {
        for(int i = 0; i < quantity; i++) {
            Integer id = UserServiceImpl.generateUniqueId();
            USERS.put(id, new User(id, "username-" + i));
        }
    }

    private static Integer generateUniqueId() {
        return atomicInteger.getAndIncrement();
    }

    public User insert(User user) {
        Integer id = UserServiceImpl.generateUniqueId();
        user.setId(id);
        USERS.put(id, user);
        return USERS.get(id);
    }

    public User update(Integer id, User user) {
        User oldUser = USERS.get(id);
        oldUser.setData(user);
        return oldUser;
    }

    public User select(Integer id) {
        return USERS.get(id);
    }

    public User[] selectAll() {
        return USERS.values().toArray(new User[0]);
    }

    public User delete(Integer id) {
        return USERS.remove(id);
    }
}
