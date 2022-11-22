package com.example.repositories;

import com.example.entities.Category;
import com.example.exeptions.SQLOperationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CategoryDao extends DaoImpl<Category> {

    public CategoryDao(Connection connection) {
        super(connection, "timekeeping.category");
    }

    @Override
    public Category buildItem(ResultSet rs) throws SQLException {
        return Category.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .build();
    }

    @Override
    public String getInsertSQLQuery() {
        return "INSERT INTO " + tableName +
                " (name)" +
                " VALUES (?)";
    }

    @Override
    public void updateInsertSQLQuery(PreparedStatement ps, Category item) throws SQLException {
        ps.setString(1, item.getName());
    }

    @Override
    public String getUpdateSQLQuery() {
        return "UPDATE " + tableName +
                " SET name = ?" +
                " WHERE id = ?";
    }

    @Override
    public void updateUpdateSQLQuery(PreparedStatement ps, Category newItem, Category oldItem) throws SQLException {
        if (newItem.getName() != null) {
            ps.setString(1, newItem.getName());
        } else ps.setString(1, oldItem.getName());

        ps.setLong(2, oldItem.getId());
    }

    @Override
    public void update(Long id, Category newItem) {
        if (id == null && newItem.getId() == null)
            throw new SQLOperationException("Couldn't update category without id");
        if (id == null)
            id = newItem.getId();
        super.update(id, newItem);
    }

    public Optional<Category> findByName (String name) {
        String sql = "SELECT * FROM " + tableName + " WHERE name = '" + name + "'";
        return findBy(sql);
    }
}
