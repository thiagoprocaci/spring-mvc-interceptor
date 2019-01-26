package com.tbp.repository;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserRepository {

    static Map<Integer, User> personMap = new HashMap<>();
    static Integer count = 0;


    public void save(User user) {
        if(user.getId() == null) {
            user.setId(count);
            count++;
        }
        personMap.put(user.getId(), user);
    }

    public void delete(Integer name) {
        personMap.remove(name);
    }

    public User getById(Integer id) {
        return personMap.get(id);
    }

    public User getByUsername(String username) {
        for(User u : personMap.values()) {
            if(u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

}
