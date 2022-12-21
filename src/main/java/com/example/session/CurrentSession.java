package com.example.session;

import com.example.entities.User;
import com.example.entities.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CurrentSession {
    private static final Logger logger = LogManager.getLogger(CurrentSession.class);

    private static final Session session = new Session();

    public static Session getSession() {
        return session;
    }

    public static void setUser(User user) {
        session.setUser(user);
        logger.info("Authorized as " + user.getName() + ". Any further operations will be processed by this role.");
    }

    public static UserRole getRole() {
        return session.getRole();
    }

    public static void clear() {
        session.clear();
        logger.info("Session was cleared. Role is set to " + UserRole.NOT_AUTHORIZED);
    }

}