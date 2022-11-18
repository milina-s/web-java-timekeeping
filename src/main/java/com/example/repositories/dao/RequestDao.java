package com.example.repositories.dao;

import com.example.entities.Request;
import com.example.entities.RequestStatus;
import com.example.entities.RequestType;
import com.example.exeptions.SQLOperationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RequestDao extends DaoImpl<Request> {

    public RequestDao(Connection connection) {
        super(connection, "timekeeping.request");
    }

    @Override
    public Request buildItem(ResultSet rs) throws SQLException {
        return Request.builder()
                .id(rs.getLong("id"))
                .activity_id(rs.getLong("activity_id"))
                .user_id(rs.getLong("user_id"))
                .status(RequestStatus.getRequestStatus(rs.getString("status")))
                .type(RequestType.getRequestType(rs.getString("type")))
                .build();
    }

    @Override
    public String getInsertSQLQuery() {
        return "INSERT INTO " + tableName +
                " (activity_id, user_id, status, type)" +
                " VALUES (?, ?, ?, ?)";
    }

    @Override
    public void updateInsertSQLQuery(PreparedStatement ps, Request item) throws SQLException {
        ps.setLong(1, item.getActivity_id());
        ps.setLong(2, item.getUser_id());
        ps.setString(3, item.getStatus().name());
        ps.setString(4, item.getType().name());
    }

    @Override
    public String getUpdateSQLQuery() {
        return "UPDATE " + tableName +
                " SET activity_id = ?, user_id = ?, status = ?, type = ?" +
                " WHERE id = ?";
    }

    @Override
    public void updateUpdateSQLQuery(PreparedStatement ps, Request newItem, Request oldItem) throws SQLException {
        if (newItem.getActivity_id() != null) {
            ps.setLong(1, newItem.getActivity_id());
        } else ps.setLong(1, oldItem.getActivity_id());

        if (newItem.getUser_id() != null) {
            ps.setLong(2, newItem.getUser_id());
        } else ps.setLong(2, oldItem.getUser_id());

        if (newItem.getStatus() != null) {
            ps.setString(3, newItem.getStatus().name());
        } else ps.setString(3, oldItem.getStatus().name());

        if (newItem.getType() != null) {
            ps.setString(4, newItem.getType().name());
        } else ps.setString(4, oldItem.getType().name());

        ps.setLong(5, oldItem.getId());
    }

    @Override
    public void update(Long id, Request newItem) {
        if (id == null && newItem.getId() == null)
            throw new SQLOperationException("Couldn't update category without id");
        if (id == null)
            id = newItem.getId();
        super.update(id, newItem);
    }

    public List<Request> findByUserId (Long id) {
        String sql = "SELECT * FROM " + tableName + " WHERE user_id = " + id;
        return findAllBy(sql);
    }

    public List<Request> findByCategoryId (Long id) {
        String sql = "SELECT * FROM " + tableName + " WHERE category_id = " + id;
        return findAllBy(sql);
    }

    public List<Request> findByStatus (RequestStatus status) {
        String sql = "SELECT * FROM " + tableName + " WHERE status = '" + status.name() + "'";
        return findAllBy(sql);
    }

    public List<Request> findByType (RequestType type) {
        String sql = "SELECT * FROM " + tableName + " WHERE type = '" + type.name() + "'";
        return findAllBy(sql);
    }
}
