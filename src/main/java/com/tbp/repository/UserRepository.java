package com.tbp.repository;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserRepository {

    static Map<Integer, User> personMap = new HashMap<>();
    static Integer count = 0;

    public List<User> findAll() {
        List<User> userList = new ArrayList<User>();
        userList.addAll(personMap.values());
        return userList;
    }

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

}
