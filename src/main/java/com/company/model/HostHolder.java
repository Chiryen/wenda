package com.company.model;

import org.springframework.stereotype.Component;

/**
 * Created by John on 2017/5/21.
 */
@Component
public class HostHolder {

    private static ThreadLocal<User> users = new ThreadLocal<User>();

    public User getUser() {
        return users.get();
    }

    public void setUsers(User user) {
        users.set(user);
    }

    public void clear() {
        users.remove();
    }
}
