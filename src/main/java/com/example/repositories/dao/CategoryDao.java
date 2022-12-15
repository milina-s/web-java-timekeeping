package com.example.repositories.dao;

import com.example.entities.Category;
import com.example.exeptions.SQLOperationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CategoryDao extends DaoImpl<Category> {

    public CategoryDao(Connection connection) {
        super(connection, "timekeeping.category");
    }

    @Override
    public Category buildItem(ResultSet rs) throws SQLException {
        return new Category.CategoryBuilder()
                .setId(rs.getLong("id"))
                .setName(rs.getString("name"))
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
        return "UPDATE " + this.tableName +
                " SET id = ?, name = ?" +
                " WHERE id = ?";
    }

    @Override
    public void updateUpdateSQLQuery(PreparedStatement ps, Category newItem, Category oldItem) throws SQLException {
//        if (newItem.getId() != null) {
//            ps.setLong(1, newItem.getId());
//        } else ps.setLong(1, oldItem.getId());

        if (newItem.getName() != null) {
            ps.setString(2, newItem.getName());
        } else ps.setString(2, oldItem.getName());

        ps.setLong(3, oldItem.getId());
    }

    @Override
    public void update(Long id, Category newItem) {
        if (id == null && newItem.getId() == null)
            throw new SQLOperationException("Couldn't update category without id");
        if (id == null)
            id = newItem.getId();
        super.update(id, newItem);
    }

    public List<Category> findByName (String name) {
        String sql = "SELECT * FROM " + tableName + " WHERE name = " + name;
        return findBy(sql);
    }
}
