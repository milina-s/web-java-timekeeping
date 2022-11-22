package com.example.repositories;

import com.example.entities.User;
import com.example.entities.UserRole;
import com.example.exeptions.SQLOperationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDao extends DaoImpl<User> {

    public UserDao(Connection connection) {
        super(connection, "timekeeping.user");
    }

    @Override
    public User buildItem(ResultSet rs) throws SQLException {
        return User.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .lastname(rs.getString("lastname"))
                .email(rs.getString("email"))
                .password(rs.getString("password"))
                .role(UserRole.getUserRole(rs.getString("role")))
                .build();
    }

    @Override
    public String getInsertSQLQuery() {
        return "INSERT INTO " + tableName +
                " (name, lastname, email, password, role)" +
                " VALUES (?, ?, ?, ?, ?)";
    }

    @Override
    public void updateInsertSQLQuery(PreparedStatement ps, User item) throws SQLException {
        ps.setString(1, item.getName());
        ps.setString(2, item.getLastname());
        ps.setString(3, item.getEmail());
        ps.setString(4, item.getPassword());
        ps.setString(5, item.getRole().name());
    }

    @Override
    public String getUpdateSQLQuery() {
        return "UPDATE " + tableName +
                " SET name = ?, lastname = ?, email = ?, password = ?, role = ?" +
                " WHERE id = ?";
    }

    @Override
    public void updateUpdateSQLQuery(PreparedStatement ps, User newItem, User oldItem) throws SQLException {
        if (newItem.getName() != null) {
            ps.setString(1, newItem.getName());
        } else ps.setString(1, oldItem.getName());

        if (newItem.getLastname() != null) {
            ps.setString(2, newItem.getLastname());
        } else ps.setString(2, oldItem.getLastname());

        if (newItem.getEmail() != null) {
            ps.setString(3, newItem.getEmail());
        } else ps.setString(3, oldItem.getEmail());

        if (newItem.getPassword() != null) {
            ps.setString(4, newItem.getPassword());
        } else ps.setString(4, oldItem.getPassword());

        if (newItem.getRole() != null) {
            ps.setString(5, newItem.getRole().name());
        } else ps.setString(5, oldItem.getRole().name());

        ps.setLong(6, oldItem.getId());
    }

    @Override
    public void update(Long id, User newItem) {
        if (id == null && newItem.getId() == null)
            throw new SQLOperationException("Couldn't update category without id");
        if (id == null)
            id = newItem.getId();
        super.update(id, newItem);
    }

    public Optional<User> findByEmail (String email) {
        String sql = "SELECT * FROM " + tableName + " WHERE email = '" + email + "'";
        return findBy(sql);
    }

    public List<User> findByRole (UserRole role) {
        String sql = "SELECT * FROM " + tableName + " WHERE role = '" + role.name() + "'";
        return findAllBy(sql);
    }
}
