package com.example.delegator;

import com.example.repositories.*;
import com.example.repositories.dao.ActivityDao;
import com.example.repositories.dao.CategoryDao;
import com.example.repositories.dao.RequestDao;
import com.example.repositories.dao.UserDao;
import com.example.services.CategoryService;
import com.example.services.RegistrationService;
import com.example.services.Service;

import java.util.HashMap;
import java.util.Map;

public class ServiceDelegator implements Delegator<Service> {

    private final Map<Class, Service> services;

    public ServiceDelegator(RepositoryDelegator repositoryDelegator) throws ClassNotFoundException {
        this.services = new HashMap<>(){{
            put(CategoryService.class, new CategoryService((CategoryRepository) repositoryDelegator.getByClass(CategoryRepository.class)));
            put(RegistrationService.class, new RegistrationService((UserRepository) repositoryDelegator.getByClass(UserRepository.class)));

        }};
    }

    @Override
    public Service getByClass(Class<?> findClass) throws ClassNotFoundException {
        if (!this.services.containsKey(findClass))
            throw new ClassNotFoundException("Class " + findClass + " is not founded");
        return this.services.getOrDefault(findClass, null);
    }
}
