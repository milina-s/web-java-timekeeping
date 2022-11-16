package com.example.repositories;

import com.example.entities.User;
import com.example.repositories.dao.UserDao;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class UserRepository implements Repository<User> {

    private final UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public void create(User user) {
        userDao.create(user);
    }

    @Override
    public void update(Long id, User newItem) {
        userDao.update(id, newItem);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }
}
