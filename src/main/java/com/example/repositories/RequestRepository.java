package com.example.repositories;

import com.example.entities.Request;
import com.example.repositories.dao.RequestDao;

import java.util.List;

public class RequestRepository implements Repository<Request> {

    private final RequestDao requestDao;

    public RequestRepository(RequestDao requestDao) {
        this.requestDao = requestDao;
    }

    @Override
    public List<Request> findAll() {
        return requestDao.findAll();
    }

    @Override
    public Request findById(Long id) {
        return requestDao.findById(id);
    }

    @Override
    public void create(Request request) {
        requestDao.create(request);
    }

    @Override
    public void update(Long id, Request newItem) {
        requestDao.update(id, newItem);
    }

    @Override
    public void delete(Long id) {
        requestDao.delete(id);
    }
}
