package com.example.repositories;

import com.example.entities.Activity;
import com.example.exeptions.SQLOperationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ActivityDao extends DaoImpl<Activity> {

    public ActivityDao(Connection connection) {
        super(connection, "timekeeping.activity");
    }

    @Override
    public Activity buildItem(ResultSet rs) throws SQLException {
        return Activity.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .category_id(rs.getLong("category_id"))
                .build();
    }

    @Override
    public String getInsertSQLQuery() {
        return "INSERT INTO " + tableName +
                " (name, category_id)" +
                " VALUES (?, ?)";
    }

    @Override
    public void updateInsertSQLQuery(PreparedStatement ps, Activity item) throws SQLException {
        ps.setString(1, item.getName());
        ps.setLong(2, item.getCategory_id());
    }

    @Override
    public String getUpdateSQLQuery() {
        return "UPDATE " + tableName +
                " SET name = ?, category_id = ?" +
                " WHERE id = ?";
    }

    @Override
    public void updateUpdateSQLQuery(PreparedStatement ps, Activity newItem, Activity oldItem) throws SQLException {
        if (newItem.getName() != null) {
            ps.setString(1, newItem.getName());
        } else ps.setString(1, oldItem.getName());

        if (newItem.getCategory_id() != null) {
            ps.setString(2, newItem.getName());
        } else ps.setString(2, oldItem.getName());

        ps.setLong(3, oldItem.getId());
    }

    @Override
    public void update(Long id, Activity newItem) {
        if (id == null && newItem.getId() == null)
            throw new SQLOperationException("Couldn't update category without id");
        if (id == null)
            id = newItem.getId();
        super.update(id, newItem);
    }

    public List<Activity> findByName (String name) {
        String sql = "SELECT * FROM " + tableName + " WHERE name = '" + name + "'";
        return findAllBy(sql);
    }

    public List<Activity> findByCategoryId (Long id) {
        String sql = "SELECT * FROM " + tableName + " WHERE category_id = " + id;
        return findAllBy(sql);
    }

    public List<Activity> findByUserId (Long id) {
        String sql = "SELECT * FROM " + tableName + " WHERE category_id = " + id;
        return findAllBy(sql);
    }
}
