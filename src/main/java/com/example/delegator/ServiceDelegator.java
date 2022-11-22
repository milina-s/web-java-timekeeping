package com.example.delegator;

import com.example.repositories.ActivityDao;
import com.example.repositories.CategoryDao;
import com.example.repositories.RequestDao;
import com.example.repositories.UserDao;
import com.example.services.ActivityService;
import com.example.services.RegistrationService;
import com.example.services.RequestService;
import com.example.services.Service;

import java.util.HashMap;
import java.util.Map;

public class ServiceDelegator implements Delegator<Service> {

    private final Map<Class, Service> services;

    public ServiceDelegator(DaoDelegator daoDelegator) throws ClassNotFoundException {
        this.services = new HashMap<>() {{
            //put(CategoryService.class, new CategoryService((CategoryRepository) daoDelegator.getByClass(CategoryRepository.class)));
            put(RegistrationService.class, new RegistrationService((UserDao) daoDelegator.getByClass(UserDao.class)));
            put(ActivityService.class, new ActivityService((RequestDao) daoDelegator.getByClass(RequestDao.class), (ActivityDao) daoDelegator.getByClass(ActivityDao.class),
                    (CategoryDao) daoDelegator.getByClass(CategoryDao.class)));
            put(RequestService.class, new RequestService((RequestDao) daoDelegator.getByClass(RequestDao.class),
                    (UserDao) daoDelegator.getByClass(UserDao.class),
                    (ActivityDao) daoDelegator.getByClass(ActivityDao.class)));
        }};
    }

    @Override
    public Service getByClass(Class<?> findClass) throws ClassNotFoundException {
        if (!this.services.containsKey(findClass))
            throw new ClassNotFoundException("Class " + findClass + " is not founded");
        return this.services.getOrDefault(findClass, null);
    }
}
