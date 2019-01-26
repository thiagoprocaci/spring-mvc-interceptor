package com.tbp.interceptor;

import com.tbp.repository.User;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode= ScopedProxyMode.TARGET_CLASS)
public class UserSession {

    User user;

    public void addLoggedUser(User u) {
        this.user = u;
    }

    public User getLoggedUser() {
        return user;
    }

}
