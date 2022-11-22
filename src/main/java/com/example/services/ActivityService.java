package com.example.services;

import com.example.entities.Activity;
import com.example.entities.Category;
import com.example.repositories.ActivityDao;
import com.example.repositories.CategoryDao;
import com.example.repositories.RequestDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class ActivityService implements Service {
    private static final Logger logger = LogManager.getLogger(ActivityService.class);
    private final RequestDao requestDao;
    private final ActivityDao activityDao;
    private final CategoryDao categoryDao;

    public ActivityService(RequestDao requestDao, ActivityDao activityDao, CategoryDao categoryDao) {
        this.requestDao = requestDao;
        this.activityDao = activityDao;
        this.categoryDao = categoryDao;
    }

    public boolean createActivity(String name, Long category_id) {

        if (categoryDao.findById(category_id).isEmpty())
            return false;
        activityDao.create(Activity.builder()
                .name(name)
                .category_id(category_id)
                .build());
        return true;
    }

    public List<Category> getAllCategories () {
        return categoryDao.findAll();
    }

    public String getCategoryNameById (Long id) {
        return categoryDao.findById(id).orElse(Category.builder().name("No name").build()).getName();
    }

    public List<Activity> getAllActivities () {
        return activityDao.findAll();
    }

    public List<Activity> getActivitiesByCategoryName(String category_name) {
        Optional<Category> categoryOptional = categoryDao.findByName(category_name);
        if (categoryOptional.isEmpty()) {
            logger.info("No category with name " + category_name);
            return null;
        }
        return activityDao.findByCategoryId(categoryOptional.get().getId());
    }


}
