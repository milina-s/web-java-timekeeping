package com.example.delegator;

import com.example.repositories.*;
import com.example.repositories.dao.ActivityDao;
import com.example.repositories.dao.CategoryDao;
import com.example.repositories.dao.RequestDao;
import com.example.repositories.dao.UserDao;

import java.util.HashMap;
import java.util.Map;

public class RepositoryDelegator implements Delegator <Repository<?>>{

    private final Map<Class<?>, Repository<?>> repositories;

    public RepositoryDelegator(DaoDelegator daoDelegator) throws ClassNotFoundException {
        this.repositories = new HashMap<>(){{
            put(ActivityRepository.class, new ActivityRepository((ActivityDao) daoDelegator.getByClass(ActivityDao.class)));
            put(CategoryRepository.class, new CategoryRepository((CategoryDao) daoDelegator.getByClass(CategoryDao.class)));
            put(RequestRepository.class, new RequestRepository((RequestDao) daoDelegator.getByClass(RequestDao.class)));
            put(UserRepository.class, new UserRepository((UserDao) daoDelegator.getByClass(UserDao.class)));
        }};
    }

    @Override
    public Repository<?> getByClass(Class<?> findClass) throws ClassNotFoundException {
        if (!this.repositories.containsKey(findClass))
            throw new ClassNotFoundException("Class " + findClass + " is not founded");
        return this.repositories.getOrDefault(findClass, null);
    }
}
