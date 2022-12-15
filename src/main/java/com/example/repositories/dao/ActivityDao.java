package com.example.repositories.dao;

import com.example.entities.Activity;
import com.example.entities.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ActivityDao extends DaoImpl<Activity> {

    public ActivityDao(Connection connection) {
        super(connection, "timekeeping.activity");
    }

    @Override
    public Activity buildItem(ResultSet rs) throws SQLException {
        return new Activity.ActivityBuilder()
                .setId(rs.getLong("id"))
                .setName(rs.getString("name"))
                .setCategory(new Category.CategoryBuilder()
                        .setId(rs.getLong("category.id"))
                        .build())
                .setDuration(rs.getInt("duration"))
                .build();
    }

    @Override
    public String getInsertSQLQuery() {
        return null;
    }

    @Override
    public void updateInsertSQLQuery(PreparedStatement ps, Activity item) throws SQLException {

    }

    @Override
    public String getUpdateSQLQuery() {
        return null;
    }

    @Override
    public void updateUpdateSQLQuery(PreparedStatement ps, Activity newItem, Activity oldItem) throws SQLException {

    }
}
