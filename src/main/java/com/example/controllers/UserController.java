package com.example.controllers;

import com.example.delegator.ServiceDelegator;
import com.example.entities.*;
import com.example.services.ActivityService;
import com.example.services.RequestService;
import com.example.session.CurrentSession;
import com.example.view.UserView;

import java.util.List;

public class UserController {

    private final ActivityService activityService;
    private final RequestService requestService;
    private final UserView userView;

    public UserController(ServiceDelegator serviceDelegator) throws ClassNotFoundException {
        activityService = (ActivityService) serviceDelegator.getByClass(ActivityService.class);
        requestService = (RequestService) serviceDelegator.getByClass(RequestService.class);
        userView = new UserView();
    }

    public void start() {
        UserAction action;
        do {
            action = userView.chooseAction();
            if (action == null) {
                userView.errorMessage();
                continue;
            }
            switch (action) {
                case ADD_ACTIVITY_REQUEST -> addActivityRequest();
                case REMOVE_ACTIVITY_REQUEST -> removeActivityRequest();
                case SET_TIME_TO_ACTIVITY -> setTimeToActivity();
                case GET_CATEGORIES -> getCategories();
                case GET_ALL_ACTIVITIES -> getAllActivities();
                case GET_ALL_ACTIVITIES_FILTERED_BY_CATEGORY -> getAllActivitiesFilteredByCategory();
                case SIGN_OUT -> {
                    userView.signOut();
                    CurrentSession.clear();
                }
                default -> {
                    userView.errorMessage();
                    start();
                }
            }
        } while (action != UserAction.SIGN_OUT);
    }

    public void addActivityRequest() {
        Long activity_id = userView.getActivityId();
        if (!requestService.creatRequest(activity_id, RequestType.ADD))
            userView.errorMessage();
    }

    public void removeActivityRequest() {
        Long activity_id = userView.getActivityId();
        if (!requestService.creatRequest(activity_id, RequestType.REMOVE))
            userView.errorMessage();
    }

    public void setTimeToActivity() {
        Long activity_id = userView.getActivityId();
        int duration = userView.getDuration();
        if (!requestService.setDuration(activity_id, duration))
            userView.errorMessage();

    }

    public void getAllActivities() {
        List<Activity> activities = activityService.getAllActivities();
        if (activities == null || activities.size() == 0) {
            userView.errorMessage();
            return;
        }
        StringBuilder res = new StringBuilder(String.format("%3s. %-10s %-20s\n", "Id", "Category", "Activity"));
        activities.forEach(x -> res.append(String.format("%3s. %-10s %-20s\n", x.getId(), activityService.getCategoryNameById(x.getCategory_id()), x.getName())));
        userView.print(res.toString());
    }

    public void getAllActivitiesFilteredByCategory() {
        String category_name = userView.getCategoryName();
        List<Activity> activities = activityService.getActivitiesByCategoryName(category_name);
        if (activities == null || activities.size() == 0) {
            userView.errorMessage();
            return;
        }
        StringBuilder res = new StringBuilder("Category: ")
                .append(category_name)
                .append(String.format("\n%3s. %-20s\n", "Id", "Activity"));
        activities.forEach(x -> res.append(String.format("%3s. %-20s\n", x.getId(), x.getName())));
        userView.print(res.toString());
    }

    public void getCategories() {
        List<Category> categories = activityService.getAllCategories();
        if (categories == null || categories.size() == 0) {
            userView.errorMessage();
            return;
        }
        StringBuilder res = new StringBuilder(String.format("%3s. %-10s \n", "Id", "Category"));
        categories.forEach(x -> res.append(String.format("%3s. %-10s \n", x.getId(), x.getName())));
        userView.print(res.toString());
    }

//    public void getMyActivities () {
//        List<Request> requests = requestService.getConfirmedRequestsByUser();
//        if (requests == null || requests.size() == 0) {
//            userView.errorMessage();
//            return;
//        }
//        StringBuilder res = new StringBuilder(String.format("%3s. %-10s %-20s\n", "Id", "Category", "Activity"));
//        activities.forEach(x -> res.append(String.format("%3s. %-10s %-20s\n", x.getId(), activityService.getCategoryNameById(x.getCategory_id()), x.getName())));
//        userView.print(res.toString());
//    }

}