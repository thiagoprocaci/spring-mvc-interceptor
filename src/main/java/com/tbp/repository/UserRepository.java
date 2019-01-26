package com.tbp.repository;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserRepository {

    static Map<Integer, User> userMap = new HashMap<>();
    static Integer count = 0;

    static {
        User u = new User("user", "123", "Ordinary");
        u.setId(count);
        userMap.put(count, u);
        count++;
    }


    public void save(User user) {
        if(user.getId() == null) {
            user.setId(count);
            count++;
        }
        userMap.put(user.getId(), user);
    }

    public void delete(Integer name) {
        userMap.remove(name);
    }

    public User getById(Integer id) {
        return userMap.get(id);
    }

    public User getByUsername(String username) {
        for(User u : userMap.values()) {
            if(u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

}
