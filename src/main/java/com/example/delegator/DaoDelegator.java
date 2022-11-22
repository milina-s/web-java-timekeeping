package com.example.delegator;

import com.example.repositories.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class DaoDelegator implements Delegator <Dao<?>> {

    private final Map<Class<?>, Dao<?>> daos;

    public DaoDelegator(Connection connection) {
        this.daos = new HashMap<>(){{
            put(ActivityDao.class, new ActivityDao(connection));
            put(CategoryDao.class, new CategoryDao(connection));
            put(RequestDao.class, new RequestDao(connection));
            put(UserDao.class, new UserDao(connection));
        }};
    }

    @Override
    public Dao<?> getByClass(Class<?> findClass) throws ClassNotFoundException {
        if (!this.daos.containsKey(findClass))
            throw new ClassNotFoundException("Class " + findClass + " is not founded");
        return this.daos.getOrDefault(findClass, null);
    }
}
