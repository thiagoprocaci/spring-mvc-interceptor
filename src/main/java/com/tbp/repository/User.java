package com.tbp.repository;

public class User {

    Integer id;
    String name;
    String password;
    String profile;

    public User(String name, String password, String profile) {
        this.name = name;
        this.password = password;
        this.profile = profile;
    }

    public User() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
