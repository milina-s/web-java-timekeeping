package com.example.services;

import com.example.entities.Request;
import com.example.entities.RequestStatus;
import com.example.entities.RequestType;
import com.example.repositories.ActivityDao;
import com.example.repositories.RequestDao;
import com.example.repositories.UserDao;
import com.example.session.CurrentSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class RequestService implements Service {

    private static final Logger logger = LogManager.getLogger(RequestService.class);
    private final RequestDao requestDao;
    private final UserDao userDao;
    private final ActivityDao activityDao;

    public RequestService(RequestDao requestDao, UserDao userDao, ActivityDao activityDao) {
        this.requestDao = requestDao;
        this.userDao = userDao;
        this.activityDao = activityDao;
    }

    public boolean creatRequest(Long activity_id, RequestType requestType) {
        if (requestType.equals(RequestType.ADD) &&
                requestDao.findByActivityIdAndUserId(activity_id, CurrentSession.getSession().getUserId()).isPresent()) {
            logger.info("This user already has activity with id=" + activity_id);
            return false;
        } else if (requestType.equals(RequestType.REMOVE) &&
                requestDao.findByActivityIdAndUserId(activity_id, CurrentSession.getSession().getUserId()).isEmpty()) {
            logger.info("This user has no activity with id=" + activity_id);
            return false;
        }
        requestDao.create(Request.builder()
                .activity_id(activity_id)
                .user_id(CurrentSession.getSession().getUserId())
                .duration(0)
                .status(RequestStatus.PENDING)
                .type(requestType)
                .build());
        return true;
    }

    public boolean setDuration (Long activity_id, int duration) {
        Optional<Request> requestOptional = requestDao.findByActivityIdAndUserId(activity_id, CurrentSession.getSession().getUserId());
        if (requestOptional.isEmpty() || !requestOptional.get().getStatus().equals(RequestStatus.CONFIRMED)){
            logger.info("This user has no confirmed activity with id=" + activity_id);
            return false;
        }
        requestDao.update(requestOptional.get().getId(), Request.builder().duration(duration).build());
        return true;
    }

    public List<Request> getConfirmedRequestsByUser () {
        return requestDao.findConfirmedByUserId(CurrentSession.getSession().getUserId());
    }


}
