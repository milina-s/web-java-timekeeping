package com.example.repositories;

import com.example.entities.Activity;
import com.example.repositories.dao.ActivityDao;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ActivityRepository implements Repository<Activity> {

    private final ActivityDao activityDao;

    @Override
    public List<Activity> findAll() {
        return activityDao.findAll();
    }

    @Override
    public Activity findById(Long id) {
        return activityDao.findById(id);
    }

    @Override
    public void create(Activity activity) {
        activityDao.create(activity);
    }

    @Override
    public void update(Long id, Activity newItem) {
        activityDao.update(id, newItem);
    }

    @Override
    public void delete(Long id) {
        activityDao.delete(id);
    }
}
