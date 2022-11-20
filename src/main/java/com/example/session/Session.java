package com.example.session;

import com.example.entities.UserRole;

public class Session {

    private String email;
    private UserRole role;
    private final Object lock;

    public Session() {
        this.role = UserRole.NOT_AUTHORIZED;
        lock = new Object();
    }

    public void setRole(UserRole role) {
        synchronized (lock) {
            this.role = role;
        }
    }

    public UserRole getRole() {
        synchronized (lock) {
            return this.role;
        }
    }

    public void setEmail(String email) {
        synchronized (lock) {
            this.email = email;
        }
    }

    public String getEmail() {
        synchronized (lock) {
            return this.email;
        }
    }

    public void clear() {
        synchronized (lock) {
            this.role = UserRole.NOT_AUTHORIZED;
        }
    }

}
