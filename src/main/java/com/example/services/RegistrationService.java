package com.example.services;

import com.example.entities.User;
import com.example.entities.UserRole;
import com.example.repositories.UserDao;
import com.example.session.CurrentSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class RegistrationService implements Service {

    private static final Logger logger = LogManager.getLogger(RegistrationService.class);
    private final UserDao userDao;

    public RegistrationService(UserDao userDao) {
        this.userDao = userDao;
    }


    public boolean login(String email, String password) {
        Optional<User> userOptional = userDao.findByEmail(email);
        if (userOptional.isEmpty()) {
            logger.info("No user with email " + email);
            return false;
        }
        User user = userOptional.get();
        if (!user.getPassword().equals(password)) {
            logger.info("Wrong password for email " + email);
            return false;
        }
        CurrentSession.setUser(user);
        return true;
    }

    public boolean register(String email, String password, UserRole role, String name, String lastname) {
        Optional<User> userOptional = userDao.findByEmail(email);
        if (userOptional.isPresent()) {
            logger.info("User with this email " + email + " already exists");
            return false;
        }
        User user = User.builder()
                .email(email)
                .password(password)
                .role(role)
                .name(name)
                .lastname(lastname)
                .build();
        userDao.create(user);
        CurrentSession.setUser(user);
        return true;
    }
}
