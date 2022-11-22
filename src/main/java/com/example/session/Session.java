package com.example.session;

import com.example.entities.User;
import com.example.entities.UserRole;

public class Session {

    private User user;
    private final Object lock;

    public Session() {
        this.user = User.builder().role(UserRole.NOT_AUTHORIZED).build();
        lock = new Object();
    }

    public void setUser(User user) {
        synchronized (lock) {
            this.user = user;
        }
    }

    public User getUser() {
        synchronized (lock) {
            return this.user;
        }
    }

    public Long getUserId() {
        synchronized (lock) {
            return this.user.getId();
        }
    }

    public UserRole getRole() {
        synchronized (lock) {
            return this.user.getRole();
        }
    }

    public void clear() {
        synchronized (lock) {
            this.user = User.builder().role(UserRole.NOT_AUTHORIZED).build();
        }
    }

}
